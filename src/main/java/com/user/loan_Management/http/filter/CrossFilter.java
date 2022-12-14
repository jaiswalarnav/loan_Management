package com.user.loan_Management.http.filter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.loan_Management.constants.ConstantMessage;
import com.user.loan_Management.model.LoanApplication;
import com.user.loan_Management.repository.LoanApplicationRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CrossFilter implements Filter {

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Override
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain)throws IOException,ServletException{
		HttpServletResponse response=(HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, type");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.addIntHeader("Access-Control-Max-Age", 180);
		
		try {
			HttpServletRequest request=(HttpServletRequest)req;
			String url=request.getRequestURL().toString();
			
			if(url.contains("/loan/personal-loan/user/")&&request.getHeader("Authorization")!=null) {
				
				String id=Jwts.parser().setSigningKey("MustBeUniqueEverwhere").parseClaimsJws(request.getHeader("Authorization")).getBody().getSubject();
				long userId=Long.parseLong(id);
				Optional<LoanApplication> loanApplication=loanApplicationRepository.findById(userId);
				if(loanApplication.isPresent()) {
					req.setAttribute("id", loanApplication.get().getId());
				chain.doFilter(req, res);
				}
				else
					throw new RuntimeException(ConstantMessage.INVALID_CREDENTIALS);
			}
			else if(url.contains("/loan/personal-loan/apply-loan")||url.contains("/loan/personal-loan/loan-status")||url.contains("/loan/personal-loan/loan-details")||url.contains("/loan/personal-loan/calculate-emi")) {
				chain.doFilter(req, res);
			}
				else {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED,ConstantMessage.INVALID_CREDENTIALS);
					throw new RuntimeException(ConstantMessage.INVALID_CREDENTIALS);
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
