package com.user.loan_Management.service;

import com.user.loan_Management.dto.LoanApplicationDto;
import com.user.loan_Management.dto.LoanApplicationStatusDto;


public interface LoanApplicationService {

	long createLoanApplication(LoanApplicationDto loanApplicationDto) throws Exception;
	
	String getLoanApplicationStatus(LoanApplicationStatusDto loanApplicationStatusDto) throws Exception;
	
}
