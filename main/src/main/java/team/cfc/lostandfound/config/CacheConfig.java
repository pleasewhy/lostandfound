package team.cfc.lostandfound.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.concurrent.ConcurrentMap;

//@Configuration
//@EnableCaching
public class CacheConfig {

//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory factory) {
//        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig().
//                entryTtl(Duration.ofSeconds(2));
//        RedisCacheManager redisCacheManager = RedisCacheManager.builder(factory).cacheDefaults(configuration).build();
//        return redisCacheManager;
//    }
}
