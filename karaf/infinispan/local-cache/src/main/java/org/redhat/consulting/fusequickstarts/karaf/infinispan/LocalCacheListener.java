package org.redhat.consulting.fusequickstarts.karaf.infinispan;

import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryActivated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryInvalidated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryLoaded;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryPassivated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryVisited;
import org.infinispan.notifications.cachelistener.event.CacheEntryEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listener(sync = true)
public class LocalCacheListener {
    
    private static final transient Logger LOG = LoggerFactory.getLogger(LocalCacheListener.class);
    
    private EmbeddedCacheManager cacheManager;

    public void start() {
        LOG.info("Registering event listener");
        cacheManager.getCache().addListener(this);
        LOG.info("Registered event listener");
    }

    public void stop() {
        LOG.info("Removing event listener");
        cacheManager.getCache().removeListener(this);
        LOG.info("Removed event listener");
    }

    @CacheEntryActivated
    @CacheEntryCreated
    @CacheEntryInvalidated
    @CacheEntryLoaded
    @CacheEntryModified
    @CacheEntryPassivated
    @CacheEntryRemoved
    @CacheEntryVisited
    public void processEvent(CacheEntryEvent<Object, Object> event) {
        if (event.isPre()) {
            LOG.info("Infinispan event received: " + event.getType() + " with key: " + event.getKey());
        }
    }
    
    public EmbeddedCacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(EmbeddedCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
