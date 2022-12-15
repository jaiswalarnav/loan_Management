package com.user.loan_Management.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.dto.AdminRegisterDto;
import com.user.loan_Management.dtoToModel.AdminRegisterDtoToAdminRegister;
import com.user.loan_Management.model.AdminRegister;
import com.user.loan_Management.repository.AdminRepository;
import com.user.loan_Management.service.AdminService;

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
	
	
//	void loginAdmin(AdminDto adminDto)throws Exception{
//		
//		if(adminDto.getId()==null)
//			throw new RuntimeException(ConstantMessage.BAD_REQUEST);
//		bCryptPasswordEncoder.matches(adminDto.getPassword(), ), null)
//		
//		
//	}
	
	public long registerAdmin(AdminRegisterDto adminRegisterDto)throws Exception {

		
		if(!(adminRepository.findByName(adminRegisterDto.getName())==null))
			throw new RuntimeException(ConstantMessage.ADMIN_ALREADY_EXIST);
			
		
		AdminRegister adminRegister = new AdminRegister();
		modelMapper.map(adminRegisterDto, adminRegister);

		adminRegister.setPassword(bCryptPasswordEncoder.encode(adminRegister.getPassword()));

		return adminRepository.save(adminRegister).getId();

	}

}
