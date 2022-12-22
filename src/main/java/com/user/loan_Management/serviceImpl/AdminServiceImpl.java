package com.user.loan_Management.serviceImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.dto.AdminDto;
import com.user.loan_Management.dto.AdminRegisterDto;
import com.user.loan_Management.dto.ViewLoanApplicationDto;
import com.user.loan_Management.dtoToModel.AdminRegisterDtoToAdminRegister;
import com.user.loan_Management.model.Aadhar;
import com.user.loan_Management.model.AdminRegister;
import com.user.loan_Management.model.Cibil;
import com.user.loan_Management.model.IncomeTax;
import com.user.loan_Management.model.LoanApplication;
import com.user.loan_Management.model.Pan;
import com.user.loan_Management.model.Token;
import com.user.loan_Management.repository.AadharRepository;
import com.user.loan_Management.repository.AdminRepository;
import com.user.loan_Management.repository.CibilRepository;
import com.user.loan_Management.repository.IncomeTaxRepository;
import com.user.loan_Management.repository.LoanApplicationRepository;
import com.user.loan_Management.repository.PanRepository;
import com.user.loan_Management.repository.TokenRepository;
import com.user.loan_Management.service.AdminService;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	AdminRegisterDtoToAdminRegister adminRegisterDtoToAdminRegister;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	BCryptPasswordEncoder  bCryptPasswordEncoder;
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
	LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	AadharRepository aadharRepository;
	
	@Autowired
	PanRepository panRepository;
	
	@Autowired
	CibilRepository cibilRepository;
	
	@Autowired
	IncomeTaxRepository incomeTaxRepository;
	
	
	public String loginAdmin(AdminDto adminDto) throws Exception {

		Optional<AdminRegister> adminRegister = adminRepository.findById((long) adminDto.getId());

		if (!adminRegister.isPresent())
			throw new RuntimeException(ConstantMessage.ADMIN_NOT_EXIST);
		
		

		if (!bCryptPasswordEncoder.matches(adminDto.getPassword(), adminRegister.get().getPassword()))
			throw new RuntimeException(ConstantMessage.INVALID_CREDENTIALS);
		
		Token token = new Token();
		token.setToken(getToken(adminDto.getId()));
		return tokenRepository.save(token).getToken();
		
		

	}
	
	public long registerAdmin(AdminRegisterDto adminRegisterDto)throws Exception {

		
		if(!(adminRepository.findByName(adminRegisterDto.getName())==null))
			throw new RuntimeException(ConstantMessage.ADMIN_ALREADY_EXIST);
			
		
		AdminRegister adminRegister = new AdminRegister();
		modelMapper.map(adminRegisterDto, adminRegister);

		adminRegister.setPassword(bCryptPasswordEncoder.encode(adminRegister.getPassword()));

		return adminRepository.save(adminRegister).getId();

	}
	
	public List<ViewLoanApplicationDto> viewAllLoanApplications(String applicationStatus) throws Exception {

		List<LoanApplication> loanApplications=loanApplicationRepository.findAllByApplicationStatus(applicationStatus);
		List<ViewLoanApplicationDto> viewLoanApplicationDtos=new ArrayList<ViewLoanApplicationDto>();
		 Iterator<LoanApplication> iterator=   loanApplications.iterator();
		 while(iterator.hasNext()) {
			 LoanApplication loanApplication= iterator.next();
			 ViewLoanApplicationDto viewLoanApplicationDto=new ViewLoanApplicationDto();
			 modelMapper.map(loanApplication, viewLoanApplicationDto);
			 viewLoanApplicationDtos.add(viewLoanApplicationDto);
		 }
		
		return viewLoanApplicationDtos;
	}
	
	
	
	public LoanApplication viewLoanApplication(long applicationNo) throws Exception {
		Optional<LoanApplication> loanApplication = loanApplicationRepository.findById(applicationNo);
		if (!loanApplication.isPresent())
			throw new RuntimeException(ConstantMessage.INVALID_APPLICATION_NUMBER);
		return loanApplication.get();
	}
	
	
	public String verifyLoanApplication(long applicationNo) throws Exception {

		Optional<LoanApplication> loanApplication = loanApplicationRepository.findById(applicationNo);

		if (!loanApplication.isPresent())
			throw new RuntimeException(ConstantMessage.INVALID_APPLICATION_NUMBER);
		LoanApplication loanApp = loanApplication.get();
		boolean verifyAadhar = verifyAadhar(loanApp);
		boolean verifyPan = verifyPan(loanApp);
		boolean verifyCibil = verifyCibil(loanApp);
		boolean verifyTax = verifyTax(loanApp);

		if (verifyAadhar == true && verifyPan == true && verifyCibil == true && verifyTax == true) {

			// int requiredAmount = loanApp.getLoanAmount();

			int cibilScore = cibilRepository.findByPanNo(loanApp.getPanNo()).getCibilScore();

			float roi = 0.0f;

			int loanTenure = loanApp.getLoanTenure();

			if ((cibilScore >= 750 && cibilScore <= 800) && (loanTenure >= 6 && loanTenure <= 12))
				roi = 20;
			else if ((cibilScore >= 750 && cibilScore <= 800) && (loanTenure > 12 && loanTenure <= 36))
				roi = 18;
			else if ((cibilScore >= 750 && cibilScore <= 800) && (loanTenure > 36 && loanTenure <= 60))
				roi = 16;
			else if ((cibilScore > 800 && cibilScore <= 850) && (loanTenure >= 6 && loanTenure <= 12))
				roi = 16;
			else if ((cibilScore > 800 && cibilScore <= 850) && (loanTenure > 12 && loanTenure <= 36))
				roi = 15;
			else if ((cibilScore > 800 && cibilScore <= 850) && (loanTenure > 36 && loanTenure <= 60))
				roi = 13.5f;
			else if ((cibilScore > 850 && cibilScore <= 900) && (loanTenure >= 6 && loanTenure <= 12))
				roi = 14;
			else if ((cibilScore > 850 && cibilScore <= 900) && (loanTenure > 12 && loanTenure <= 36))
				roi = 13;
			else
				roi = 12;

			int emi = (int)(Math.round((loanApp.getLoanAmount() * (roi / 1200d))
					* (Math.pow((1 + (roi / 1200d)), loanApp.getLoanTenure()))
					/ (Math.pow((1 + (roi / 1200d)), loanApp.getLoanTenure()) - 1)));

			int repaymentCapability = (int) (loanApp.getMonthlyIncome() - 0.6 * loanApp.getMonthlyIncome())
					- loanApp.getTotalEmi();

			if (repaymentCapability >= emi && verifyAadhar == true && verifyCibil == true && verifyPan == true
					&& verifyTax == true) {
				loanApp.setApplicationStatus(ConstantMessage.APPROVED);
				loanApp.setMessage(ConstantMessage.APPROVE_ROI + roi + "% " + ConstantMessage.APPROVE_EMI + emi);
			}

			else if (repaymentCapability < emi && verifyAadhar == true && verifyCibil == true && verifyPan == true
					&& verifyTax == true) {

				int eligibleLoanAmount = (int)(Math.round( (emi * (Math.pow(1 + (roi / 100), loanTenure) - 1))
						/ ((roi / 100) * Math.pow(1 + (roi / 100), loanTenure))));

				loanApp.setApplicationStatus(ConstantMessage.APPROVED);
				loanApp.setMessage(ConstantMessage.APPROVE_LOAN_AMOUNT + eligibleLoanAmount + " "
						+ ConstantMessage.APPROVE_ROI + roi + "% " + ConstantMessage.APPROVE_EMI + emi);
			} else {

				loanApp.setApplicationStatus(ConstantMessage.REJECTED);
				loanApp.setMessage(ConstantMessage.REJECT_MESSAGE);

			}
		} else {

			loanApp.setApplicationStatus(ConstantMessage.REJECTED);
			loanApp.setMessage(ConstantMessage.REJECT_MESSAGE);

		}

		loanApplicationRepository.save(loanApp);
		return loanApplication.get().getApplicationStatus();

	}
	
	
	
	
	public String getToken(long id) {

		String adminId = "" + id;
		String token = Jwts.builder().setSubject(adminId)
				.setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
				.signWith(SignatureAlgorithm.HS512, "MustBeUniqueEverwhere").compact();

		return token;
	}
	
	public boolean verifyAadhar(LoanApplication loanApplication) {
		Aadhar aadhar = aadharRepository.findByAadharNo(loanApplication.getAadharNo());
		if (aadhar == null)
			throw new RuntimeException(ConstantMessage.INVALID_AADHAR);
		if (loanApplication.getAadharNo().equals(aadhar.getAadharNo())
				&& loanApplication.getName().equalsIgnoreCase(aadhar.getName())
				&& loanApplication.getDob().equalsIgnoreCase(aadhar.getDob())
				&& loanApplication.getGender().equalsIgnoreCase(aadhar.getGender()))
			return true;
		else
			return false;

	}
	
	public boolean verifyPan(LoanApplication loanApplication) {
		Pan pan = panRepository.findByPanNo(loanApplication.getPanNo());
		if (pan == null)
			throw new RuntimeException(ConstantMessage.INVALID_PAN);
		if (loanApplication.getPanNo().equals(pan.getPanNo())
				&& loanApplication.getName().equalsIgnoreCase(pan.getName())
				&& loanApplication.getDob().equalsIgnoreCase(pan.getDob())
				&& loanApplication.getGender().equalsIgnoreCase(pan.getGender()))
			return true;
		else
			return false;
	}
	
	public boolean verifyCibil(LoanApplication loanApplication) {
		Cibil cibil = cibilRepository.findByPanNo(loanApplication.getPanNo());
		if (cibil == null)
			throw new RuntimeException(ConstantMessage.INVALID_PAN);
		if (cibil.getCibilScore() >= 750 && cibil.getCibilScore() <= 900)
			return true;
		else
			return false;

	}

	public boolean verifyTax(LoanApplication loanApplication) {
		IncomeTax incomeTax = incomeTaxRepository.findByPanNo(loanApplication.getPanNo());
		if (incomeTax == null)
			throw new RuntimeException(ConstantMessage.INVALID_PAN);
		// if(incomeTax.getTaxPaidAmount()== loanApplication.getf)
		return true;
	}

}
