package com.user.loan_Management.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.partitions.model.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.dto.CalculateEmiDto;
import com.user.loan_Management.dto.LoanApplicationDto;
import com.user.loan_Management.dto.LoanApplicationStatusDto;
import com.user.loan_Management.dto.LoanDetailsDto;
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
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AmazonSimpleEmailService amazonSimpleEmailService;

	public long createLoanApplication(LoanApplicationDto loanApplicationDto) throws Exception {

		LoanApplication loanApplication = loanApplicationRepository.findByPanNo(loanApplicationDto.getPanNo());

		if (loanApplication != null)
			throw new RuntimeException(ConstantMessage.LOAN_APPLICATION_IN_PROCESS);
		else
			loanApplication = new LoanApplication();

		loanApplicationDto.setApplicationStatus(ConstantMessage.IN_PROCESS);
		loanApplicationDto.setMessage(ConstantMessage.IN_PROCESS_MESSAGE);
		modelMapper.map(loanApplicationDto, loanApplication);
		System.out.println(loanApplication);

		return loanApplicationRepository.save(loanApplication).getId();
		
		

	}

	public String getLoanApplicationStatus(LoanApplicationStatusDto loanApplicationStatusDto) throws Exception {

		Optional<LoanApplication> loanApplication = loanApplicationRepository
				.findById(loanApplicationStatusDto.getApplicationNo());

		if (!(loanApplication.isPresent())) {
			throw new RuntimeException(ConstantMessage.INVALID_APPLICATION_NUMBER);
		}
		
		if(!(loanApplicationStatusDto.getDob().equals(loanApplication.get().getDob())))
			throw new RuntimeException(ConstantMessage.INVALID_CREDENTIALS);

		return (loanApplication.get().getApplicationStatus()+". "+loanApplication.get().getMessage());
	}
	
	public LoanDetailsDto viewLoanDetails() throws Exception {

		if (loanDetailsRepository.findAll().get(0) == null)
			throw new RuntimeException(ConstantMessage.NO_CONTENT);
		LoanDetailsDto loanDetailsDto = new LoanDetailsDto();
		modelMapper.map(loanDetailsRepository.findAll().get(0), loanDetailsDto);
		return loanDetailsDto;

	}
	
	
	
	public double calculateEmi(CalculateEmiDto calculateEmiDto) throws Exception {

		long loanAmount = calculateEmiDto.getLoanAmount();

		float roi = calculateEmiDto.getRoi();

		int tenure = calculateEmiDto.getTenure();

		double emi = (loanAmount * (roi / 1200)) * (Math.pow((1 + (roi / 1200)), tenure))
				/ (Math.pow((1 + (roi / 1200)), tenure) - 1);

		return emi;
	}

	public String sendAdminEmail(long loanApplicationId)throws Exception {
		
		
		String FROM="arnav.jaiswal@techvalens.com";
		String TO="ankit.shinde@techvalens.com";
		List<String> toAddresses=new ArrayList<String>();
		toAddresses.add(TO);
		Destination destination=new Destination(toAddresses);
		//Regions region=Regions.AP_SOUTH_1;
		
		//AmazonSimpleEmailService client=AmazonSimpleEmailServiceClientBuilder.standard().withRegion(region).build();
		SendEmailRequest sendEmailRequest=new SendEmailRequest();
		sendEmailRequest.setSource(FROM);
		sendEmailRequest.setDestination(destination);
		
		
		
		Content subject=new Content("NEW LOAN APPLICATION");
		Content text=new Content("Hey ADMIN,Please verify the below application! "+'\n'+" Loan Application No is : "+loanApplicationId);
		Body body=new Body(text);
		Message message=new Message(subject, body);
		sendEmailRequest.setMessage(message);
		
		SendEmailResult emailResult=  amazonSimpleEmailService.sendEmail(sendEmailRequest);
		if(emailResult.getMessageId()!=null)
			return ConstantMessage.MAIL_SENT;
			
			
		else
			return ConstantMessage.MAIL_ERROR;
			
		
	}
}
