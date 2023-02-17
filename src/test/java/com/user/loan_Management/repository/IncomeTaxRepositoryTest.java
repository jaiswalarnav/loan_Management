package com.user.loan_Management.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.loan_Management.model.IncomeTax;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class IncomeTaxRepositoryTest {

	@Autowired
	IncomeTaxRepository incomeTaxRepository;
	
	
	@Test
	void testFindByPanNo() {
		IncomeTax incomeTax=new IncomeTax();
		incomeTax.setPanNo("ABCDE1010J");
		incomeTax.setTaxPaidAmount(100l);
		
		IncomeTax expectedResult= incomeTaxRepository.save(incomeTax);
		
		IncomeTax actualResult= incomeTaxRepository.findByPanNo("ABCDE1010J");
		
		assertEquals(expectedResult, actualResult);
		
	}
	
	@AfterAll
	void cleanUp() {
		System.out.println("cleaning up Income tac repository");
		incomeTaxRepository.deleteAll();
	}
	
	

}
