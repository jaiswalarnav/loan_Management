package com.user.loan_Management.dto;

import lombok.Data;

@Data
public class ViewLoanApplicationDto {

	private Long id;

	private String name;

	private String contactNo;

	private String panNo;

	private Integer loanAmount;

	private String applicationStatus;

}
