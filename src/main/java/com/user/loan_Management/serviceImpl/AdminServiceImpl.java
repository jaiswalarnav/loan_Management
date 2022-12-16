package com.user.loan_Management.serviceImpl;


import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.dto.AdminDto;
import com.user.loan_Management.dto.AdminRegisterDto;
import com.user.loan_Management.dtoToModel.AdminRegisterDtoToAdminRegister;
import com.user.loan_Management.model.AdminRegister;
import com.user.loan_Management.model.Token;
import com.user.loan_Management.repository.AdminRepository;
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
	
	public String getToken(long id) {

		String adminId = "" + id;
		String token = Jwts.builder().setSubject(adminId)
				.setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
				.signWith(SignatureAlgorithm.HS512, "MustBeUniqueEverwhere").compact();

		return token;
	}


}
