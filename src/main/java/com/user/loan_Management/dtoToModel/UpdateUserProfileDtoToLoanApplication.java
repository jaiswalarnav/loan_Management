package com.user.loan_Management.dtoToModel;

import com.user.loan_Management.dto.UserUpdateProfileDto;
import com.user.loan_Management.model.LoanApplication;

public class UpdateUserProfileDtoToLoanApplication {
	
	public LoanApplication updateUserProfileDtoToLoanApplication(UserUpdateProfileDto userUpdateProfileDto,LoanApplication loanApplication) {
		
		loanApplication.setAadharNo(loanApplication.getAadharNo());
		loanApplication.setAddress(userUpdateProfileDto.getAddress());
		loanApplication.setMonthlyIncome(loanApplication.getMonthlyIncome());
		loanApplication.setApplicationStatus(loanApplication.getApplicationStatus());
		loanApplication.setContactNo("+91" + userUpdateProfileDto.getContactNo());
		loanApplication.setDob(loanApplication.getDob());
		loanApplication.setGender(loanApplication.getGender());
		loanApplication.setMailId(userUpdateProfileDto.getMailId());
		loanApplication.setMarried(userUpdateProfileDto.isMarried());
		loanApplication.setName(loanApplication.getName());
		loanApplication.setEmployerName(loanApplication.getEmployerName());
		loanApplication.setPanNo(loanApplication.getPanNo());
		loanApplication.setOccupationType(loanApplication.getOccupationType());
		loanApplication.setCurrentEmployementPeriod(loanApplication.getCurrentEmployementPeriod());
		loanApplication.setForm16(loanApplication.getForm16());
		loanApplication.setLoanTenure(loanApplication.getLoanTenure());
		loanApplication.setPhoto(loanApplication.getPhoto());
		loanApplication.setSalarySlip(loanApplication.getSalarySlip());
		loanApplication.setSignature(loanApplication.getSignature());
		loanApplication.setTotalEmi(loanApplication.getTotalEmi());
		loanApplication.setLoanAmount(loanApplication.getLoanAmount());
		loanApplication.setOtherLoans(loanApplication.isOtherLoans());
		
		return loanApplication;
		
	}

}
