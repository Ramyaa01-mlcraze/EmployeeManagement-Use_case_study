package com.app.employee.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.employee.model.User;

/*Deletion, storage, retrieval, storing list of the object at a single time*/
public interface UserRepository extends MongoRepository<User, String>{
	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
}
