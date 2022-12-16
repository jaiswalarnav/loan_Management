package com.user.loan_Management.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LoanApplicationDto {

	private long id;

	@NotBlank(message = "name can not be empty")
	private String name;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "DOB can not be empty")
	private String dob;

	@NotBlank(message = "contact no can not be empty")
	@Pattern(regexp = "^[1-9][0-9]*$", message = "enter valid contact number")
	@Size(min = 10, max = 10, message = "contact number should be of 10 digits")
	private String contactNo;

	@Email
	@NotBlank(message = "mail Id can not be empty")
	private String mail;

	@NotBlank(message = "gender can not be empty ")
	private String gender;

	@NotBlank(message = "address can not be empty")
	@Size(max = 100, message = "Address length should not be greater than 100 characters ")
	private String address;

	@NotBlank(message = "Pan no can not be empty")
	@Pattern(regexp = "^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$", message = "entervalid pan number")
	private String panNo;

	@NotBlank(message = "Aadhar no can not be empty")
	@Pattern(regexp = "^[1-9][0-9]*$", message = "enter valid aadhar number")
	@Size(min = 12, max = 12, message = "aadhar number must be of 12 characters length ")
	private String aadharNo;

	@NotBlank(message = "occupationType can not be empty")
	private String occupationType;

	@NotNull(message = "monthly income can not be empty")
	private int monthlyIncome;

	@NotNull(message = "marital status can not be empty")
	private boolean married;

	@NotNull(message = "Current Employement Period can not be empty")
	private int currentEmployementPeriod;

	@NotBlank(message = "Employer Name can not be empty")
	private String employerName;

	// @Column(columnDefinition = "varchar(50) default 'pending'")
	private String applicationStatus;

	@NotBlank(message = "photo can not be empty")
	private String photo;

	@NotBlank(message = "signature can not be empty")
	private String signature;

	@NotBlank(message = "Salary Slip can not be empty")
	private String salarySlip;

	@NotBlank(message = "form16 can not be empty")
	private String form16;

	@NotNull(message = "Other Loans can not be empty")
	private boolean otherLoans;

	@NotNull(message = "total Emi can not be empty")
	private int totalEmi;

	@NotNull(message = "Loan Amount can not be empty")
	private int loanAmount;

	@NotNull(message = "Loan Tenure can not be empty")
	private int loanTenure;

}