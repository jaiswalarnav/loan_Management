package com.user.loan_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.constants.StatusCode;
import com.user.loan_Management.dto.UserLoginReturnDto;
import com.user.loan_Management.dto.UserViewProfileDto;
import com.user.loan_Management.http.response.DataResponse;
import com.user.loan_Management.repository.LoanApplicationRepository;
import com.user.loan_Management.service.UserService;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;

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
			HttpServletRequest request = (HttpServletRequest) req;

			if (request.getHeader("Authorization") == null) {
				throw new RuntimeException(ConstantMessage.UNAUTHORIZED_USER);
			}
			String id = Jwts.parser().setSigningKey("MustBeUniqueEverwhere")
					.parseClaimsJws(request.getHeader("Authorization")).getBody().getSubject();
			long userId = Long.parseLong(id);

			UserViewProfileDto userViewProfileDto = userService.viewUserProfile(userId);

			return new DataResponse(StatusCode.SUCCESS, ConstantMessage.OK, userViewProfileDto);
		} catch (Exception e) {
			return new DataResponse(StatusCode.INTERNAL_SERVER_ERROR, ConstantMessage.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}

	}
}
