package com.redhat.consulting.fusequickstarts.karaf.infinispan;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.commons.api.BasicCacheContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteClient {

    private static final transient Logger LOG = LoggerFactory.getLogger(RemoteClient.class);

    private BasicCacheContainer cacheManager;
    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void start() {
        LOG.info("Creating client");
        Configuration config = new ConfigurationBuilder()
            .classLoader(Thread.currentThread().getContextClassLoader()).addServers(getHost()).build();
        cacheManager = new RemoteCacheManager(config, true);
    }

    public void stop() {
        if (cacheManager != null) {
            cacheManager.stop();
        }
    }

    public Object process(String operation, String key, String value) {
        LOG.info("Remote " + operation + " {} : {} ", key, value);
        if ("PUT".equals(operation)) {
            return cacheManager.getCache().put(key, value);
        } else if ("GET".equals(operation)) {
            return cacheManager.getCache().get(key);
        } else if ("DELETE".equals(operation)) {
            return cacheManager.getCache().remove(key);
        } else
            throw new UnsupportedOperationException(operation);
    }

}
