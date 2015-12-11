package com.redhat.consulting.fusequickstarts.karaf.msf;

import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.CamelContext;
import org.apache.commons.lang.StringUtils;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouteFactory implements ManagedServiceFactory {

    // Logger
    private Logger LOG = LoggerFactory.getLogger(RouteFactory.class);

    // OSGi & Camel Properties
    private BundleContext bundleContext;
    private CamelContext camelContext;
    private ServiceRegistration registration;
    private ServiceTracker tracker;
    private String configurationPid;

    // Currently Running Routes
    private Map<String, RouteDispatcher> routeDispatchers = Collections
        .synchronizedMap(new HashMap<String, RouteDispatcher>());

    /**
     * Init Method
     */
    @SuppressWarnings("unchecked")
    public void init() {
        LOG.info("Starting " + this.getName());

        // Create Property Dictionary
        Dictionary servProps = new Properties();
        servProps.put(Constants.SERVICE_PID, configurationPid);

        // Register Factory Service
        registration = bundleContext.registerService(ManagedServiceFactory.class.getName(), this, servProps);

        // Start Configuration Tracker
        tracker = new ServiceTracker(bundleContext, ConfigurationAdmin.class.getName(), null);
        tracker.open();

        LOG.info("Started " + this.getName());
    }

    /**
     * Get the Name for the Factory
     */
    public String getName() {
        return configurationPid;
    }

    /**
     * PID is Updated
     */
    public void updated(String pid, Dictionary dict) throws ConfigurationException {
        LOG.info("MSF Route Update: pid={} props={}", pid, dict.toString());

        // Create New Route Dispatcher
        RouteDispatcher routeDispatcher = null;

        // Find Previous Route and Destroy it
        deleted(pid);

        // Verify dictionary contents before applying them to Hello
        if (dict.get(DictionaryConstants.HELLO_GREETING) != null
            && !StringUtils.isEmpty(dict.get(DictionaryConstants.HELLO_GREETING).toString())) {
            LOG.info("HELLO_GREETING set to " + dict.get(DictionaryConstants.HELLO_GREETING));
        } else {
            throw new IllegalArgumentException("Missing HELLO_GREETING");
        }

        if (dict.get(DictionaryConstants.HELLO_NAME) != null
            && !StringUtils.isEmpty(dict.get(DictionaryConstants.HELLO_NAME).toString())) {
            LOG.info("HELLO_NAME set to " + dict.get(DictionaryConstants.HELLO_NAME));
        } else {
            throw new IllegalArgumentException("Missing HELLO_NAME");
        }
        
        if (dict.get(DictionaryConstants.ROUTE_ID) != null
            && !StringUtils.isEmpty(dict.get(DictionaryConstants.ROUTE_ID).toString())) {
            LOG.info("ROUTE_ID set to " + dict.get(DictionaryConstants.ROUTE_ID));
        } else {
            throw new IllegalArgumentException("Missing HELLO_NAME");
        }

        // Configuration was verified above, now create dispatcher.
        routeDispatcher = new RouteDispatcher();
        routeDispatcher.setCamelContext(camelContext);
        routeDispatcher.setRouteId(dict.get(DictionaryConstants.ROUTE_ID).toString());
        routeDispatcher.setGreeting(dict.get(DictionaryConstants.HELLO_GREETING).toString());
        routeDispatcher.setName(dict.get(DictionaryConstants.HELLO_NAME).toString());

        // Add Route Dispatcher to List & Start
        routeDispatchers.put(pid, routeDispatcher);
        LOG.debug("Start the Route Dispatcher...");
        routeDispatcher.start();
    }

    /**
     * PID is Deleted
     */
    public void deleted(String pid) {

        if (routeDispatchers.containsKey(pid)) {
            RouteDispatcher dispatcher = routeDispatchers.get(pid);

            if (dispatcher != null) {
                destroyDispatcher(dispatcher);
            }
            routeDispatchers.remove(pid);
        }

        LOG.info("Deleted " + pid);
    }

    private void destroyDispatcher(RouteDispatcher dispatcher) {
        dispatcher.stop();
    }

    /**
     * Stop the Factory Service
     */
    public void destroy() {
        LOG.info("Destroying RouteFactory {}", configurationPid);
        registration.unregister();
        tracker.close();
    }

    // Wired in blueprint.
    public void setConfigurationPid(String configurationPid) {
        this.configurationPid = configurationPid;
    }

    // Wired in blueprint.
    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    // Wired in blueprint.
    public void setCamelContext(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

}
