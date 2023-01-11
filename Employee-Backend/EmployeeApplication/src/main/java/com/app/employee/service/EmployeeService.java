package com.app.employee.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.app.employee.model.Employee;
import com.app.employee.model.EmployeeSequence;

@Service
public class EmployeeService {
	private MongoOperations mongoOperations;
	
	@Autowired
	public EmployeeService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
	
	/* Method to generate auto generated employee sequence */
	public long generateEmployeeSequence(String seqName) {
		EmployeeSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
				new Update().inc("seq",1), options().returnNew(true).upsert(true), 
				EmployeeSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() +215678 : 1;
	}
	
	/* Method to final all the employees and return the Employee in a list */
	public List<Employee> listAll() {
		return mongoOperations.findAll(Employee.class);
	}

	
	


}
