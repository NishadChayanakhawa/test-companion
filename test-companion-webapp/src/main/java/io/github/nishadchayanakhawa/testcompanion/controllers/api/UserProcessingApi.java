package io.github.nishadchayanakhawa.testcompanion.controllers.api;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.nishadchayanakhawa.testcompanion.model.User;
import io.github.nishadchayanakhawa.testcompanion.model.dto.UserDTO;
import io.github.nishadchayanakhawa.testcompanion.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserProcessingApi {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public ResponseEntity<Iterable<UserDTO>> getAllUsers() {
		List<UserDTO> users=this.userService.getAllUsers()
				.stream()
				.map(user-> modelMapper.map(user, UserDTO.class))
				.toList();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<User> addUser(@RequestBody User user) {
		HttpStatus httpStatus=HttpStatus.CREATED;
		if(this.userService.existsByUsername(user.getUsername())) {
			User existingUser=userService.findByUsername(user.getUsername());
			user.setPassword(existingUser.getPassword());
			httpStatus=HttpStatus.OK;
		} else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		return new ResponseEntity<>(this.userService.addUser(user),httpStatus);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<UserDTO> getUser(@PathVariable String username) {
		User user=this.userService.findByUsername(username);
		UserDTO userDto=modelMapper.map(user, UserDTO.class);
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable String username) {
		this.userService.deleteUser(username);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
