package com.user.loan_Management.service;

import java.util.List;

import com.user.loan_Management.dto.AdminDto;
import com.user.loan_Management.dto.AdminRegisterDto;
import com.user.loan_Management.dto.ViewLoanApplicationDto;
import com.user.loan_Management.model.LoanApplication;

public interface AdminService {

	String loginAdmin(AdminDto adminDto) throws Exception;

	long registerAdmin(AdminRegisterDto adminRegisterDto) throws Exception;

	List<ViewLoanApplicationDto> viewAllLoanApplications(String applicationStatus) throws Exception;
	
	LoanApplication viewLoanApplication(long applicationNo) throws Exception;
	
	String verifyLoanApplication(long applicationNo)throws Exception;
}
