package com.github.hcguersoy.redisexample;

import java.util.Set;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Implementation of a very simple Spring Data Redis repository
 * to store measurement data.
 * 
 * @author hcguersoy
 *
 */
@Slf4j
@RequiredArgsConstructor
public class RedisMeasurementDataRepository implements RedisSimpleRepository<String, MeasureData> {
	
	@NonNull
	private RedisTemplate<String, MeasureData> template;

	@Override
	public void put(MeasureData dataObj) {
		log.debug("Will save {} to redis", dataObj);
		template.boundHashOps(MeasureData.KEY).put(dataObj.getMeasureId(), dataObj);
		log.debug("Saving done");
	}

	@Override
	public MeasureData get(String hashKey) {
		log.debug("Will retrieve data for hashkey {}", hashKey);
		return (MeasureData) template.boundHashOps(MeasureData.KEY).get(hashKey);

	}

	@Override
	public void delete(MeasureData dataObj) {
		log.debug("Will delete {}", dataObj);
		template.boundHashOps(MeasureData.KEY).delete(dataObj.getMeasureId());
		log.debug("Deleted");
	}

	@Override
	public Set<String> getHashKeys() {
		log.debug("Will retrieve all hashkeys");
		BoundHashOperations<String, String, MeasureData> ops = template.boundHashOps(MeasureData.KEY);
	    return ops.keys();
	}
}
