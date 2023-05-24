package com.itsgood.ru.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@ConfigurationProperties("application-cache.yaml")
public class CacheConfiguration {

    @Bean
    public CacheManager cacheManager() {
//        CaffeineCacheManager cacheManager = new CaffeineCacheManager("role", "contract", "item", "category", "address", "payment", "delivery");
//        cacheManager.setCaffeine(cacheProperties());
//        return cacheManager;
        return new CaffeineCacheManager();
    }

//    public Caffeine<Object, Object> cacheProperties() {
//        return Caffeine.newBuilder()
//                .initialCapacity(10).maximumSize(20)
//                .expireAfterAccess(10, TimeUnit.SECONDS)
//                .weakKeys().recordStats();
//    }
}
