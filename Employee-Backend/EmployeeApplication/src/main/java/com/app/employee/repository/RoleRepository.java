package com.app.employee.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.employee.model.ERole;
import com.app.employee.model.Role;

/*Deletion, storage, retrieval, storing list of the object at a single time*/
public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);

}
