package io.github.nishadchayanakhawa.testcompanion.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import io.github.nishadchayanakhawa.testcompanion.TestCompanionApplication;
import io.github.nishadchayanakhawa.testcompanion.model.User;
import io.github.nishadchayanakhawa.testcompanion.services.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
@SpringBootTest(classes = TestCompanionApplication.class,webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationHomeControllerTests {
	@Value("${server.port}")
	private int serverPort;
	
	private String url;
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private UserService userService;
	
	private MockMvc mvc;
	
	@BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
          .webAppContextSetup(context)
          .apply(springSecurity())
          .build();
        url=String.format("http://localhost:%d", serverPort);
    }
	
	@Test
    @Order(1)
    void loginPage_test() throws Exception {
    			mvc
    		.perform(
    				get(url + "/login"))
    		.andExpect(status().isOk());
    }
	
	@ParameterizedTest
    @Order(2)
	@ValueSource(strings = {"/home", "/setting/jenkins","/setting/usermanagement"})
    void homePage_test(String path) throws Exception {
		User user=userService.findByUsername("admin");
    			mvc
    		.perform(
    				get(url + path).with(user(user)))
    		.andExpect(status().isOk());
    }
	
	@Test
    @Order(2)
    void anonymous_test() throws Exception {
    			mvc
    		.perform(
    				get(url + "/home").with(anonymous()).with(csrf()))
    		.andExpect(status().is3xxRedirection());
    }

}
