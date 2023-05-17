package io.github.nishadchayanakhawa.testcompanion.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.nishadchayanakhawa.testcompanion.model.User;
import io.github.nishadchayanakhawa.testcompanion.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserProcessingApi {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<Iterable<User>> getAllUsers() {
		return new ResponseEntity<>(this.userService.getAllUsers(),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<User> addUser(@RequestBody User user) {
		HttpStatus httpStatus=HttpStatus.CREATED;
		if(this.userService.existsByUsername(user.getUsername())) {
			httpStatus=HttpStatus.OK;
		}
		return new ResponseEntity<>(this.userService.addUser(user),httpStatus);
	}
}
