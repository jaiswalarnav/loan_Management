package com.user.loan_Management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adminId;
	
	private String password;
	private long applicationNo;
	
	
	public Admin() {
		super();
		
	}


	public Admin(int adminId, String password, long applicationNo) {
		super();
		this.adminId = adminId;
		this.password = password;
		this.applicationNo = applicationNo;
	}


	public int getAdminId() {
		return adminId;
	}


	public void setAdminId(int adminId) {
		this.adminId = adminId;
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


	public void setApplicationNo(long applicationNo) {
		this.applicationNo = applicationNo;
	}


	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", password=" + password + ", applicationNo=" + applicationNo + "]";
	}
	
	
	

}
