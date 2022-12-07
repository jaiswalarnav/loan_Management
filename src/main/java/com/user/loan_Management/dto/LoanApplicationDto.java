package com.user.loan_Management.dto;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class LoanApplicationDto {
	
	
	private long id;
	
	@NotBlank(message="name can not be empty")
	private String name;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotBlank(message="DOB can not be empty")
	private Timestamp dob;
	
	@NotBlank(message="contact no can not be empty")
	@Pattern(regexp="^[1-9][0-9]*$")
	@Size(min=10,max=10)
	private String contactNo;
	
	@Email
	@NotBlank(message="mail Id can not be empty" )
	private String mailId;
	
	@NotBlank(message="gender can not be empty ")
	private String gender;
	
	@NotBlank(message="address can not be empty")
	private String address;
	
	@NotBlank(message="Pan no can not be empty")
	@Size(min=10,max=10,message="pan number must be of 10 characters length ")
	private String panNo;
	
	@NotBlank(message="Aadhar no can not be empty")
	@Pattern(regexp="^[1-9][0-9]*$")
	@Size(min=12,max=12,message="aadhar number must be of 12 characters length ")
	private String aadharNo;
	
	@NotBlank(message="profession can not be empty")
	private String profession;
	
	@NotBlank(message="annual income can not be empty")
	private String annualIncome;
	
	@NotBlank(message="marital can not be empty")
	@Column(columnDefinition=" boolean default false ")
	private boolean married;
	
	@NotBlank(message="work experience can not be empty")
	private String workExperience;
	
	@NotBlank(message="organisation can not be empty")
	private String organisation;
	
	@Column(columnDefinition = "varchar(50) default 'pending'")
	private String applicationStatus;

}
