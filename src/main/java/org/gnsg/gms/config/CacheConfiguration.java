package org.gnsg.gms.config;

import io.github.jhipster.config.JHipsterProperties;
import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, org.gnsg.gms.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, org.gnsg.gms.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, org.gnsg.gms.domain.User.class.getName());
            createCache(cm, org.gnsg.gms.domain.Authority.class.getName());
            createCache(cm, org.gnsg.gms.domain.User.class.getName() + ".authorities");
            createCache(cm, org.gnsg.gms.domain.Program.class.getName());
            createCache(cm, org.gnsg.gms.domain.Program.class.getName() + ".sevadars");
            createCache(cm, org.gnsg.gms.domain.Task.class.getName());
            createCache(cm, org.gnsg.gms.domain.Sevadar.class.getName());
            createCache(cm, org.gnsg.gms.domain.Student.class.getName());
            createCache(cm, org.gnsg.gms.domain.Student.class.getName() + ".charges");
            createCache(cm, org.gnsg.gms.domain.Student.class.getName() + ".appliedCharges");
            createCache(cm, org.gnsg.gms.domain.Charge.class.getName());
            createCache(cm, org.gnsg.gms.domain.AppliedCharge.class.getName());
            createCache(cm, org.gnsg.gms.domain.AppliedCharge.class.getName() + ".students");
            createCache(cm, org.gnsg.gms.domain.Sevadar.class.getName() + ".programs");
            createCache(cm, org.gnsg.gms.domain.ASPath.class.getName());
            createCache(cm, org.gnsg.gms.domain.ASPath.class.getName() + ".pRouls");
            createCache(cm, org.gnsg.gms.domain.PRoul.class.getName());
            createCache(cm, org.gnsg.gms.domain.Expense.class.getName());
            createCache(cm, org.gnsg.gms.domain.Revenue.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }
}
