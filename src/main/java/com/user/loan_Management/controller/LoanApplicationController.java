package com.user.loan_Management.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.constants.StatusCode;
import com.user.loan_Management.dto.CalculateEmiDto;
import com.user.loan_Management.dto.LoanApplicationDto;
import com.user.loan_Management.dto.LoanApplicationStatusDto;
import com.user.loan_Management.dto.LoanDetailsDto;
import com.user.loan_Management.http.response.DataResponse;
import com.user.loan_Management.model.support.FileUploadSupport;
import com.user.loan_Management.service.LoanApplicationService;
import com.user.loan_Management.validator.Validations;

@RestController
@RequestMapping("/loan/personal-loan")
public class LoanApplicationController {

	@Autowired
	LoanApplicationService loanApplicationService;

	@Autowired
	FileUploadSupport fileUploadSupport;

	// This handler is for applying loan

	@PostMapping("/apply-loan")
	public DataResponse postLoanApplication( @RequestParam("LoanApplicationDto") String loanApplicationDto1,
			@RequestParam("form16") MultipartFile multipartFile) {
		try {
			// System.out.println(loanApplicationDto);

			LoanApplicationDto loanApplicationDto = new ObjectMapper().readValue(loanApplicationDto1,
					LoanApplicationDto.class);

			Validations validations = new Validations();

			String[] requestedArray = { "name", "dob", "contactNo", "mail", "gender", "address", "panNo", "aadharNo",
					"monthlyIncome", "currentEmployementPeriod", "photo", "signature", "form16", "salarySlip",
					"otherLoans", "totalEmi", "loanAmount", "loanTenure", "occupationType", "employerName" };

			DataResponse res = validations.validate(loanApplicationDto, requestedArray);

			if (res != null)
				return res;

			// System.out.println(contactDto);
//
//			if (bindingResult.hasErrors()) {
//				List<String> message = new ArrayList<String>();
//				for (Object object : bindingResult.getAllErrors()) {
//
//					if (object instanceof FieldError) {
//
//						FieldError fieldError = (FieldError) object;
//
//						message.add(fieldError.getDefaultMessage());
//
//					}
//				}
//
//				return new DataResponse(StatusCode.BAD_REQUEST, ConstantMessage.BAD_REQUEST, message);
//			}

			if (loanApplicationDto.getMonthlyIncome() < 15000)
				throw new RuntimeException(ConstantMessage.INSUFFICIENT_INCOME);

			if (multipartFile.isEmpty())
				throw new RuntimeException(ConstantMessage.FILE_NOT_FOUND);

			if (!multipartFile.getContentType().equals("image/png"))
				throw new RuntimeException(ConstantMessage.FILE_EXTENSION_ERROR);

			boolean uploadFile = fileUploadSupport.uploadFile(multipartFile);

			if (!uploadFile)
				throw new RuntimeException(ConstantMessage.INTERNAL_SERVER_ERROR);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dob = loanApplicationDto.getDob();

			int age = Period.between(LocalDate.parse(dob, formatter), LocalDate.now()).getYears();

			if (!(age >= 18 && age <= 60))
				throw new RuntimeException(ConstantMessage.AGE_EXCEPTION);

			loanApplicationDto.setForm16(multipartFile.getOriginalFilename());

			long applicationNo = loanApplicationService.createLoanApplication(loanApplicationDto);

			 String mailMessage=  loanApplicationService.sendAdminEmail(applicationNo);
			 
			 if(mailMessage!= ConstantMessage.MAIL_SENT)
				 throw new RuntimeException(ConstantMessage.MAIL_ERROR);
			
			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.SUCCESSFUL_APPLICATION,
					ConstantMessage.APPLICATION_NUMBER + applicationNo);

		} catch (Exception e) {

			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}

	}

	// This handler is to check loan application status

	@GetMapping("/loan-status")
	public DataResponse getLoanApplicationStatus(@Valid @RequestBody LoanApplicationStatusDto loanApplicationStatusDto,
			BindingResult bindingResult) {

		try {
			if (bindingResult.hasErrors()) {
				List<String> errorMessage = new ArrayList<String>();
				for (Object object : bindingResult.getAllErrors()) {
					if (object instanceof FieldError) {
						FieldError fieldError = (FieldError) object;
						errorMessage.add(fieldError.getDefaultMessage());
					}
				}
				return new DataResponse(StatusCode.BAD_REQUEST, ConstantMessage.BAD_REQUEST, errorMessage);
			}

			String status = loanApplicationService.getLoanApplicationStatus(loanApplicationStatusDto);

			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.OK,
					ConstantMessage.APPLICATION_STATUS + status);

		} catch (Exception e) {
			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());

		}
	}

	// this handler is to view loan details

	@GetMapping("/loan-details")
	public DataResponse viewLoanDetails() {
		try {
			LoanDetailsDto loanDetailsDto = loanApplicationService.viewLoanDetails();

			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.OK, loanDetailsDto);
		} catch (Exception e) {

			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}

	}

	// this handler is to calculate emi

	@PostMapping("calculate-emi")
	public DataResponse calculateEmi(@Valid @RequestBody CalculateEmiDto calculateEmiDto, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<String> errorMessage = new ArrayList<String>();

				for (Object object : bindingResult.getAllErrors()) {
					if (object instanceof FieldError) {
						FieldError fieldError = (FieldError) object;
						errorMessage.add(fieldError.getDefaultMessage());
					}
				}

				return new DataResponse(StatusCode.BAD_REQUEST, ConstantMessage.BAD_REQUEST, errorMessage);
			}
			int emi = (int) Math.round(loanApplicationService.calculateEmi(calculateEmiDto));

			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.OK, ConstantMessage.EMI_MESSAGE + emi);
		}

		catch (Exception e) {
			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}
}
