package com.user.loan_Management.model;

import java.sql.Timestamp;

import com.user.loan_Management.model.support.Support;

public class User extends Support{
	
	private long id;
	
	private Timestamp dob;
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(boolean isDeleted, Timestamp createdAt, Timestamp updatedAt, Timestamp deletedAt) {
		super(isDeleted, createdAt, updatedAt, deletedAt);
		// TODO Auto-generated constructor stub
	}

	public User(long id, Timestamp dob) {
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
		return "User [id=" + id + ", dob=" + dob + "]";
	}
	
	
	
	

}
