package com.user.loan_Management.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.constants.StatusCode;
import com.user.loan_Management.dto.UserLoginReturnDto;
import com.user.loan_Management.dto.UserUpdateProfileDto;
import com.user.loan_Management.dto.UserViewProfileDto;
import com.user.loan_Management.http.response.DataResponse;
import com.user.loan_Management.repository.LoanApplicationRepository;
import com.user.loan_Management.service.UserService;
import jakarta.servlet.ServletRequest;


@RestController
@RequestMapping("loan/personal-loan/user")
public class UserController {

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Autowired
	UserService userService;

	// this handler is for user login

	@PostMapping("/login")
	public DataResponse userLogin(@RequestParam Long applicationNo, @RequestParam String dob) {
		try {

			UserLoginReturnDto userLoginReturnDto = userService.userLogin(applicationNo, dob);

			return new DataResponse(200,
					ConstantMessage.LOGIN_SUCCESS + userLoginReturnDto.getName() + ConstantMessage.LOGIN_MESSAGE,
					userLoginReturnDto);

		} catch (Exception e) {
			return new DataResponse(500, ConstantMessage.INTERNAL_SERVER_ERROR, e.getMessage());
		}

	}

	// this handler is for viewing user profile

	@GetMapping("/view-profile")
	public DataResponse viewProfile(ServletRequest req) {

		try {
//			HttpServletRequest request = (HttpServletRequest) req;
//
//			if (request.getHeader("Authorization") == null) {
//				throw new RuntimeException(ConstantMessage.UNAUTHORIZED_USER);
//			}
//			String id = Jwts.parser().setSigningKey("MustBeUniqueEverwhere")
//					.parseClaimsJws(request.getHeader("Authorization")).getBody().getSubject();
			long applicationNo = (long) req.getAttribute("id");

			UserViewProfileDto userViewProfileDto = userService.viewUserProfile(applicationNo);

			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.OK, userViewProfileDto);
		} catch (Exception e) {
			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}

	}
	
	//this handler is to update user profile 
	
	@PutMapping("/update-profile")
	public DataResponse updateProfile(@Valid @RequestBody UserUpdateProfileDto userUpdateProfileDto ,BindingResult bindingResult, ServletRequest req) {
		try {
			
			if (bindingResult.hasErrors()) {
				List<String> message = new ArrayList<String>();
				for (Object object : bindingResult.getAllErrors()) {

					if (object instanceof FieldError) {

						FieldError fieldError = (FieldError) object;

						message.add(fieldError.getDefaultMessage());

					}
				}

				return new DataResponse(StatusCode.BAD_REQUEST, ConstantMessage.BAD_REQUEST, message);
			}
			
			long applicationNo=(long) req.getAttribute("id");
			userService.updateUserProfile(applicationNo, userUpdateProfileDto);
			
			return new DataResponse(StatusCode.SUCCESS,ConstantMessage.LOGIN_SUCCESS,ConstantMessage.UPDATE_SUCCESS);
			
			
			
		}
		catch (Exception e) {
			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
}
