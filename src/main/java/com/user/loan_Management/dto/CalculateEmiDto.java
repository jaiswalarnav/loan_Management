package com.user.loan_Management.dto;


import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CalculateEmiDto {

	@NotNull(message = "loan amount can not be empty")
	private long loanAmount;

	@NotNull(message = "ROI can not be empty")
	private float roi;

	@NotNull(message = "tenure can not be empty")
	private int tenure;

}
