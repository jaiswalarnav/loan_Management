package com.user.loan_Management.dto;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class LoanApplicationDto {
	
	
	private long id;
	
	@NotBlank(message="name can not be empty")
	private String name;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotBlank(message="DOB can not be empty")
	private Timestamp dob;
	
	@NotBlank(message="contact no can not be empty")
	@Pattern(regexp="^[1-9][0-9]*$")
	@Size(min=10,max=10)
	private String contactNo;
	
	@Email
	@NotBlank(message="mail Id can not be empty" )
	private String mailId;
	
	@NotBlank(message="gender can not be empty ")
	private String gender;
	
	@NotBlank(message="address can not be empty")
	private String address;
	
	@NotBlank(message="Pan no can not be empty")
	@Size(min=10,max=10,message="pan number must be of 10 characters length ")
	private String panNo;
	
	@NotBlank(message="Aadhar no can not be empty")
	@Pattern(regexp="^[1-9][0-9]*$")
	@Size(min=12,max=12,message="aadhar number must be of 12 characters length ")
	private String aadharNo;
	
	@NotBlank(message="profession can not be empty")
	private String profession;
	
	@NotBlank(message="annual income can not be empty")
	private String annualIncome;
	
	@NotBlank(message="marital can not be empty")
	@Column(columnDefinition=" boolean default false ")
	private boolean married;
	
	@NotBlank(message="work experience can not be empty")
	private String workExperience;
	
	@NotBlank(message="organisation can not be empty")
	private String organisation;
	
	@Column(columnDefinition = "varchar(50) default 'pending'")
	private String applicationStatus;

	
	
	public LoanApplicationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanApplicationDto(long id, @NotBlank(message = "name can not be empty") String name,
			@NotBlank(message = "DOB can not be empty") Timestamp dob,
			@NotBlank(message = "contact no can not be empty") @Pattern(regexp = "^[1-9][0-9]*$") @Size(min = 10, max = 10) String contactNo,
			@Email @NotBlank(message = "mail Id can not be empty") String mailId,
			@NotBlank(message = "gender can not be empty ") String gender,
			@NotBlank(message = "address can not be empty") String address,
			@NotBlank(message = "Pan no can not be empty") @Size(min = 10, max = 10, message = "pan number must be of 10 characters length ") String panNo,
			@NotBlank(message = "Aadhar no can not be empty") @Pattern(regexp = "^[1-9][0-9]*$") @Size(min = 12, max = 12, message = "aadhar number must be of 12 characters length ") String aadharNo,
			@NotBlank(message = "profession can not be empty") String profession,
			@NotBlank(message = "annual income can not be empty") String annualIncome,
			@NotBlank(message = "marital can not be empty") boolean married,
			@NotBlank(message = "work experience can not be empty") String workExperience,
			@NotBlank(message = "organisation can not be empty") String organisation, String applicationStatus) {
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

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
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
		return "LoanApplicationDto [id=" + id + ", name=" + name + ", dob=" + dob + ", contactNo=" + contactNo
				+ ", mailId=" + mailId + ", gender=" + gender + ", address=" + address + ", panNo=" + panNo
				+ ", aadharNo=" + aadharNo + ", profession=" + profession + ", annualIncome=" + annualIncome
				+ ", married=" + married + ", workExperience=" + workExperience + ", organisation=" + organisation
				+ ", applicationStatus=" + applicationStatus + "]";
	}
	
	
	
	
	
	
	

}
