package com.app.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.employee.model.User;
import com.app.employee.repository.UserRepository;

@Service
public class AuthService {

		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private PasswordEncoder encoder;
		
		
		/* Method to check in the repository whether the username exists */
		public boolean existsByUsername(String username) {
			return userRepository.existsByUsername(username);
		}
		
		/* Method to check whether the user object is saved in the repository */
		public User save(User user) {
			return userRepository.save(user);
		}

		
}
