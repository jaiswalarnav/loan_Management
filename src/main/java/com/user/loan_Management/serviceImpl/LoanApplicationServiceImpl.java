package com.user.loan_Management.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.loan_Management.dto.LoanApplicationDto;
import com.user.loan_Management.dtoToModel.LoanApplicationDtoToLoanApplication;
import com.user.loan_Management.model.LoanApplication;
import com.user.loan_Management.repository.LoanApplicationRepository;
import com.user.loan_Management.service.LoanApplicationService;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
	
	@Autowired
	LoanApplicationRepository loanApplicationRepository;
	
//	@Autowired

	
	public long createLoanApplication(LoanApplicationDto loanApplicationDto) throws Exception{
		
		LoanApplication loanApplication=loanApplicationRepository.findByPanNo(loanApplicationDto.getPanNo());
		
		if(loanApplication != null)
			throw new RuntimeException("LOAN_APPLICATION_IN_PROCESS");
		LoanApplicationDtoToLoanApplication loanApplicationDtoToLoanApplication=new LoanApplicationDtoToLoanApplication();
		
		 loanApplication=loanApplicationDtoToLoanApplication.dtoToLoanApplication(loanApplicationDto);
		 
		 loanApplicationRepository.save(loanApplication);
		
		  long applicationNo=loanApplicationRepository.findByPanNo(loanApplication.getPanNo()).getId();
		 
		  return applicationNo;
		
			
	}

}
