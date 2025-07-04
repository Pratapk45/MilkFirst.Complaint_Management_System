package com.milkFirst.MilkFirst_Complaint_Management_System.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private  String username;
    private  String password;
    private  Long mobileNo ;
    private String gmail;
    private String role = "USER";
}
