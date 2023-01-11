package com.app.employee.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.employee.exceptions.BadRequestException;
import com.app.employee.exceptions.EmployeeNotFoundException;
import com.app.employee.exceptions.UserNotFoundException;

import com.app.employee.model.ERole;
import com.app.employee.model.Employee;
import com.app.employee.model.Role;
import com.app.employee.model.User;
import com.app.employee.payload.request.LoginRequest;
import com.app.employee.payload.request.SignupRequest;
import com.app.employee.payload.response.MessageResponse;
import com.app.employee.payload.response.JwtResponse;

import com.app.employee.repository.RoleRepository;
import com.app.employee.repository.UserRepository;
import com.app.employee.security.jwt.JwtUtils;
import com.app.employee.security.services.UserDetailsImpl;
import com.app.employee.service.AuthService;


@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	AuthService authservice;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	

	/* Invalid attempts variable */
	private static final int MAX_ATTEMPTS = 3;
	
	/* Controller for login */
	 @PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt=jwtUtils.generateJwtToken(authentication);

	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


	    List<String> roles = userDetails.getAuthorities().stream()
	        .map(item -> item.getAuthority())
	        .collect(Collectors.toList());

	    return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getId(),
	                                   userDetails.getUsername(),
	                                   userDetails.getEmail(),
	                                   roles));
	  }
	
	 
	/* Controller for register */
	 @PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Username is already taken!"));
	    }

	    else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Email is already in use!"));
	    }

		/*
		 * Create new user's account
		 */	    User user = new User(signUpRequest.getUsername(), 
	                         signUpRequest.getEmail(),
	                         encoder.encode(signUpRequest.getPassword()));

	    Set<String> strRoles = signUpRequest.getRoles();
	    Set<Role> roles = new HashSet<>();

	    if (strRoles == null) {
	      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	      roles.add(userRole);
	    } else {
	      strRoles.forEach(role -> {
	        switch (role) {
	        case "admin":
	          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(adminRole);

	          break;
	        default:
	          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(userRole);
	        }
	      });
	    }

	    user.setRoles(roles);
	    userRepository.save(user);

	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	  }
	 
	
	/* Updating the password of the user by their username */
	 @PutMapping("/change-password/{username}")
		public ResponseEntity<User> updatePassword(@PathVariable(value= "username")String username,
			@Valid @RequestBody User userInfo) throws UserNotFoundException{
				User user = userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException("User Not Found"));
				user.setPassword(encoder.encode (userInfo.getPassword()));
				final User updatedPassword = userRepository.save(user);
				return ResponseEntity.ok(updatedPassword);
		}
	 
	/* Updating the invalid attempt of the user by their username */
	 @PutMapping("/failed-attempt/{username}")
		public ResponseEntity<User> updateAttempt(@PathVariable(value= "username")String username,
			@Valid @RequestBody User userInfo) throws UserNotFoundException{
				User user = userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException("User Not Found"));
				if(user.getFailedAttempt() >= MAX_ATTEMPTS) {
					user.setAccountLocked(true);
					user.setLockTime(new Date());
					userRepository.save(user);
					throw new RuntimeException("Login Attempt exceeds "+MAX_ATTEMPTS);
					
				}
				else {
		            int attempts = user.getFailedAttempt();
		            user.setFailedAttempt(++attempts);
		            
		            userRepository.save(user);
					/* Create Error Message for User */
		            throw new RuntimeException("Login attempts "+attempts);
		        }
		}
	
		 
	 
	 
}
	
	


