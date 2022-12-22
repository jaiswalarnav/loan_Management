package com.user.loan_Management.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.constants.StatusCode;
import com.user.loan_Management.dto.AdminDto;
import com.user.loan_Management.dto.AdminRegisterDto;
import com.user.loan_Management.http.response.DataResponse;
import com.user.loan_Management.repository.LoanApplicationRepository;
import com.user.loan_Management.service.AdminService;
import jakarta.servlet.ServletRequest;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	LoanApplicationRepository loanApplicationRepository;
	
	//this handler is for admin login
	
	@PostMapping("/login")
	public DataResponse login(@Valid @RequestBody AdminDto adminDto, BindingResult bindingResult) {

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
			
			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.LOGIN_SUCCESS + ConstantMessage.LOGIN_MESSAGE,
					adminService.loginAdmin(adminDto));

		} catch (Exception e) {
			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}
		
		
		//this handler is for admin registration
		
		@PostMapping("/register")
		public DataResponse register(@Valid @RequestBody AdminRegisterDto adminRegisterDto,
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

				return new DataResponse(StatusCode.SUCCESS, ConstantMessage.OK,
						"Your Admin Id : " + adminService.registerAdmin(adminRegisterDto));

			} catch (Exception e) {

				return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
						e.getMessage());

			}
		}
		
		
		
		//this handler is to view all loan applications
		
		@GetMapping("/view-all-loan-applications")
		public DataResponse viewAllLoanApplications( @RequestParam String applicationStatus
		) {

			try {
				if(applicationStatus.isBlank())
				{
					throw new RuntimeException(ConstantMessage.APPLICATION_STATUS_ERROR);
				}
				
				return new DataResponse(StatusCode.SUCCESS, ConstantMessage.OK,
						adminService.viewAllLoanApplications(applicationStatus));
			} catch (Exception e) {
				return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
						e.getMessage());
			}

		}
		
		
		//this handler is to view a particular loan application
		
		@GetMapping("/view-all-loan-applications/{applicationNo}")
		public DataResponse viewLoanApplication(@PathVariable long applicationNo) {

			try {
				
				return new DataResponse(StatusCode.SUCCESS, ConstantMessage.OK,
						adminService.viewLoanApplication(applicationNo));

			} catch (Exception e) {
				return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
						e.getMessage());
			}

		}
		
		
		
		//this handler is to verify loan application
		
		@PostMapping("/verify-loan-application/{applicationNo}")
		public DataResponse verifyLoanApplication(@PathVariable long applicationNo) {
			try {
				if (!loanApplicationRepository.findById(applicationNo).isPresent())
					throw new RuntimeException(ConstantMessage.INVALID_APPLICATION_NUMBER);

				if (!(loanApplicationRepository.findById(applicationNo).get().getApplicationStatus()
						.equals(ConstantMessage.IN_PROCESS)))
					throw new RuntimeException(ConstantMessage.APPLICATION_VERIFIED);

				return new DataResponse(StatusCode.SUCCESS, ConstantMessage.OK,
						adminService.verifyLoanApplication(applicationNo));

			} catch (Exception e) {
				return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
						e.getMessage());
			}
		}
	}
