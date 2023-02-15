package com.user.loan_Management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class StripeCustomer{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	private String mailId;
	
	private String stripeCustomerId;

	private long loanApplicationNo;
	
	private String cardToken;

}
