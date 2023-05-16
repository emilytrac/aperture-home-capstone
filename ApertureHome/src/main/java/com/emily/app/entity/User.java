package com.emily.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {

    /* Same POJO class as in the user service no need to map to database
    as all interaction handled within the user service */
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
