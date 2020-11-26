package com.loan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.loan.bean.UserLoanDtls;

@Service
public interface LoanRepository extends CrudRepository<UserLoanDtls, Long> {

}
