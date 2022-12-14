package com.user.loan_Management.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.loan_Management.ModelToDto.LoanDetailsToLoanDetailsDto;
import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.dto.CalculateEmiDto;
import com.user.loan_Management.dto.LoanApplicationDto;
import com.user.loan_Management.dto.LoanApplicationStatusDto;
import com.user.loan_Management.dto.LoanDetailsDto;
import com.user.loan_Management.dtoToModel.LoanApplicationDtoToLoanApplication;
import com.user.loan_Management.model.LoanApplication;
import com.user.loan_Management.repository.LoanApplicationRepository;
import com.user.loan_Management.repository.LoanDetailsRepository;
import com.user.loan_Management.service.LoanApplicationService;
import java.lang.Math;


@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Autowired
	LoanDetailsRepository loanDetailsRepository;

	public long createLoanApplication(LoanApplicationDto loanApplicationDto) throws Exception {

		LoanApplication loanApplication = loanApplicationRepository.findByPanNo(loanApplicationDto.getPanNo());

		if (loanApplication != null)
			throw new RuntimeException(ConstantMessage.LOAN_APPLICATION_IN_PROCESS);
		LoanApplicationDtoToLoanApplication loanApplicationDtoToLoanApplication = new LoanApplicationDtoToLoanApplication();
		loanApplicationDto.setApplicationStatus(ConstantMessage.IN_PROCESS);
		loanApplication = loanApplicationDtoToLoanApplication.dtoToLoanApplication(loanApplicationDto);

		loanApplicationRepository.save(loanApplication);

		long applicationNo = loanApplicationRepository.findByPanNo(loanApplication.getPanNo()).getId();

		return applicationNo;

	}

	public String getLoanApplicationStatus(LoanApplicationStatusDto loanApplicationStatusDto) throws Exception {

		Optional<LoanApplication> loanApplication = loanApplicationRepository
				.findById(loanApplicationStatusDto.getApplicationNo());

		if (!(loanApplication.isPresent())) {
			throw new RuntimeException(ConstantMessage.INVALID_APPLICATION_NUMBER);
		}
		
		if(!(loanApplicationStatusDto.getDob().equals(loanApplication.get().getDob())))
			throw new RuntimeException(ConstantMessage.INVALID_CREDENTIALS);

		return loanApplication.get().getApplicationStatus();
	}
	
	public LoanDetailsDto viewLoanDetails() throws Exception {

		LoanDetailsToLoanDetailsDto loanDetailsToLoanDetailsDto = new LoanDetailsToLoanDetailsDto();

		if (loanDetailsRepository.findAll().get(0) == null)
			throw new RuntimeException(ConstantMessage.NO_CONTENT);

		return (loanDetailsToLoanDetailsDto.LoanDetailsToDto(loanDetailsRepository.findAll().get(0)));
	}
	
	
	
	public double calculateEmi(CalculateEmiDto calculateEmiDto) throws Exception {

		long loanAmount = calculateEmiDto.getLoanAmount();

		float roi = calculateEmiDto.getRoi();

		int tenure = calculateEmiDto.getTenure();

		double emi = (loanAmount * (roi / 1200)) * (Math.pow((1 + (roi / 1200)), tenure))
				/ (Math.pow((1 + (roi / 1200)), tenure) - 1);

		return emi;
	}

}
