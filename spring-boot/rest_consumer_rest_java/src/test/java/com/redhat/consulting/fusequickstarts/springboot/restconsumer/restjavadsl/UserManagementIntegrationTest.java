package com.redhat.consulting.fusequickstarts.springboot.restconsumer.restjavadsl;

import org.apache.camel.CamelContext;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.*;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@UseAdviceWith
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UserManagementIntegrationTest {

    private static final Set<String> INCLUDED_ROUTE_GROUPS = Collections.singleton("userManagement");

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        camelContext.getRouteDefinitions()
                .stream()
                // Set autoStartup to true on routes within the groups specified by INCLUDED_ROUTE_GROUPS
                // Note: You could similarly filter on routeDefinition.getId() for the route ID.
                .filter(routeDefinition -> INCLUDED_ROUTE_GROUPS.contains(routeDefinition.getGroup()))
                .forEach(routeDefinition -> routeDefinition.autoStartup(true));

        // Start with empty user list (not restarting context between tests; per @DirtiesContext above)
        userService.getUsersById().clear();

        // Start context (not auto-started due to @UseAdviceWith)
        camelContext.start();
    }

    @Test
    public void testListUsers_ZeroUsers() {
        assumeTrue("starting user map should be empty", userService.getUsersById().isEmpty());

        RequestEntity<Void> requestEntity = RequestEntity
                .get(fromPath("/camel-rest/user").build().toUri())
                .build();

        ResponseEntity<List<User>> resultEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<User>>() {});

        assertThat(resultEntity, notNullValue());
        assertThat(resultEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(resultEntity.getBody(), emptyCollectionOf(User.class));
    }

    @Test
    public void testListUsers_SomeUsers() {
        User testUser = new User(10, "test user");
        userService.createUser(testUser);

        assumeFalse("starting user map should not be empty", userService.getUsersById().isEmpty());

        RequestEntity<Void> requestEntity = RequestEntity
                .get(fromPath("/camel-rest/user").build().toUri())
                .build();

        ResponseEntity<List<User>> resultEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<User>>() {});

        assertThat(resultEntity, notNullValue());
        assertThat(resultEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(resultEntity.getBody(), contains(samePropertyValuesAs(testUser)));
    }

    @Test
    public void testGetUser_Success() {
        User testUser = new User(10, "test user");
        userService.createUser(testUser);

        assumeFalse("starting user map should not be empty", userService.getUsersById().isEmpty());

        RequestEntity<Void> requestEntity = RequestEntity
                .get(fromPath("/camel-rest/user/10").build().toUri())
                .build();

        ResponseEntity<User> resultEntity = restTemplate.exchange(requestEntity, User.class);

        assertThat(resultEntity, notNullValue());
        assertThat(resultEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(resultEntity.getBody(), samePropertyValuesAs(testUser));
    }

    @Test
    public void testCreateUser_Success() {
        assumeTrue("starting user map should be empty", userService.getUsersById().isEmpty());

        User testUser = new User(15, "create user");
        RequestEntity<User> requestEntity = RequestEntity
                .post(fromPath("/camel-rest/user").build().toUri())
                .body(testUser);

        ResponseEntity<User> responseEntity = restTemplate.exchange(requestEntity, User.class);

        assertThat(responseEntity, notNullValue());
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(responseEntity.getBody(), samePropertyValuesAs(testUser));
        assertThat(userService.getUsersById().values(), contains(samePropertyValuesAs(testUser)));
    }

    @Test
    public void testUpdateUser_Success() {
        userService.createUser(new User(10, "test user"));

        assumeFalse("starting user map should not be empty", userService.getUsersById().isEmpty());

        User updateUser = new User(10, "update user");
        RequestEntity<User> requestEntity = RequestEntity
                .put(fromPath("/camel-rest/user").build().toUri())
                .body(updateUser);

        ResponseEntity<User> responseEntity = restTemplate.exchange(requestEntity, User.class);

        assertThat(responseEntity, notNullValue());
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(responseEntity.getBody(), samePropertyValuesAs(updateUser));
        assertThat(userService.getUsersById().values(), contains(samePropertyValuesAs(updateUser)));
    }

    @Test
    public void testDeleteUser_Success() {
        userService.createUser(new User(10, "test user"));

        assumeFalse("starting user map should not be empty", userService.getUsersById().isEmpty());

        RequestEntity<Void> requestEntity = RequestEntity
                .delete(fromPath("/camel-rest/user/10").build().toUri())
                .build();

        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestEntity, Void.class);

        assertThat(responseEntity, notNullValue());
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(userService.getUsersById().values(), emptyCollectionOf(User.class));
    }

}
