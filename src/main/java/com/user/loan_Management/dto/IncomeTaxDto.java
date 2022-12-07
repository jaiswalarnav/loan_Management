package com.user.loan_Management.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class IncomeTaxDto {
	
	private int id;
	
	@NotBlank(message = "Pan number field can not be blank")
	@Size(min = 10, max = 10, message = "Pan number must be 10 characters long")
	@Pattern(regexp = "^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$")	
	private String panNo;
	
	@NotBlank(message = "Tax paid amount field can not be blank")
	private long taxPaidAmount;

	public IncomeTaxDto() {
		super();
		
	}

	public IncomeTaxDto(int id, String panNo, long taxPaidAmount) {
		super();
		this.id = id;
		this.panNo = panNo;
		this.taxPaidAmount = taxPaidAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public long getTaxPaidAmount() {
		return taxPaidAmount;
	}

	public void setTaxPaidAmount(long taxPaidAmount) {
		this.taxPaidAmount = taxPaidAmount;
	}

	@Override
	public String toString() {
		return "IncomeTaxDto [id=" + id + ", panNo=" + panNo + ", taxPaidAmount=" + taxPaidAmount + "]";
	}
	
	
	

}
