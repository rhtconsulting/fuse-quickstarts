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

@ManagedResource(description = "Memory based idempotent repository using Guava Cache")
public class GuavaIdempotentRepository extends ServiceSupport implements IdempotentRepository<String> {

    private Cache<String, String> cache;

    public GuavaIdempotentRepository(int milliseconds) {
        cache = CacheBuilder.newBuilder().expireAfterWrite(milliseconds, TimeUnit.MILLISECONDS).build();
        System.out.println("<<<>>> Cache Created");
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
            System.out.println("<<<>>> Key Found, Skipping Add: " + key);
            return false;
        } else {
            this.cache.put(key, key);
            System.out.println("<<<>>> Key Added: " + key);
            return true;
        }
    }

    public boolean confirm(String key) {
        // noop
        return true;
    }

    @ManagedOperation(description = "Does the store contain the given key")
    public boolean contains(String key) {
        System.out.println("<<<>>> Searching for Key: " + key);
        try {
            this.cache.get(key, new Callable<String>() {
                public String call() throws Exception {
                    System.out.println("<<<>>> In Callable");
                    throw new ExecutionException(new IllegalArgumentException());
                }
            });
        } catch (ExecutionException e) {
            System.out.println("<<<>>> Does Not Contain Key: " + key);
            return false;
        }
        
        System.out.println("<<<>>> Key Found: " + key);
        return true;
    }

    @ManagedOperation(description = "Remove the key from the store")
    public boolean remove(String key) {
        System.out.println("<<<>>> Removed Key: " + key);
        this.cache.invalidate(key);
        return true;
    }

    @Override
    protected void doStart() throws Exception {
        System.out.println("<<<>>> Do Start");
        if (this.cache == null) {
            this.cache = CacheBuilder.newBuilder().expireAfterWrite(60000, TimeUnit.MILLISECONDS).build();
        }
    }

    @Override
    protected void doStop() throws Exception {
        System.out.println("<<<>>> Do Stop");
        this.cache.invalidateAll();
        this.cache.cleanUp();

    }

    @ManagedOperation(description = "Clear the store")
    public void clear() {
        System.out.println("<<<>>> Clearing Cache");
        this.cache.invalidateAll();
    }

}
