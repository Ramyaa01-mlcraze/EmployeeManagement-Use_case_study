package com.app.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*Collection consisting of roles of the user*/
@Document(collection="roles")
public class Role {
	
	@Id
	private String id;
	
	private ERole name;

	public Role() {

	}
	
	/* Getters and setters of the method */
	public Role(ERole name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

}
