package com.user.loan_Management.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import com.user.loan_Management.model.LoanApplication;


@SpringBootTest
class LoanApplicationRepositoryTest {

	@Autowired
	private  LoanApplicationRepository loanApplicationRepository;

//	@Test
//	void testFindByPanNo() {
//		fail("Not yet implemented");
//	}

	@Disabled
	@Test
	@DisplayName("testing findAllByApplicationStatus method")
	void testFindAllByApplicationStatus() {

		List<LoanApplication> applicationsExpected = new ArrayList<LoanApplication>();

		LoanApplication loanApplication1 = new LoanApplication();
		loanApplication1.setAadharNo("111111111111");
		loanApplication1.setAddress("Vijay NAgar Indore");
		loanApplication1.setApplicationStatus("Approved");
		loanApplication1.setContactNo("7586878885");
		loanApplication1.setCreatedAt(null);
		loanApplication1.setCurrentEmployementPeriod(0);
		
		loanApplication1.setDeletedAt(null);
		loanApplication1.setDob("05/12/1962");
		loanApplication1.setEmployerName("Techavlens");
		loanApplication1.setForm16("tech.pdf");
		loanApplication1.setGender("male");
		loanApplication1.setIsDeleted(false);
		loanApplication1.setLoanAmount(200000);
		loanApplication1.setLoanTenure(36);
		loanApplication1.setMail("arnav@gmail.com");
		loanApplication1.setMarried(false);
		loanApplication1.setMessage("null");
		loanApplication1.setMonthlyIncome(40000);
		loanApplication1.setName("Arnav Jaiswal");
		loanApplication1.setOccupationType("Salaried");
		loanApplication1.setOtherLoans(false);
		loanApplication1.setPanNo("ABCTY1234M");
		loanApplication1.setPhoto("arnav.jpg");
		loanApplication1.setSalarySlip("NA");
		loanApplication1.setSignature("arnavSignature.jpg");
		loanApplication1.setTotalEmi(0);
		loanApplication1.setUpdatedAt(null);

		LoanApplication loanApplication2 = new LoanApplication();
		loanApplication2.setAadharNo("111111111111");
		loanApplication2.setAddress("Vijay NAgar Indore");
		loanApplication2.setApplicationStatus("Approved");
		loanApplication2.setContactNo("7586878885");
		loanApplication2.setCreatedAt(null);
		loanApplication2.setCurrentEmployementPeriod(0);
		
		loanApplication2.setDeletedAt(null);
		loanApplication2.setDob("05/12/1962");
		loanApplication2.setEmployerName("Techavlens");
		loanApplication2.setForm16("tech.pdf");
		loanApplication2.setGender("male");
		loanApplication2.setIsDeleted(false);
		loanApplication2.setLoanAmount(200000);
		loanApplication2.setLoanTenure(36);
		loanApplication2.setMail("arnav@gmail.com");
		loanApplication2.setMarried(false);
		loanApplication2.setMessage("null");
		loanApplication2.setMonthlyIncome(40000);
		loanApplication2.setName("Arnav Jaiswal");
		loanApplication2.setOccupationType("Salaried");
		loanApplication2.setOtherLoans(false);
		loanApplication2.setPanNo("ABCTY1234N");
		loanApplication2.setPhoto("arnav.jpg");
		loanApplication2.setSalarySlip("NA");
		loanApplication2.setSignature("arnavSignature.jpg");
		loanApplication2.setTotalEmi(0);
		loanApplication2.setUpdatedAt(null);

		LoanApplication getLoanApplication1 = loanApplicationRepository.save(loanApplication1);

		LoanApplication getLoanApplication2 = loanApplicationRepository.save(loanApplication2);

		applicationsExpected.add(getLoanApplication1);
		applicationsExpected.add(getLoanApplication2);

		List<LoanApplication> applicationsActual = loanApplicationRepository.findAllByApplicationStatus("Approved");
		// for(LoanApplication application:loanApplications) {

		assertEquals(applicationsExpected, applicationsActual);
		// }
	}
	
	
	@Test
	void testFindByPanNo() {
		LoanApplication loanApplication1 = new LoanApplication();
		loanApplication1.setAadharNo("111111111111");
		loanApplication1.setAddress("Vijay NAgar Indore");
		loanApplication1.setApplicationStatus("Approved");
		loanApplication1.setContactNo("7586878885");
		loanApplication1.setCreatedAt(null);
		loanApplication1.setCurrentEmployementPeriod(0);
		
		loanApplication1.setDeletedAt(null);
		loanApplication1.setDob("05/12/1962");
		loanApplication1.setEmployerName("Techavlens");
		loanApplication1.setForm16("tech.pdf");
		loanApplication1.setGender("male");
		loanApplication1.setIsDeleted(false);
		loanApplication1.setLoanAmount(200000);
		loanApplication1.setLoanTenure(36);
		loanApplication1.setMail("arnav@gmail.com");
		loanApplication1.setMarried(false);
		loanApplication1.setMessage("null");
		loanApplication1.setMonthlyIncome(40000);
		loanApplication1.setName("Arnav Jaiswal");
		loanApplication1.setOccupationType("Salaried");
		loanApplication1.setOtherLoans(false);
		loanApplication1.setPanNo("ABCTY1234M");
		loanApplication1.setPhoto("arnav.jpg");
		loanApplication1.setSalarySlip("NA");
		loanApplication1.setSignature("arnavSignature.jpg");
		loanApplication1.setTotalEmi(0);
		loanApplication1.setUpdatedAt(null);
		LoanApplication expectedLoanApplication = loanApplicationRepository.save(loanApplication1);
		
		LoanApplication actualLoanApplication = loanApplicationRepository.findByPanNo("ABCTY1234N");
		
		assertEquals(expectedLoanApplication, actualLoanApplication);
		
	}
	
	
//	@AfterEach
//	  void cleanUp() {
//		System.out.println("Cleaning up");
//		loanApplicationRepository.deleteAll();
//	}

}
