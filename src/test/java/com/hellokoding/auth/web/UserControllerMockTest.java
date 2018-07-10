package com.hellokoding.auth.web;

import com.hellokoding.auth.model.User;
import com.hellokoding.auth.service.UserService;
import com.hellokoding.auth.service.WeatherService;
import com.hellokoding.auth.test.AbstractControllerTest;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class UserControllerMockTest extends AbstractControllerTest{

    @Mock
    private UserService userService;

     @InjectMocks
    private UserController userController;

   @Mock
   private WeatherService weatherService;
   
    @Before
    public void setUp() {
        // Initialize Mockito annotated components
        MockitoAnnotations.initMocks(this);
        // Prepare the Spring MVC Mock components for standalone testing
        setUp(userController);
    }
/*
    @Test
    public void testGetGreetings() throws Exception {

        // Create some test data
        Collection<Greeting> list = getEntityListStubData();

        // Stub the GreetingService.findAll method return value
        when(greetingService.findAll()).thenReturn(list);

        // Perform the behavior being tested
        String uri = "/api/greetings";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        // Extract the response status and body
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        // Verify the GreetingService.findAll method was invoked once
        verify(greetingService, times(1)).findAll();

        // Perform standard JUnit assertions on the response
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

    }

    @Test
    public void testGetLocation() throws Exception {

        // Create some test data
        Long id = new Long(1);
        User entity = getEntityStubData();

        // Stub the GreetingService.findOne method return value
        when(userService.findOne(id)).thenReturn(entity);

        // Perform the behavior being tested
        String uri = "/api/greetings/{id}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        // Extract the response status and body
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        // Verify the GreetingService.findOne method was invoked once
        verify(greetingService, times(1)).findOne(id);

        // Perform standard JUnit assertions on the test results
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }

    @Test
    public void testGetGreetingNotFound() throws Exception {

        // Create some test data
        Long id = Long.MAX_VALUE;

        // Stub the GreetingService.findOne method return value
        when(greetingService.findOne(id)).thenReturn(null);

        // Perform the behavior being tested
        String uri = "/api/greetings/{id}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        // Extract the response status and body
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        // Verify the GreetingService.findOne method was invoked once
        verify(greetingService, times(1)).findOne(id);

        // Perform standard JUnit assertions on the test results
        Assert.assertEquals("failure - expected HTTP status 404", 404, status);
        Assert.assertTrue("failure - expected HTTP response body to be empty",
                content.trim().length() == 0);

    }

  
   */

    private Collection<User> getEntityListStubData() {
        Collection<User> list = new ArrayList<User>();
        list.add(getEntityStubData());
        return list;
    }

    private User getEntityStubData() {
        User entity = new User();
        entity.setUsername("testuser");
        return entity;
    }

}