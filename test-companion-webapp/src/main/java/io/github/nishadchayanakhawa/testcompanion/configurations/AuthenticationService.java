package io.github.nishadchayanakhawa.testcompanion.configurations;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.github.nishadchayanakhawa.testcompanion.model.User;
import io.github.nishadchayanakhawa.testcompanion.services.UserService;
import org.slf4j.Logger;
/**
 * Authentication Service
 * @author nishad
 */
@Service
public class AuthenticationService implements UserDetailsService {
	private static Logger logger=LoggerFactory.getLogger(AuthenticationService.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=this.userService.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		logger.info("User: {}",user);
		return user;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
