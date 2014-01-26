package com.github.hcguersoy.redisexample;

import java.util.Set;

/**
 * A very simple repository definition for Spring Data Redis.
 * 
 * @author hcguersoy
 * 
 */
public interface RedisSimpleRepository<HK, V> {

	void put(V dataObj);

	V get(HK hashKey);

	void delete(V dataObj);

	Set<HK> getHashKeys();
	
}
