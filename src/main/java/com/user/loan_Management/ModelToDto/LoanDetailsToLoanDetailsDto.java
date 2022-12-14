package com.user.loan_Management.ModelToDto;

import com.user.loan_Management.dto.LoanDetailsDto;
import com.user.loan_Management.model.LoanDetails;

public class LoanDetailsToLoanDetailsDto {
	LoanDetailsDto loanDetailsDto = new LoanDetailsDto();

	public LoanDetailsDto LoanDetailsToDto(LoanDetails loanDetails) {
		loanDetailsDto.setLoanAmount(loanDetails.getLoanAmount());
		loanDetailsDto.setLoanTenure(loanDetails.getLoanTenure());
		loanDetailsDto.setProcessingFee(loanDetails.getProcessingFee());
		loanDetailsDto.setRoi(loanDetails.getRoi());

		return loanDetailsDto;
	}

}
