package io.github.nishadchayanakhawa.testcompanion.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import io.github.nishadchayanakhawa.testcompanion.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.nishadchayanakhawa.testcompanion.model.Role;
import io.github.nishadchayanakhawa.testcompanion.model.User;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
	private static final Logger logger=LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

	@Value("${server.port}")
	private int serverPort;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		CommandLineAppStartupRunner.logger.info("Application started!!!");
		CommandLineAppStartupRunner.logger.info("Navigate to http://localhost:{}",serverPort);
		
		if(userService.getAllUsers().isEmpty()) {
			CommandLineAppStartupRunner.logger.warn("No users loaded. Creating default admin user");
			User user=new User();
			user.setUsername("admin");
			String generatedPassword=CommandLineAppStartupRunner.generateRandomPassword(20);
			user.setPassword(passwordEncoder.encode(generatedPassword));
			user.setFirstName("Admin");
			user.setLastName("LNU");
			user.setEmail("admin@organization.com");
			user.setRole(Role.ADMIN);
			userService.addUser(user);
			CommandLineAppStartupRunner.logger.warn("Username: admin Password: {}",generatedPassword);
			
			user=new User();
			user.setUsername("tester");
			generatedPassword=CommandLineAppStartupRunner.generateRandomPassword(20);
			user.setPassword(passwordEncoder.encode(generatedPassword));
			user.setFirstName("Tester");
			user.setLastName("LNU");
			user.setEmail("tester@organization.com");
			user.setRole(Role.TESTER);
			userService.addUser(user);
			CommandLineAppStartupRunner.logger.warn("Username: tester Password: {}",generatedPassword);
			
			user=new User();
			user.setUsername("user");
			generatedPassword=CommandLineAppStartupRunner.generateRandomPassword(20);
			user.setPassword(passwordEncoder.encode(generatedPassword));
			user.setFirstName("User");
			user.setLastName("LNU");
			user.setEmail("user.com");
			user.setRole(Role.USER);
			userService.addUser(user);
			CommandLineAppStartupRunner.logger.warn("Username: user Password: {}",generatedPassword);
		}
	}

	private static String generateRandomPassword(int length) {
		SecureRandom random = new SecureRandom(); // Compliant for security-sensitive use cases
		byte[] bytes = new byte[length];
		random.nextBytes(bytes);
		Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	    return encoder.encodeToString(bytes);
    }
	
}
