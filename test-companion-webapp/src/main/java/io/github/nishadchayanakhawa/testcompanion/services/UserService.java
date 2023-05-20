package io.github.nishadchayanakhawa.testcompanion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.nishadchayanakhawa.testcompanion.jparepositories.UserRepository;
import io.github.nishadchayanakhawa.testcompanion.model.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}
	
	public User addUser(User user) {
		return this.userRepository.save(user);
	}
	
	public boolean existsByUsername(String username) {
		return this.userRepository.existsById(username);
	}
	
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}
	
	public void deleteUser(String username) {
		this.userRepository.deleteById(username);
	}
}
