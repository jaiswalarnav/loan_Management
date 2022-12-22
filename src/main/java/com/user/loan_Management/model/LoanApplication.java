package com.user.loan_Management.model;

import javax.validation.constraints.NotBlank;

import com.user.loan_Management.model.support.Support;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LoanApplication extends Support {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	private String dob;

	private String contactNo;

	private String mail;

	private String gender;

	private String address;

	private String panNo;

	private String aadharNo;

	private String occupationType;

	

	private int monthlyIncome;

	private boolean married;

	private int currentEmployementPeriod;
	
	private String employerName;

	private String photo;

	private String signature;

	private String salarySlip;

	private String form16;

	private boolean otherLoans;

	private int totalEmi;

	private int loanAmount;

	private int loanTenure;

	// @Column(columnDefinition = "varchar(50) default 'pending'")
	private String applicationStatus;
	
	private String message;

}