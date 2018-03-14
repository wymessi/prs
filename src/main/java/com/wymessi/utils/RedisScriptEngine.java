package com.wymessi.utils;

import redis.clients.jedis.Jedis;

public class RedisScriptEngine {

	/**
	 * 添加key value
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		Jedis jedis = RedisUtils.getJedis();
		jedis.set(key, value);
		RedisUtils.returnResource(jedis);
	}
}
