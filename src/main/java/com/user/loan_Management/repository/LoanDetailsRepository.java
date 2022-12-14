package com.user.loan_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.loan_Management.model.LoanDetails;

public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Long> {

}
