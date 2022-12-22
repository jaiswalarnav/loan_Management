package com.user.loan_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.loan_Management.model.Pan;

public interface PanRepository extends JpaRepository<Pan, Long> {
	
	Pan findByPanNo(String panNo);

}
