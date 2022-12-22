package com.user.loan_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.loan_Management.model.Aadhar;

public interface AadharRepository extends JpaRepository<Aadhar, Long>{
	
public Aadhar findByAadharNo(String aadharNo);
	
}
