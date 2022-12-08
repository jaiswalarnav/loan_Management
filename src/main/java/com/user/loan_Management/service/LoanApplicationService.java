package com.user.loan_Management.service;

import com.user.loan_Management.dto.LoanApplicationDto;

public interface LoanApplicationService {

	long createLoanApplication(LoanApplicationDto loanApplicationDto) throws Exception;
	
}
