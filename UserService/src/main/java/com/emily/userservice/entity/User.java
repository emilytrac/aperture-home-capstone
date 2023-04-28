package com.emily.userservice.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int userId;
    private String userEmail;
    private String firstName;
    private String lastName;
    private String userPassword;

}
