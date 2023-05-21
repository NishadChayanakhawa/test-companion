package io.github.nishadchayanakhawa.testcompanion.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import io.github.nishadchayanakhawa.testcompanion.model.JenkinsSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.nishadchayanakhawa.testcompanion.TestCompanionApplication;

@SpringBootTest(classes = TestCompanionApplication.class,webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
class JenkinsSettingProcessingApiTests {
	private static final Logger logger=LoggerFactory.getLogger(JenkinsSettingProcessingApiTests.class);
	
	@Value("${server.port}")
	private int serverPort;
	
	private String url;
	
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
        url=String.format("http://localhost:%d", serverPort);
    }
	
	@Test
    @Order(1)
    void getJenkinsSetting_emptyTest() throws Exception {
		mvc
    		.perform(
    				get(url + "/api/jenkinsSetting")
    				.with(user("admin").password("admin").roles("ADMIN")))
    		.andExpect(status().isOk()).andReturn();
		JenkinsSettingProcessingApiTests.logger.info("getJenkinsSetting_emptyTest [PASS]");
    }
	
	@Test
    @Order(2)
    void putJenkinsSetting_test() throws Exception {
		JenkinsSetting jenkinsSetting=new JenkinsSetting();
		jenkinsSetting.setUrl("http://103.160.144.199:8080");
		jenkinsSetting.setUsername("nishad");
		jenkinsSetting.setApiKey("11ee84e97dbc4da1ddc4f3bdde05d3bfc8");
		mvc
    		.perform(
    				put(url + "/api/jenkinsSetting")
    				.contentType(MediaType.APPLICATION_JSON_VALUE)
    				.content(objectMapper.writeValueAsString(jenkinsSetting))
    				.with(user("admin").password("admin").roles("ADMIN")))
    		.andExpect(status().isOk()).andReturn();
		JenkinsSettingProcessingApiTests.logger.info("putJenkinsSetting_test [PASS]");
    }
	
	@Test
    @Order(3)
    void testJenkinsSetting_test() throws Exception {
		JenkinsSetting jenkinsSetting=new JenkinsSetting();
		jenkinsSetting.setUrl("http://103.160.144.199:8080");
		jenkinsSetting.setUsername("nishad");
		jenkinsSetting.setApiKey("11ee84e97dbc4da1ddc4f3bdde05d3bfc8");
		mvc
    		.perform(
    				post(url + "/api/jenkinsSetting")
    				.contentType(MediaType.APPLICATION_JSON_VALUE)
    				.content(objectMapper.writeValueAsString(jenkinsSetting))
    				.with(user("admin").password("admin").roles("ADMIN")))
    		.andExpect(status().isOk()).andReturn();
		JenkinsSettingProcessingApiTests.logger.info("testJenkinsSetting_test [PASS]");
    }
	
	@Test
    @Order(4)
    void testJenkinsSetting_invalidTest() throws Exception {
		JenkinsSetting jenkinsSetting=new JenkinsSetting();
		jenkinsSetting.setUrl("http://103.160.144.199:8080");
		jenkinsSetting.setUsername("invalid");
		jenkinsSetting.setApiKey("invalid");
		mvc
    		.perform(
    				post(url + "/api/jenkinsSetting")
    				.contentType(MediaType.APPLICATION_JSON_VALUE)
    				.content(objectMapper.writeValueAsString(jenkinsSetting))
    				.with(user("admin").password("admin").roles("ADMIN")))
    		.andExpect(status().isInternalServerError()).andReturn();
		JenkinsSettingProcessingApiTests.logger.info("testJenkinsSetting_test [PASS]");
    }
}
