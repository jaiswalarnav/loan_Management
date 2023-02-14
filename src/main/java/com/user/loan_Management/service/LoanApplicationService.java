package com.user.loan_Management.service;

import com.user.loan_Management.dto.CalculateEmiDto;
import com.user.loan_Management.dto.LoanApplicationDto;
import com.user.loan_Management.dto.LoanApplicationStatusDto;
import com.user.loan_Management.dto.LoanDetailsDto;


public interface LoanApplicationService {

	long createLoanApplication(LoanApplicationDto loanApplicationDto) throws Exception;
	
	String getLoanApplicationStatus(LoanApplicationStatusDto loanApplicationStatusDto) throws Exception;
	
	LoanDetailsDto viewLoanDetails()throws Exception;
	
	double calculateEmi(CalculateEmiDto calculateEmiDto)throws Exception;
	
	String sendAdminEmail(long loanApplicationId)throws Exception;
	
}
