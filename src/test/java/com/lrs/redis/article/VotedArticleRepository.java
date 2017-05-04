package com.lrs.redis.article;

import java.util.HashMap;

import redis.clients.jedis.Jedis;

public class VotedArticleRepository {

	public static final int ONE_WEEK_IN_SECONDS = 7 * 24 * 60 * 60;

	private Jedis jedis;

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}

	/**
	 * 文章发布
	 * 
	 * @param user
	 * @param title
	 * @param link
	 */
	public long postArticle(final String user, final String title, final String link) {
		// 使用一个counter存储文章id
		long articleId = jedis.incr("article:");
		// 文章投票信息保存到set中
		String voted = "voted:" + articleId;
		jedis.sadd(voted, user);
		jedis.expire(voted, ONE_WEEK_IN_SECONDS);

		final long current = System.currentTimeMillis();
		// 文章信息存放 hash
		String article = "article:" + articleId;

		jedis.hmset(article, new HashMap<String, String>() {

			private static final long serialVersionUID = 1L;

			{
				put("title", title);
				put("link", link);
				put("poster", user);
				put("time", Long.toString(current));
				put("votes", Integer.toString(1));
			}
		});
		// 添加文章评分信息(score:)-zset(有序集合)
		jedis.zadd("score:", current, article);
		// 添加文章发布时间信息(time:)-zset(有序集合)
		jedis.zadd("time:", current, article);
		return articleId;

	}
}
