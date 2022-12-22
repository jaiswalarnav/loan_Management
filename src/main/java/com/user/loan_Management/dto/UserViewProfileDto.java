package com.user.loan_Management.dto;

import lombok.Data;

@Data
public class UserViewProfileDto {
	
	private String name;

	private String dob;

	private String contactNo;

	private String mail;

	private String gender;

	private String address;

	private Boolean married;
	
	private String applicationStatus;
	
	private String message;

}
