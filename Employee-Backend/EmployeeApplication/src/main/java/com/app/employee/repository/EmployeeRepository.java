package com.app.employee.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.employee.model.Employee;

/*Deletion, storage, retrieval, storing list of the object at a single time*/
@Repository
public interface EmployeeRepository extends MongoRepository<Employee,Long> {
	
}
