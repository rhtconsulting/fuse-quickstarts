package com.redhat.consulting.fusequickstarts.karaf.itests.rest.dsl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExamServer;

import com.redhat.consulting.fusequickstarts.karaf.itests.support.FuseTestUtil;

public class RestDslRouteIT {

    /**
     * Run PaxExam in Server Mode. Starts a New Container for Each Test.
     */
    @Rule
    public PaxExamServer exam = new PaxExamServer();

    /**
     * Configure the Container
     */
    @Configuration
    public Option[] config() {
        return FuseTestUtil.container();
    }

    /**
     * Tests the GET Rest Endpoint
     * 
     * @throws Exception
     */
    @Test
    public void testRestGet() throws Exception {

        // Give camel a little time to start listening
        Thread.sleep(1000);

        // Configure HTTP Client
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost:8182/rest/message");

        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                // Get/Check Status Code
                int status = response.getStatusLine().getStatusCode();
                Assert.assertEquals("Invalid Status Code", 200, status);

                // Get/Check Response Body
                HttpEntity entity = response.getEntity();
                Assert.assertNotNull("Response Entity was Null", entity);

                // Return Body as a String
                return EntityUtils.toString(entity);
            }

        };

        // Execute Request and Get Response as a String
        String responseBody = httpclient.execute(httpget, responseHandler);

        // Validate Response
        Assert.assertEquals("Response is Incorrect",
                "{\"message\":\"REST is Awesome\",\"to\":\"User\",\"from\":\"Developer\"}", responseBody);
    }

    /**
     * Tests the POST Rest Endpoint
     * 
     * @throws Exception
     */
    @Test
    public void testRestPost() throws Exception {

        // Give camel a little time to start listening
        Thread.sleep(1000);

        // Configure HTTP Client
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://localhost:8182/rest/message");

        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                // Get/Check Status Code
                int status = response.getStatusLine().getStatusCode();
                Assert.assertEquals("Invalid Status Code", 201, status);

                // Get/Check Response Body
                HttpEntity entity = response.getEntity();
                Assert.assertNotNull("Response Entity was Null", entity);

                // Return Body as a String
                return EntityUtils.toString(entity);
            }

        };

        // Execute Request and Get Response as a String
        String responseBody = httpclient.execute(httppost, responseHandler);

        // Validate Response
        Assert.assertEquals("Response is Incorrect", "\"\"", responseBody);
    }

}
