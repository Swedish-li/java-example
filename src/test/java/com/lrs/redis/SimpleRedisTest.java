package com.lrs.redis;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Test;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;

// set,zset
public class SimpleRedisTest {

	private static Jedis getJedis() {
		return new Jedis("127.0.0.1", 6379);
	}

	@Test
	public void testSortedSet() {
		Jedis jedis = getJedis();
		String key = "zset-key1";

		jedis.zadd(key, new HashMap<String, Double>() {
		
			private static final long serialVersionUID = 1L;

			{
				put("key1", 12.2);
				put("key2", 8D);
			}
		});
		
		String type = jedis.type(key);
		
		assertEquals("zset", type);
	}

	// sdiff:差集
	// sinte:交集
	// sunion:并集
	@Test
	public void testSet2() {
		Jedis jedis = getJedis();
		String key1 = "set-diff-key1";
		String key2 = "set-diff-key2";
		jedis.sadd(key1, "Characteristics", "Architecture", "restrict");
		jedis.sadd(key2, "Characteristics", "Stateless");

		// 返回一个集合的全部成员，该集合是所有给定集合之间的差集
		Set<String> set = jedis.sdiff(key1, key2);
		assertFalse("", set.contains("Characteristics"));
		assertFalse("", set.contains("Stateless"));
		assertTrue(set.contains("Architecture"));
		assertTrue(set.contains("restrict"));
	}

	private Jedis initData(String key) {
		Jedis jedis = getJedis();
		jedis.sadd(key, "Identifying Resources");
		jedis.sadd(key, "Designing URLs");
		jedis.sadd(key, "API Response");
		jedis.sadd(key, "Storing application data on server side");

		return jedis;
	}

	@Test
	public void testSet4() {
		String key = "set-key-4";
		Jedis jedis = initData(key);
		// Redis 2.4 版本以前， SREM 只接受单个 member 值
		long remCount = jedis.srem(key, "API Response", "Designing URLs");

		assertEquals(1, remCount);
		assertEquals(Long.valueOf(3), jedis.scard(key));

	}

	@Test
	public void testSet3() {
		String key = "set-key-3";

		Jedis jedis = initData(key);
		// 返回一个随机元素；如果集合为空，返回 nil
		String val = jedis.srandmember(key);

		assertTrue(jedis.sismember(key, val));
		assertEquals(Long.valueOf(4), jedis.scard(key));

		// 移除并返回集合中的一个随机元素。
		String val2 = jedis.spop(key);
		assertFalse(jedis.sismember(key, val2));
		assertEquals(Long.valueOf(3), jedis.scard(key));

	}

	@Test
	public void testSet() {
		Jedis jedis = getJedis();
		String setKey = "set-key1";
		jedis.sadd(setKey, "Characteristics", "Architecture", "restrict");
		Set<String> set = jedis.smembers(setKey);

		assertTrue(set.contains("Characteristics"));
		assertTrue(set.contains("Architecture"));
		assertTrue(set.contains("restrict"));

		assertEquals(Long.valueOf(1), jedis.srem(setKey, "restrict"));
		// 判断 member 元素是否集合 key 的成员。
		assertTrue(jedis.sismember(setKey, "Characteristics"));
		// 返回集合 key 的基数(集合中元素的数量)。
		assertEquals(Long.valueOf(2), jedis.scard(setKey));

	}

	@Test
	public void testList2() {
		// 删除值为value的元素（当count大于0时从左面删count个，count小于0从右面算起删count个，等于0，全删）
		String listKey = "list-key2";
		String[] strs = { "str", "str", "str3", "str4", "str", "str6" };
		Jedis jedis = getJedis();

		jedis.rpush(listKey, strs);
		jedis.lrem(listKey, 2, "str");

		assertArrayEquals(new Object[] { "str3", "str4", "str", "str6" }, jedis.lrange(listKey, 0, -1).toArray());

		jedis.lset(listKey, 2, "name");
		assertEquals("name", jedis.lindex(listKey, 2));

		jedis.ltrim(listKey, 1, 2);

		assertArrayEquals(new Object[] { "str4", "name" }, jedis.lrange(listKey, 0, -1).toArray());
		// 将value插入到值为pivot的前面或后面
		jedis.linsert(listKey, LIST_POSITION.BEFORE, "name", "world");

		assertArrayEquals(new Object[] { "str4", "world", "name" }, jedis.lrange(listKey, 0, -1).toArray());

		// 从source列表右边弹出一个元素添加到destistion列表的左边
		jedis.rpoplpush(listKey, "list-dest");
		jedis.rpoplpush(listKey, "list-dest");
		assertArrayEquals(new Object[] { "str4" }, jedis.lrange(listKey, 0, -1).toArray());
		assertArrayEquals(new Object[] { "world", "name" }, jedis.lrange("list-dest", 0, -1).toArray());
	}

	@Test
	public void testList() {
		String listKey = "list-key1";
		Jedis jedis = getJedis();
		jedis.lpush(listKey, "list-val1");
		jedis.rpush(listKey, "list-val2");

		List<String> list = jedis.lrange(listKey, 0, -1);
		assertArrayEquals(new Object[] { "list-val1", "list-val2" }, list.toArray());

		jedis.rpop(listKey);
		assertEquals(Long.valueOf(1), jedis.llen(listKey));
		assertEquals("list-val1", jedis.lindex(listKey, 0));
	}

	@Test
	public void testHash2() {
		Jedis jedis = getJedis();
		String hashKey = "hash-key-2";
		// 增加数字的值
		jedis.hset(hashKey, "hincrby", "6");

		assertEquals("6", jedis.hget(hashKey, "hincrby"));
		jedis.hincrBy(hashKey, "hincrby", 2);
		assertEquals("8", jedis.hget(hashKey, "hincrby"));

		jedis.close();
	}

	// hash(散列表)(hmset,hmget)
	@Test
	public void testHash1() {
		Object[] expArr = { "val1", "val2" };

		Set<String> expKeys = new HashSet<>();
		expKeys.add("key-hset");
		expKeys.add("key1");
		expKeys.add("key2");

		String hashKey = "test-hash-key";
		Jedis jedis = getJedis();
		// hmset
		Map<String, String> map = new HashMap<>();
		map.put("key1", "val1");
		map.put("key2", "val2");
		jedis.hmset(hashKey, map);

		List<String> list = jedis.hmget(hashKey, "key1", "key2");
		assertArrayEquals(expArr, list.toArray());
		// hgetall
		Map<String, String> map2 = jedis.hgetAll(hashKey);

		assertEquals(map, map2);

		// 设置获取字典元素的键值
		jedis.hset(hashKey, "key-hset", "val-hset");
		// hkeys
		Set<String> keys = jedis.hkeys(hashKey);

		assertEquals(expKeys, keys);
		assertEquals("val-hset", jedis.hget(hashKey, "key-hset"));

		assertTrue(jedis.hexists(hashKey, "key-hset"));
		// 判断是否存在，不存在赋值
		jedis.hsetnx(hashKey, "key-hset", "val-hsetnx");
		jedis.hsetnx(hashKey, "key-hsetnx", "val-hsetnx");

		assertEquals("val-hset", jedis.hget(hashKey, "key-hset"));
		assertEquals("val-hsetnx", jedis.hget(hashKey, "key-hsetnx"));

		jedis.hdel(hashKey, "key-hsetnx");
		assertFalse("已删除！", jedis.hexists(hashKey, "key-hsetnx"));

		assertEquals(Long.valueOf(3), jedis.hlen(hashKey));
		jedis.close();

	}

	// setnx(SET if Not eXists)
	@Test
	public void testSetnx() {
		String key = "test-setnx-key";
		Jedis jedis = getJedis();
		jedis.setnx("test-setnx-key", "test1");
		assertEquals("test1", jedis.get(key));
		jedis.setnx(key, "test2");
		assertEquals("test1", jedis.get(key));
		jedis.close();
	}

	// 操作多个键值
	@Test
	public void testMultiKeys() {
		Object[] vals = { "val1", "val2" };
		Jedis jedis = getJedis();
		jedis.mset("test-key1", "val1", "test-key2", "val2");

		List<String> strs = jedis.mget("test-key1", "test-key2");

		assertArrayEquals(vals, strs.toArray());

		jedis.close();
	}

	// 当字符串为数字时的操作
	@Test
	public void testNumber() {
		String key = "key-test-number";
		Jedis jedis = getJedis();
		jedis.set(key, "10");
		jedis.incr(key);
		assertEquals("11", jedis.get(key));
		// 以指定数值增长
		jedis.incrBy(key, 2);
		assertEquals("13", jedis.get(key));

		jedis.decr(key);
		assertEquals("12", jedis.get(key));

		jedis.decrBy(key, 3);
		assertEquals("9", jedis.get(key));

		// 增加浮点数
		jedis.incrByFloat(key, 1.5D);
		assertEquals("10.5", jedis.get(key));

		jedis.close();
	}

	// 字符串操作
	@Test
	public void testStringOp() throws InterruptedException {
		String key = "key-test-str-op";
		String val = "val-test-str-op";
		Jedis jedis = getJedis();
		// set,get
		jedis.set(key, val);
		String val1 = jedis.get(key);
		assertEquals(val, val1);

		// strlen
		long len = jedis.strlen(key);

		assertEquals((long) val.length(), len);
		// append
		jedis.append(key, "-append");

		assertEquals("val-test-str-op-append", jedis.get(key));

		// ttl
		jedis.ttl(key);
		assertEquals(new Long(-1L), jedis.ttl(key));

		// expire,exists
		jedis.expire(key, 3);
		assertTrue(jedis.exists(key));
		TimeUnit.SECONDS.sleep(3);
		assertFalse("key:" + key + "已过期！", jedis.exists(key));

		jedis.close();
	}

	@AfterClass
	public static void testAfter() {
		Jedis jedis = getJedis();
		jedis.flushDB();
		jedis.close();
	}
}
