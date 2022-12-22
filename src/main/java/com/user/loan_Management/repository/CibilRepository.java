package com.user.loan_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.loan_Management.model.Cibil;

public interface CibilRepository extends JpaRepository<Cibil, Long>{
	
	 public Cibil findByPanNo(String panNo);

}
