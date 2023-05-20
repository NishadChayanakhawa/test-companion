package io.github.nishadchayanakhawa.testcompanion;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application main class
 * @author nishad
 */
@SpringBootApplication
public class TestCompanionApplication {
	private static final Logger logger=LoggerFactory.getLogger(TestCompanionApplication.class);
	public static void main(String[] args) {
		logger.debug("Running spring boot application.");
		SpringApplication.run(TestCompanionApplication.class);
	}
}
