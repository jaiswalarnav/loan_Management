package com.user.loan_Management.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.constants.StatusCode;
import com.user.loan_Management.dto.AdminDto;
import com.user.loan_Management.dto.AdminRegisterDto;
import com.user.loan_Management.http.response.DataResponse;
import com.user.loan_Management.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
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
	}
