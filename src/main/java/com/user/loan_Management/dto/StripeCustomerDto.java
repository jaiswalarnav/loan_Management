package com.user.loan_Management.dto;

import lombok.Data;

@Data
public class StripeCustomerDto {
	
	//private long id;
	
	private String name;
	
	private String mailId;
	
	private String stripeCustomerId;
	
	private long loanApplicationNo;
	
	

}
