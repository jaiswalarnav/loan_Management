package com.user.loan_Management.model;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Data
@Entity
public class LoanDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = "ROI can not be empty")
	private String roi;

	@NotBlank(message = "Loan Tenure can not be empty")
	private String loanTenure;

	@NotBlank(message = "Loan Amount can not be empty")
	private String loanAmount;

	@NotBlank(message = "Processing Fee can not be empty")
	private String processingFee;

}
