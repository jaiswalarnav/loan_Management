package com.user.loan_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.loan_Management.dto.LoanApplicationDto;
import com.user.loan_Management.http.response.DataResponse;
import com.user.loan_Management.service.LoanApplicationService;

@RestController
@RequestMapping("/loan/personal-loan/loan-application")
public class LoanApplicationController {
	
	@Autowired
	LoanApplicationService loanApplicationService;
	
	@PostMapping("/apply-loan")
	public DataResponse postLoanApplication(@RequestBody LoanApplicationDto loanApplicationDto) {
		
		try {
			
		
			long applicationNo=loanApplicationService.createLoanApplication(loanApplicationDto);
		
		return new DataResponse(200,"LOAN_SUCCESSFULLY_APPLIED",applicationNo);
		
		}
		catch(Exception e) {
			
			return new DataResponse(500,"INTERNAL_SERVER_ERROR",null);
		}
		
	}

}
