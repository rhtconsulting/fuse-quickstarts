package com.redhat.consulting.fusequickstarts.karaf.amq.route;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.expression.EvaluationException;
import org.apache.camel.api.management.ManagedOperation;
import org.apache.camel.api.management.ManagedResource;
import org.apache.camel.spi.IdempotentRepository;
import org.apache.camel.support.ServiceSupport;

/**
 * This is an Idempotent Repository for use with the Camel Idempotent Consumer.
 * It is based on a Guava Cache with a Time Expiration.
 */
@ManagedResource(description = "Memory based idempotent repository using Guava Cache")
public class GuavaIdempotentRepository extends ServiceSupport implements IdempotentRepository<String> {

    private Cache<String, String> cache;

    public GuavaIdempotentRepository(int milliseconds) {
        cache = CacheBuilder.newBuilder().expireAfterWrite(milliseconds, TimeUnit.MILLISECONDS).build();
    }

    /**
     * Creates a new memory based repository using Guava with the specified Key
     * timeout.
     *
     * @param cacheSize the cache size
     */
    public static IdempotentRepository<String> guavaIdempotentRepository(int milliseconds) {
        return new GuavaIdempotentRepository(milliseconds);
    }

    @ManagedOperation(description = "Adds the key to the store")
    public boolean add(String key) {
        if(this.contains(key)){
            return false;
        } else {
            this.cache.put(key, key);
            return true;
        }
    }

    public boolean confirm(String key) {
        // noop
        return true;
    }

    @ManagedOperation(description = "Does the store contain the given key")
    public boolean contains(String key) {
        try {
            this.cache.get(key, new Callable<String>() {
                public String call() throws Exception {
                    // Throw an Exception so that we do not create a new Entry for the key since it was not found.
                    throw new ExecutionException(new IllegalArgumentException());
                }
            });
        } catch (ExecutionException e) {
            return false;
        }
        
        return true;
    }

    @ManagedOperation(description = "Remove the key from the store")
    public boolean remove(String key) {
        this.cache.invalidate(key);
        return true;
    }

    @Override
    protected void doStart() throws Exception {
        if (this.cache == null) {
            this.cache = CacheBuilder.newBuilder().expireAfterWrite(60000, TimeUnit.MILLISECONDS).build();
        }
    }

    @Override
    protected void doStop() throws Exception {
        this.cache.invalidateAll();
        this.cache.cleanUp();

    }

    @ManagedOperation(description = "Clear the store")
    public void clear() {
        this.cache.invalidateAll();
    }

}
