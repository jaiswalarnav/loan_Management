package com.user.loan_Management.model;

import java.sql.Timestamp;

import com.user.loan_Management.model.support.Support;

import jakarta.persistence.Entity;

@Entity
public class Pan extends Support{

	private int id;
	private String panNo;
	private String name;
	private String fatherName;
	private Timestamp dob;
	private String gender;
	private String phoneNo;
	private String emailId;
	private String address;
	
	
	public Pan() {
		super();
		
	}


	public Pan(int id, String panNo, String name, String fatherName, Timestamp dob, String gender, String phoneNo,
			String emailId, String address) {
		super();
		this.id = id;
		this.panNo = panNo;
		this.name = name;
		this.fatherName = fatherName;
		this.dob = dob;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.emailId = emailId;
		this.address = address;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFatherName() {
		return fatherName;
	}


	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}


	public Timestamp getDob() {
		return dob;
	}


	public void setDob(Timestamp dob) {
		this.dob = dob;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhone_no(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Pan [id=" + id + ", panNo=" + panNo + ", name=" + name + ", fatherName=" + fatherName + ", dob="
				+ dob + ", gender=" + gender + ", phoneNo=" + phoneNo + ", emailId=" + emailId + ", address="
				+ address + "]";
	}
	
	
	
	
	
}
