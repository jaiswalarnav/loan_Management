package com.user.loan_Management.model;
import java.sql.Timestamp;

import com.user.loan_Management.model.support.Support;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Aadhar extends Support {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String aadharNo;
	
	private String name;
	
	private String fatherName;
	
	private Timestamp dob;
	
	private String gender;
	
	private String phoneNo;
	
	private String email;
	
	private String address;
	
	
}