package com.user.loan_Management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cibil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String panNo;
	
	private int cibilScore;

	public Cibil() {
		super();
		
	}

	public Cibil(int id, String panNo, int cibilScore) {
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
		return "Cibil [id=" + id + ", panNo=" + panNo + ", cibilScore=" + cibilScore + "]";
	}
	
	
	
	

}
