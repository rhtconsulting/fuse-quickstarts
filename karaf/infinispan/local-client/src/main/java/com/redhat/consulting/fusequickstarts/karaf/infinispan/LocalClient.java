package com.redhat.consulting.fusequickstarts.karaf.infinispan;

import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalClient {
    
    private static final transient Logger LOG = LoggerFactory.getLogger(LocalClient.class);
    private EmbeddedCacheManager cacheManager;

    public EmbeddedCacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(EmbeddedCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Object process(String operation, String key, String value) {
        LOG.info("Local " + operation + "{} : {} ",  key, value);
        if ("PUT".equals(operation)) {
            return cacheManager.getCache().put(key, value);
        } else  if ("GET".equals(operation)) {
            return cacheManager.getCache().get(key);
        } else if ("DELETE".equals(operation)) {
            return cacheManager.getCache().remove(key);
        } else throw new UnsupportedOperationException(operation);
    }
}
