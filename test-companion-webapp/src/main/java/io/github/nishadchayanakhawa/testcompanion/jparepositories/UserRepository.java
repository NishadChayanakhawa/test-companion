package io.github.nishadchayanakhawa.testcompanion.jparepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.nishadchayanakhawa.testcompanion.model.User;

public interface UserRepository extends JpaRepository<User,String>{
	public User findByUsername(String username);
}
