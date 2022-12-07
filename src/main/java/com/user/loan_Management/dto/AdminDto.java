package com.user.loan_Management.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



public class AdminDto {
	
	
	private int id;
	
	@NotBlank(message = "Password field can not be blank")
	@Size(min = 8, message = "Password size must be atleast 8 characters long !")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)(?=.*[@#$%^&+=]).*[A-Za-z0-9]$",
    message = "Password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit !!")
	private String password;
	
	private long applicationNo;
	
	
	public AdminDto() {
		super();
		
	}


	public AdminDto(int id, String password, long applicationNo) {
		super();
		this.id = id;
		this.password = password;
		this.applicationNo = applicationNo;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public long getApplicationNo() {
		return applicationNo;
	}


	public void setApplication_no(long applicationNo) {
		this.applicationNo = applicationNo;
	}


	@Override
	public String toString() {
		return "AdminDto [id=" + id + ", password=" + password + ", applicationNo=" + applicationNo + "]";
	}
	
	
	

}
