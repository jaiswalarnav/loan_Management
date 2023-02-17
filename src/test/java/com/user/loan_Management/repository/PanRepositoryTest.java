package com.user.loan_Management.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.loan_Management.model.Pan;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class PanRepositoryTest {

	@Autowired
	PanRepository panRepository;
	
	@Disabled
	@Test
	void testFindByPanNo() {
		
		Pan pan=new Pan();
		pan.setAddress("vijay nagar");
		pan.setDob("16/10/1998");
		pan.setEmail("arnav@gmail.com");
		pan.setGender("male");
		pan.setName("arnav");
		pan.setPanNo("BKTPJ1677P");
		pan.setPhoneNo("9999999999");
		
		Pan expectedResult= panRepository.save(pan);
		
		Pan actualResult= panRepository.findByPanNo("BKTPJ1677P");
		
		assertEquals(expectedResult, actualResult);
		
		
		
		
		
	}
	
	@Disabled
	@AfterAll
	void cleanUp() {
		System.out.println("cleaning pan repository");
		panRepository.deleteAll();
	}

}
