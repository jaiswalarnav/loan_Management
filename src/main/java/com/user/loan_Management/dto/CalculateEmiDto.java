package com.user.loan_Management.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

//import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CalculateEmiDto {

	@NotNull(message = "Loan amount can not be empty")
	@Min(value=50000,message="Loan amount  can not be less than 50,000")
	@Max(value=1500000,message="Loan amount  can not be more than 15,00,000")
	private Long loanAmount;

	@NotNull(message = "ROI can not be empty")
	@Min(value=12,message="ROI can not be less than 12%")
	@Max(value=21,message="ROI can not be greater than 21%")
	private Float roi;

	
	@NotNull(message = "Tenure can not be empty")
	@Min(value=6,message="Tenure can not be less than 06 months")
	@Max(value=60,message="Tenure can not be greater than 60 months")
	private Integer tenure;

}
