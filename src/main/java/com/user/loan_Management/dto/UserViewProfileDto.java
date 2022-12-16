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

	private boolean married;
	
	private String applicationStatus;

}
