package com.user.loan_Management.model;

import java.sql.Timestamp;

import com.user.loan_Management.model.support.Support;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoanApplication extends Support{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	
	private String name;
	
	
	private Timestamp dob;
	
	
	private String contactNo;
	
	
	private String mailId;
	
	
	private String gender;
	
	
	private String address;
	
	
	private String panNo;
	
	
	private String aadharNo;
	
	
	private String profession;
	
	
	private String annualIncome;
	
	
	private boolean married;
	
	
	private String workExperience;
	
	
	private String organisation;
	
	//@Column(columnDefinition = "varchar(50) default 'pending'")
	private String applicationStatus;
	
	

	public LoanApplication() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

	public LoanApplication(long id, String name, Timestamp dob, String contactNo, String mailId, String gender,
			String address, String panNo, String aadharNo, String profession, String annualIncome, boolean married,
			String workExperience, String organisation, String applicationStatus) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.contactNo = contactNo;
		this.mailId = mailId;
		this.gender = gender;
		this.address = address;
		this.panNo = panNo;
		this.aadharNo = aadharNo;
		this.profession = profession;
		this.annualIncome = annualIncome;
		this.married = married;
		this.workExperience = workExperience;
		this.organisation = organisation;
		this.applicationStatus = applicationStatus;
	}





	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getDob() {
		return dob;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	public boolean getMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}





	@Override
	public String toString() {
		return "LoanApplication [id=" + id + ", name=" + name + ", dob=" + dob + ", contactNo=" + contactNo
				+ ", mailId=" + mailId + ", gender=" + gender + ", address=" + address + ", panNo=" + panNo
				+ ", aadharNo=" + aadharNo + ", profession=" + profession + ", annualIncome=" + annualIncome
				+ ", married=" + married + ", workExperience=" + workExperience + ", organisation=" + organisation
				+ ", applicationStatus=" + applicationStatus + "]";
	}


	
	
	

}
