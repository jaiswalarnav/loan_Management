package com.user.loan_Management.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.loan_Management.model.AdminRegister;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class AdminRepositoryTest {

	@Autowired
	private  AdminRepository adminRepository;
	
	@Disabled
	@Test
	void testFindByName() {
		AdminRegister adminRegister=new AdminRegister();
		adminRegister.setName("Ayush Jain");
		adminRegister.setPassword("12345");
		
		   adminRepository.save(adminRegister);
		   
		   AdminRegister actualResult=  adminRepository.findByName("Ayush Jain");
		   
		   assertEquals(adminRegister, actualResult);
	}
	
	@Disabled
	@AfterAll
	 void cleanUp() {
		System.out.println("cleaning up admin Repository");
		adminRepository.deleteAll();
	}

}
