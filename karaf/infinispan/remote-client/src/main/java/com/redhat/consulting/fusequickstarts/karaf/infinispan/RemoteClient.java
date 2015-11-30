package com.redhat.consulting.fusequickstarts.karaf.infinispan;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.api.BasicCacheContainer;

public class RemoteClient {

    //private static final transient Logger LOG = LoggerFactory.getLogger(RemoteClient.class);

    private BasicCacheContainer cacheManager;
    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void start() {
        //LOG.info("Creating client");
        //ConfigurationBuilder builder = new ConfigurationBuilder();
        //builder.addServer().host("localhost");
        //cacheManager = new RemoteCacheManager(builder.build());
        cacheManager = new RemoteCacheManager("localhost", 11222);
    }

    public void stop() {
        if (cacheManager != null) {
            cacheManager.stop();
        }
    }

    public Object process(String operation, String key, String value) {
        //LOG.info("Remote " + operation + " {} : {} ", key, value);
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
