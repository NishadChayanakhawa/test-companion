package io.github.nishadchayanakhawa.testcompanion.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import io.github.nishadchayanakhawa.testcompanion.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.nishadchayanakhawa.testcompanion.TestCompanionApplication;
import org.slf4j.Logger;

@SpringBootTest(classes = TestCompanionApplication.class,webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserProcessingApiTests {
	private static final Logger logger=LoggerFactory.getLogger(UserProcessingApiTests.class);
	
	@Autowired
    private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
    @Order(1)
    void addUser_test() throws Exception {
		User user=new User();
		user.setUsername("test");
    			mvc
    		.perform(
    				put("http://localhost:8999/api/user")
    				.contentType(MediaType.APPLICATION_JSON_VALUE)
    				.content(objectMapper.writeValueAsString(user))).andExpect(status().isCreated()).andReturn();
    	UserProcessingApiTests.logger.info("addUser_test [PASS]");
    }
	
	@Test
    @Order(2)
    void updateUser_test() throws Exception {
		User user=new User();
		user.setUsername("test");
		user.setFirstName("Jane");
    			mvc
    		.perform(
    				put("http://localhost:8999/api/user")
    				.contentType(MediaType.APPLICATION_JSON_VALUE)
    				.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk()).andReturn();
    	UserProcessingApiTests.logger.info("updateUser_test [PASS]");
    }
	
	@Test
    @Order(3)
    void getAllUsers_test() throws Exception {
    	MvcResult result=
    			mvc
    		.perform(
    				get("http://localhost:8999/api/user"))
    		.andExpect(status().isOk()).andReturn();
    	UserProcessingApiTests.logger.info("{}",result.getResponse().getContentAsString());
    	UserProcessingApiTests.logger.info("getAllUsers_test [PASS]");
    }
}
