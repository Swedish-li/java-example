package com.lrs.redis;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestRedisBase {
	protected Jedis jedis;

	@Before
	public void before() {
		jedis = new Jedis("192.168.99.100", 32769);
	}

	@Test
	public void simpleTest() {
		jedis.set("key1", "value1");
	}
}
