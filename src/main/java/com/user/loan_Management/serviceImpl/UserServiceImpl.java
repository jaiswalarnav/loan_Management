package com.user.loan_Management.serviceImpl;

import java.util.Date;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.dto.UserLoginReturnDto;
import com.user.loan_Management.dto.UserUpdateProfileDto;
import com.user.loan_Management.dto.UserViewProfileDto;
import com.user.loan_Management.dtoToModel.UpdateUserProfileDtoToLoanApplication;
import com.user.loan_Management.model.LoanApplication;
import com.user.loan_Management.model.Token;
import com.user.loan_Management.repository.LoanApplicationRepository;
import com.user.loan_Management.repository.TokenRepository;
import com.user.loan_Management.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public UserLoginReturnDto userLogin(long applicationNo, String dob) throws Exception {
		Optional<LoanApplication> loanApplication = loanApplicationRepository.findById(applicationNo);
		if (!(loanApplication.isPresent()))
			throw new RuntimeException(ConstantMessage.INVALID_APPLICATION_NUMBER);

		if (!loanApplication.get().getDob().equals(dob))
			throw new RuntimeException(ConstantMessage.INVALID_CREDENTIALS);

		Token token = new Token();
		token.setToken(getToken(loanApplication.get().getId()));

		tokenRepository.save(token);

		UserLoginReturnDto userLoginReturnDto = new UserLoginReturnDto();
		userLoginReturnDto.setName(loanApplication.get().getName());
		userLoginReturnDto.setUserToken(token.getToken());
		
		return userLoginReturnDto;

	}

	public UserViewProfileDto viewUserProfile(long applicationNo) throws Exception {
		Optional<LoanApplication> loanApplication = loanApplicationRepository.findById(applicationNo);

		if (!loanApplication.isPresent())
			throw new RuntimeException(ConstantMessage.INTERNAL_SERVER_ERROR);

		UserViewProfileDto userViewProfileDto = new UserViewProfileDto();

		modelMapper.map(loanApplication.get(), userViewProfileDto);

		return userViewProfileDto;
	}
	
	public void updateUserProfile(long applicationNo, UserUpdateProfileDto userUpdateProfileDto) {
		Optional<LoanApplication> loanApplication = loanApplicationRepository.findById(applicationNo);
		
		if (!loanApplication.isPresent())
			throw new RuntimeException(ConstantMessage.INVALID_CREDENTIALS);

		UpdateUserProfileDtoToLoanApplication updateUserProfileDto = new UpdateUserProfileDtoToLoanApplication();
		//modelMapper.map(userUpdateProfileDto, loanApplication.get());
		//loanApplicationRepository.save(loanApplication.get());
		
		loanApplicationRepository.save(updateUserProfileDto.updateUserProfileDtoToLoanApplication(userUpdateProfileDto,
				loanApplication.get()));

	}

	public String getToken(long applicationNo) {

		String userId = "" + applicationNo;
		String token = Jwts.builder().setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
				.signWith(SignatureAlgorithm.HS512, "MustBeUniqueEverwhere").compact();

		return token;
	}

}
