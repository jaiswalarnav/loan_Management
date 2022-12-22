package com.user.loan_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.loan_Management.model.IncomeTax;

public interface IncomeTaxRepository extends JpaRepository<IncomeTax, Long>{
	
	public IncomeTax findByPanNo(String panNo);

}
