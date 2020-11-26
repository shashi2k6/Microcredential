package com.loan.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.loan.bean.UserBean;
import com.loan.bean.UserLoanDtls;

@Service
public interface UserDao {
	
	public String createUser(UserBean user);
	
	public List<UserBean> getAllUsers();
	
	public boolean applyLoan(UserLoanDtls userDetails);

}
