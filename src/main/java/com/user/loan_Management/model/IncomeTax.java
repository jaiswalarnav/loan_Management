  package com.user.loan_Management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
  public class IncomeTax {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String panNo;
	
	private long taxPaidAmount;

	public IncomeTax() {
		super();
		
	}

	public IncomeTax(int id, String panNo, long taxPaidAmount) {
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
		return "IncomeTax [id=" + id + ", panNo=" + panNo + ", taxPaidAmount=" + taxPaidAmount + "]";
	}
	
	
	
}
