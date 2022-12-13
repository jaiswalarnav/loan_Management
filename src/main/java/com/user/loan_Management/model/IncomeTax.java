package com.user.loan_Management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class IncomeTax {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String panNo;

	private long taxPaidAmount;

}