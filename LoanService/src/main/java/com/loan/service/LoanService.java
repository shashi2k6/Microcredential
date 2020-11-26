package com.loan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.bean.UserLoanDtls;
import com.loan.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired
	LoanRepository loanRepository;

	public UserLoanDtls createLoan(UserLoanDtls userLoanDtls) {
		//User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return loanRepository.save(userLoanDtls);
	}

	public Optional<UserLoanDtls> findById(Long id) {
		return loanRepository.findById(id);
	}

	public void updateLoanDtls(UserLoanDtls userLoanDtls) {
		loanRepository.save(userLoanDtls);
	}
}
