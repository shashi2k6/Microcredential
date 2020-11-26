package com.loan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.bean.UserBean;
import com.loan.customexception.UserNotFoundException;
import com.loan.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public UserBean save(UserBean userBean)throws Exception {
		return userRepository.save(userBean);
	}

	public Boolean findByUser(String username, String password) throws UserNotFoundException{
		Optional<UserBean> userBean = userRepository.findByUser(username, password);
		if(null!=userBean) {
			return true;
		}else {
			return false;
		}
	}

	public Iterable<UserBean> getAllUsers() {
		return userRepository.findAll();
	}
}
