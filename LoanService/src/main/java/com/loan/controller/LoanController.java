package com.loan.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.loan.bean.UserLoanDtls;
import com.loan.service.LoanService;

@RefreshScope
@RestController
public class LoanController {

	@Value("${app.name:Loan applicaion}")
	private String appname;

	@Autowired
	LoanService loanService;
	
	@RequestMapping( "/")
	public String loanHome() {
		return appname+ " service up and running port : 8082";
	}

	@PostMapping("/createLoan")
	public ResponseEntity<?> createLoan(@RequestBody UserLoanDtls userLoanDtls) {
		UserLoanDtls userLoan = loanService.createLoan(userLoanDtls);
		if(null!=userLoan && userLoan.getId()>0) {
			return new ResponseEntity<String>("{\"message\":\"User Loan created\"}",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("{\"message\":\"User loan creation failed.\"}",HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/getUserLoanDtsl")
	public ResponseEntity<?> getLoanDtls(@RequestParam Long id) {
		Optional<UserLoanDtls> userLoanDtls = loanService.findById(id);
		String json = new Gson().toJson(userLoanDtls );
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	@PostMapping("/updateUserLoanDtsl")
	public ResponseEntity<?> updateLoanDtls(@RequestBody UserLoanDtls userLoanDtls) {
		loanService.updateLoanDtls(userLoanDtls);
		return new ResponseEntity<String>("Updated Loan details successfully", HttpStatus.OK);
	}
}
