package dev.boot.configuration;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class UpdateCacheConfig {
    @CacheEvict(cacheNames = "branch", allEntries = true)
    @Scheduled(cron = "0 0 */8 * * *")
    public void evictBranchCache() {}
}
