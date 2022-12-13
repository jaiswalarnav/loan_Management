package com.user.loan_Management.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.dto.LoanApplicationDto;
import com.user.loan_Management.dto.LoanApplicationStatusDto;
import com.user.loan_Management.dtoToModel.LoanApplicationDtoToLoanApplication;
import com.user.loan_Management.model.LoanApplication;
import com.user.loan_Management.repository.LoanApplicationRepository;
import com.user.loan_Management.service.LoanApplicationService;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

//	@Autowired

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

		if (loanApplication.isPresent()) {
			throw new RuntimeException(ConstantMessage.INVALID_APPLICATION_NUMBER);
		}
		
		if(!(loanApplicationStatusDto.getDob().equals(loanApplication.get().getDob())))
			throw new RuntimeException(ConstantMessage.INVALID_CREDENTIALS);

		return loanApplication.get().getApplicationStatus();
	}

}
