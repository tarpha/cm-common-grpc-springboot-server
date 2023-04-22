package kbank.cm.common.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfiguration {
    // @Bean
    // public CacheManager cacheManager() {
    //     SimpleCacheManager cacheManager = new SimpleCacheManager();
    //     Cache booksCache = new ConcurrentMapCache("grpc");
    //     cacheManager.setCaches(Arrays.asList(booksCache));
    //     return cacheManager;
    // }
  
    @Bean("hashKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new HashKeyGenerator();
    }
    
}
