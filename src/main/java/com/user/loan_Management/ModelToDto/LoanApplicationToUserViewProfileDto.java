package com.user.loan_Management.ModelToDto;

import com.user.loan_Management.dto.UserViewProfileDto;
import com.user.loan_Management.model.LoanApplication;

public class LoanApplicationToUserViewProfileDto {

	public UserViewProfileDto loanApplicationToUserViewProfileDto(LoanApplication loanApplication) {
		UserViewProfileDto userViewProfileDto = new UserViewProfileDto();

		userViewProfileDto.setAddress(loanApplication.getAddress());
		userViewProfileDto.setApplicationStatus(loanApplication.getApplicationStatus());
		userViewProfileDto.setContactNo(loanApplication.getContactNo());
		userViewProfileDto.setDob(loanApplication.getDob());
		userViewProfileDto.setGender(loanApplication.getGender());
		userViewProfileDto.setMailId(loanApplication.getMailId());
		userViewProfileDto.setMarried(loanApplication.isMarried());
		userViewProfileDto.setName(loanApplication.getName());

		return userViewProfileDto;

	}

}
