package com.user.loan_Management.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CibilDto {
	
	private int id;
	
	@NotBlank(message = "Pan number field can not be blank")
	@Size(min = 10, max = 10, message = "Pan number must be 10 characters long")
	@Pattern(regexp = "^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$")	
	private String panNo;
	
	@NotBlank(message = "Cibil score field can not be blank")
	private int cibilScore;

	public CibilDto() {
		super();
		
	}

	public CibilDto(int id, String panNo, int cibilScore) {
		super();
		this.id = id;
		this.panNo = panNo;
		this.cibilScore = cibilScore;
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

	public int getCibilScore() {
		return cibilScore;
	}

	public void setCibilScore(int cibilScore) {
		this.cibilScore = cibilScore;
	}

	@Override
	public String toString() {
		return "CibilDto [id=" + id + ", panNo=" + panNo + ", cibilScore=" + cibilScore + "]";
	}
	
	

}
