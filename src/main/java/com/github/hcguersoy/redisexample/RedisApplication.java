package com.github.hcguersoy.redisexample;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

/**
 * A simple application to show usage of Spring Data Redis.
 * 
 * @author hcguersoy
 *
 */
@Slf4j
@Import(RedisApplicationConfiguration.class)
public class RedisApplication implements CommandLineRunner {
	    
    private static final int MEASURE_COUNT = 10000;
	@Autowired
    RedisSimpleRepository<String, MeasureData> repository;
    
    @Override
    public void run(String... args) throws Exception {
    	
    	val rnd = new Random();
    	
    	//generate some data
    	log.info("Creating some test data!");
    	for (int i = 0; i < MEASURE_COUNT; i++) {
    		repository.put(new MeasureData(UUID.randomUUID().toString(), "gauge1", new BigInteger(32, rnd), new Date()));
		}
    	
    	//print data for n elements
    	List<String> keys = new ArrayList<String>(repository.getHashKeys());    	    	
    	for (int i = 0; i < 10; i++) {
			val measureData = repository.get(keys.get(rnd.nextInt(keys.size())));
			log.info("Found measure data: {}", measureData);
		}
    }

    /**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

}
