package com.emily.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {
	
	/* userId is the primary key in MySQL table
	field names match up with fields in the database */
	@Id
	private int userId;
	private String userEmail;
	private String firstName;
	private String lastName;
	private String userPassword;
	
	// id is an auto increment field so does not need to be in the constructor - can be implemented later to be able to add users
	
//	public User(String userEmail, String firstName, String lastName, String userPassword) {
//		this.userEmail = userEmail;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.userPassword = userPassword;
//	}

}
