package com.user.loan_Management.serviceImpl;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.loan_Management.ModelToDto.LoanApplicationToUserViewProfileDto;
import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.dto.UserLoginReturnDto;
import com.user.loan_Management.dto.UserViewProfileDto;
import com.user.loan_Management.model.LoanApplication;
import com.user.loan_Management.model.Token;
import com.user.loan_Management.repository.LoanApplicationRepository;
import com.user.loan_Management.repository.TokenRepository;
import com.user.loan_Management.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.Jwts;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Autowired
	TokenRepository tokenRepository;

	public UserLoginReturnDto userLogin(long applicationNo, String dob) throws Exception {
		Optional<LoanApplication> loanApplication = loanApplicationRepository.findById(applicationNo);
		if (!(loanApplication.isPresent()))
			throw new RuntimeException(ConstantMessage.INVALID_APPLICATION_NUMBER);

		if (!loanApplication.get().getDob().equals(dob))
			throw new RuntimeException(ConstantMessage.INVALID_CREDENTIALS);
		UserLoginReturnDto userLoginReturnDto = new UserLoginReturnDto();

		Token token = new Token();
		token.setToken(getToken(loanApplication.get().getId()));

		tokenRepository.save(token);

		userLoginReturnDto.setName(loanApplication.get().getName());
		userLoginReturnDto.setUserToken(token.getToken());
		return userLoginReturnDto;

	}

	public UserViewProfileDto viewUserProfile(long applicationNo) throws Exception {
		Optional<LoanApplication> loanApplication = loanApplicationRepository.findById(applicationNo);

		if (!loanApplication.isPresent())
			throw new RuntimeException(ConstantMessage.INTERNAL_SERVER_ERROR);

		LoanApplicationToUserViewProfileDto loanApplicationToUserViewProfileDto = new LoanApplicationToUserViewProfileDto();

		UserViewProfileDto userViewProfileDto = loanApplicationToUserViewProfileDto
				.loanApplicationToUserViewProfileDto(loanApplication.get());

		return userViewProfileDto;
	}

	public String getToken(long applicationNo) {

		String userId = "" + applicationNo;
		String token = Jwts.builder().setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
				.signWith(SignatureAlgorithm.HS512, "MustBeUniqueEverwhere").compact();

		return token;
	}

}