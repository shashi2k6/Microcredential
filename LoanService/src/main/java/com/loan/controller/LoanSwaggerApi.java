package com.loan.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loan.bean.UserLoanDtls;
import com.loan.repository.LoanRepository;

@RestController
@RequestMapping("/api")
public class LoanSwaggerApi {

	@Autowired
	public LoanRepository loadRepository;

	@GetMapping("/{id}")
	public Optional<UserLoanDtls> getUserLoanDetails(@PathVariable Long id) {
		if(null!=id) {
			return loadRepository.findById(id);
		}
		return null;

	}

}
