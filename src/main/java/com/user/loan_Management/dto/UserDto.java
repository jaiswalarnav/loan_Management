package com.user.loan_Management.dto;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

public class UserDto {
	
	private long id;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull(message="dob can not be empty")
	private Timestamp dob;
	
	

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(long id, Timestamp dob) {
		super();
		this.id = id;
		this.dob = dob;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getDob() {
		return dob;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", dob=" + dob + "]";
	}
	
	
	

}
