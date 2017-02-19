package com.lrs.redis.article;

import org.junit.Before;
import org.junit.Test;

import com.lrs.redis.TestRedisBase;

public class TestArticleVoted extends TestRedisBase{
	private VotedArticleRepository votedArticle;
	@Before
	public void testBefore(){
		votedArticle = new VotedArticleRepository();
		votedArticle.setJedis(super.jedis);
	}
	@Test
	public void testPostArticle(){
		votedArticle.postArticle("user01", "java is best", "link-to-article");
	}
}
