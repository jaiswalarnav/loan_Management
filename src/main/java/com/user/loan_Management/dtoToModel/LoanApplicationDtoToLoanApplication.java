package com.user.loan_Management.dtoToModel;

import com.user.loan_Management.dto.LoanApplicationDto;
import com.user.loan_Management.model.LoanApplication;

public class LoanApplicationDtoToLoanApplication {

	public LoanApplication dtoToLoanApplication(LoanApplicationDto loanApplicationDto) {

		LoanApplication loanApplication = new LoanApplication();

		loanApplication.setAadharNo(loanApplicationDto.getAadharNo());
		loanApplication.setAddress(loanApplicationDto.getAddress());
		loanApplication.setMonthlyIncome(loanApplicationDto.getMonthlyIncome());
		loanApplication.setApplicationStatus(loanApplicationDto.getApplicationStatus());
		loanApplication.setContactNo("+91" + loanApplicationDto.getContactNo());
		loanApplication.setDob(loanApplicationDto.getDob());
		loanApplication.setGender(loanApplicationDto.getGender());
		loanApplication.setMailId(loanApplicationDto.getMailId());
		loanApplication.setMarried(loanApplicationDto.isMarried());
		loanApplication.setName(loanApplicationDto.getName());
		loanApplication.setEmployerName(loanApplicationDto.getEmployerName());
		loanApplication.setPanNo(loanApplicationDto.getPanNo());
		loanApplication.setOccupationType(loanApplicationDto.getOccupationType());
		loanApplication.setCurrentEmployementPeriod(loanApplicationDto.getCurrentEmployementPeriod());
		loanApplication.setForm16(loanApplicationDto.getForm16());
		loanApplication.setLoanTenure(loanApplicationDto.getLoanTenure());
		loanApplication.setPhoto(loanApplicationDto.getPhoto());
		loanApplication.setSalarySlip(loanApplicationDto.getSalarySlip());
		loanApplication.setSignature(loanApplicationDto.getSignature());
		loanApplication.setTotalEmi(loanApplicationDto.getTotalEmi());
		loanApplication.setLoanAmount(loanApplicationDto.getLoanAmount());
		loanApplication.setOtherLoans(loanApplicationDto.isOtherLoans());

		return loanApplication;

	}

}
