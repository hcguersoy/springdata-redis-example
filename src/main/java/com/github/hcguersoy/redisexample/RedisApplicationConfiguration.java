package com.github.hcguersoy.redisexample;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Configuration bean for a simple redis application.
 * This configuration asumes that redis runs on localhost
 * and on default port (6379).
 * 
 * @author guersoy
 */
@Configuration
@Slf4j
public class RedisApplicationConfiguration {
    
	/**
	 * We use Jedis in this example to create a conection factory.
	 * This is needed by the template.
	 * 
	 * @return A jedis connection factory
	 */
	@Bean
    JedisConnectionFactory connectionFactory() {
		log.info("returning connection factory");
        return new org.springframework.data.redis.connection.jedis.JedisConnectionFactory();
    }	
    
    /**
     * Creates a Spring Template for the Redis access.
     * In this example we use a String-Serializer for the key and hashkey,
     * a Json-Serialiser for the bean.
     *  
     * @param connectionFactory a ConnectionFactory.
     * @return The template
     */
    @Bean
    RedisTemplate<String, MeasureData> template(RedisConnectionFactory connectionFactory) {
    	RedisTemplate<String, MeasureData> template = new RedisTemplate<String, MeasureData>();
    	template.setKeySerializer(new StringRedisSerializer());
    	template.setHashKeySerializer(new StringRedisSerializer());
    	template.setHashValueSerializer(new JacksonJsonRedisSerializer<MeasureData>(MeasureData.class));
    	template.setConnectionFactory(connectionFactory);
    	log.info("returning template");
    	return template;
    }    
    
    /**
     * This initialises a very simple Repository.
     * 
     * @param template
     * @return a repository object 
     */
    @Bean
    RedisSimpleRepository<String, MeasureData> repository(RedisTemplate<String, MeasureData> template) {
    	log.info("Returning repository...");
    	return new RedisMeasurementDataRepository(template);
    }
}
