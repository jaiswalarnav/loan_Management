package com.user.loan_Management.dtoToModel;

import com.user.loan_Management.dto.LoanApplicationDto;
import com.user.loan_Management.model.LoanApplication;

public class LoanApplicationDtoToLoanApplication {

	public LoanApplication dtoToLoanApplication(LoanApplicationDto loanApplicationDto) {

		LoanApplication loanApplication = new LoanApplication();

		loanApplication.setAadharNo(loanApplicationDto.getAadharNo());
		loanApplication.setAddress(loanApplicationDto.getAddress());
		loanApplication.setAnnualIncome(loanApplicationDto.getAnnualIncome());
		loanApplication.setApplicationStatus(loanApplicationDto.getApplicationStatus());
		loanApplication.setContactNo(loanApplicationDto.getContactNo());

		loanApplication.setDob(loanApplicationDto.getDob());
		loanApplication.setGender(loanApplicationDto.getGender());

		loanApplication.setMailId(loanApplicationDto.getMailId());
		loanApplication.setMarried(loanApplicationDto.getMarried());
		loanApplication.setName(loanApplicationDto.getName());
		loanApplication.setOrganisation(loanApplicationDto.getOrganisation());
		loanApplication.setPanNo(loanApplicationDto.getPanNo());
		loanApplication.setProfession(loanApplicationDto.getProfession());

		loanApplication.setWorkExperience(loanApplicationDto.getWorkExperience());
		
		return loanApplication;

	}

}
