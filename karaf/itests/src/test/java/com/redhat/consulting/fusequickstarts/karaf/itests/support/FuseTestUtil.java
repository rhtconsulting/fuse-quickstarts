package com.redhat.consulting.fusequickstarts.karaf.itests.support;

import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.configureConsole;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.editConfigurationFilePut;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.felix.service.command.CommandProcessor;
import org.apache.felix.service.command.CommandSession;
import org.junit.Assert;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.karaf.options.KarafDistributionOption;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities for Configuring a Fuse Test with Pax Exam.
 * @author Bryan Saunders <bsaunder@redhat.com>
 *
 */
public class FuseTestUtil {
    private static Logger log = LoggerFactory.getLogger(FuseTestUtil.class);

    // Set Maven Coordinates for Fuse Zip
    public static final String GROUP_ID = "org.jboss.fuse";
    public static final String ARTIFACT_ID = "jboss-fuse";
    public static final String VERSION = "6.3.0.GA";

    // Adjust ports so they don't conflict with other tests/running versions of Fuse
    public static final String RMI_SERVER_PORT = "44445";
    public static final String HTTP_PORT = "9081";
    public static final String RMI_REG_PORT = "1100";
    public static final String AMQ_PORT = "62626";

    public static final Long COMMAND_TIMEOUT = 10000L;
    public static final Long DEFAULT_TIMEOUT = 20000L;
    public static final Long SERVICE_TIMEOUT = 30000L;

    private FuseTestUtil() {
        super();
    }

    @SuppressWarnings("rawtypes")
    public static String explode(Dictionary dictionary) {
        Enumeration keys = dictionary.keys();
        StringBuffer result = new StringBuffer();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            result.append(String.format("%s = %s", key, dictionary.get(key)));
            if (keys.hasMoreElements()) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    /**
     * Configured the Fuse container for the Tests. This should install all of the Bundles/Features/Configurations
     * needed for testing.
     * 
     * @return
     */
    public static Option[] container() {
        return new Option[] {
                karafDistributionConfiguration()
                        // Extract/Install Fuse
                        .frameworkUrl(maven().groupId(GROUP_ID).artifactId(ARTIFACT_ID).version(VERSION).type("zip"))
                        .karafVersion("2.3.0").useDeployFolder(false).name("JBoss Fuse")
                        .unpackDirectory(new File("target/paxexam/unpack")),
                configureConsole().ignoreLocalConsole(),

                // Edit Fuse Configurations
                editConfigurationFilePut("etc/config.properties", "karaf.startup.message",
                        "Loading Fabric from: ${karaf.home}"),
                editConfigurationFilePut("etc/org.ops4j.pax.web.cfg", "org.osgi.service.http.port", HTTP_PORT),
                editConfigurationFilePut("etc/system.properties", "activemq.port", AMQ_PORT),
                editConfigurationFilePut("etc/org.apache.karaf.management.cfg", "rmiRegistryPort", RMI_REG_PORT),
                editConfigurationFilePut("etc/org.apache.karaf.management.cfg", "rmiServerPort", RMI_SERVER_PORT),
                editConfigurationFilePut("etc/users.properties", "admin",
                        "admin,admin,manager,viewer,Monitor, Operator, Maintainer, Deployer, Auditor, Administrator, SuperUser"),

                // Install Required Features
                features("camel-blueprint", "camel-core", "camel-test", "camel-jasypt", "jasypt-encryption", "camel-http4", "camel-restlet", "camel-jackson"),

                // Install Configuration File(s) for Testing
                KarafDistributionOption.replaceConfigurationFile(
                        "etc/com.redhat.consulting.fusequickstarts.karaf.properties.cfg", new File(
                                "src/test/resources/com.redhat.consulting.fusequickstarts.karaf.properties.cfg")),
                KarafDistributionOption.replaceConfigurationFile(
                        "etc/com.redhat.consulting.fusequickstarts.karaf.properties.enc.cfg", new File(
                                "src/test/resources/com.redhat.consulting.fusequickstarts.karaf.properties.enc.cfg")),

                // Install Bundles for Testing
                mavenBundle("com.redhat.consulting.fusequickstarts.karaf", "properties").versionAsInProject(),
                mavenBundle("com.redhat.consulting.fusequickstarts.karaf", "properties-encryption").versionAsInProject(),
                mavenBundle("com.redhat.consulting.fusequickstarts.karaf", "rest-dsl").versionAsInProject(),

                // Set Logging Level
                logLevel(LogLevelOption.LogLevel.INFO),

                // Uncomment to keep Exploded Fuse Installation Directory after Testing
                // keepRuntimeFolder(),

        };
    };

    @SuppressWarnings("rawtypes")
    public static Collection<ServiceReference> asCollection(ServiceReference[] references) {
        return references != null ? Arrays.asList(references) : Collections.<ServiceReference> emptyList();
    }

    /**
     * Get an OSGi Service
     * @param type
     * @param timeout
     * @param bundleContext
     * @return
     */
    public static <T> T getOsgiService(Class<T> type, long timeout, BundleContext bundleContext) {
        return getOsgiService(type, null, timeout, bundleContext);
    }

    /**
     * Get an OSGi Service
     * @param type
     * @param bundleContext
     * @return
     */
    public static <T> T getOsgiService(Class<T> type, BundleContext bundleContext) {
        return getOsgiService(type, null, SERVICE_TIMEOUT, bundleContext);
    }

    /**
     * Get an OSGi Service
     * @param type
     * @param filter
     * @param timeout
     * @param bundleContext
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> T getOsgiService(Class<T> type, String filter, long timeout, BundleContext bundleContext) {
        ServiceTracker tracker = null;
        try {
            String flt;
            if (filter != null) {
                if (filter.startsWith("(")) {
                    flt = "(&(" + Constants.OBJECTCLASS + "=" + type.getName() + ")" + filter + ")";
                } else {
                    flt = "(&(" + Constants.OBJECTCLASS + "=" + type.getName() + ")(" + filter + "))";
                }
            } else {
                flt = "(" + Constants.OBJECTCLASS + "=" + type.getName() + ")";
            }
            Filter osgiFilter = FrameworkUtil.createFilter(flt);
            tracker = new ServiceTracker(bundleContext, osgiFilter, null);
            tracker.open(true);
            // Note that the tracker is not closed to keep the reference
            // This is buggy, as the service reference may change i think
            Object svc = type.cast(tracker.waitForService(timeout));
            if (svc == null) {

                for (ServiceReference ref : FuseTestUtil
                        .asCollection(bundleContext.getAllServiceReferences(null, null))) {
                    System.err.println("ServiceReference: " + ref);
                }

                for (ServiceReference ref : FuseTestUtil.asCollection(bundleContext.getAllServiceReferences(null, flt))) {
                    System.err.println("Filtered ServiceReference: " + ref);
                }

                throw new RuntimeException("Gave up waiting for service " + flt);
            }
            return type.cast(svc);
        } catch (InvalidSyntaxException e) {
            throw new IllegalArgumentException("Invalid filter", e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Asserts that the Given Bundle is Active. 
     * @param bundleName
     * @param bundleContext
     */
    public static void assertBundleActive(String bundleName, BundleContext bundleContext) {
        log.info("Asserting {} is active", bundleName);
        Bundle[] bundles = bundleContext.getBundles();
        boolean found = false;
        boolean active = false;

        for (Bundle bundle : bundles) {
            if (bundle.getSymbolicName().equals(bundleName)) {
                found = true;
                if (bundle.getState() == Bundle.ACTIVE) {
                    log.info("  ACTIVE");
                    active = true;
                } else {
                    log.info("  NOT ACTIVE");
                }
                break;
            }
        }
        Assert.assertTrue(bundleName + " not found in container", found);
        Assert.assertTrue(bundleName + " not active", active);
    }

    /**
     * Executes a Karaf Shell Command
     * @param command
     * @param executor
     * @param bundleContext
     * @return
     */
    public static String executeCommand(final String command, ExecutorService executor, BundleContext bundleContext) {
        return executeCommand(command, COMMAND_TIMEOUT, false, executor, bundleContext);
    }

    /**
     * Executes a Karaf Shell Command
     * @param command
     * @param timeout
     * @param silent
     * @param executor
     * @param bundleContext
     * @return
     */
    public static String executeCommand(final String command, final Long timeout, final Boolean silent,
            ExecutorService executor, BundleContext bundleContext) {
        String response;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        final CommandProcessor commandProcessor = getOsgiService(CommandProcessor.class, bundleContext);
        final CommandSession commandSession = commandProcessor.createSession(System.in, printStream, System.err);
        FutureTask<String> commandFuture = new FutureTask<String>(new Callable<String>() {
            public String call() {
                try {
                    if (!silent) {
                        System.err.println(command);
                    }
                    commandSession.execute(command);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
                printStream.flush();
                return byteArrayOutputStream.toString();
            }
        });

        try {
            executor.submit(commandFuture);
            response = commandFuture.get(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            response = "SHELL COMMAND TIMED OUT: ";
        }

        return response;
    }
}
