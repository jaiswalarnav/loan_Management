package com.user.loan_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.user.loan_Management.model.StripeCustomer;


@Repository
public interface StripeCustomerRepository extends JpaRepository<StripeCustomer, Long>{
	
	public StripeCustomer findByLoanApplicationNo(long loanApplicationNo);

}
