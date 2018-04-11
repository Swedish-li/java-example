package com.lrs.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class TestRedisBase {
    protected Jedis jedis;

    @Before
    public void before() {
        jedis = new Jedis("127.0.0.1", 6379);
    }

    @Test
    public void simpleTest() {
        jedis.set("key1", "value1");
    }


}
