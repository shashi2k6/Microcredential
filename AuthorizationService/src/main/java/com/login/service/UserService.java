package com.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.bean.UserBean;
import com.login.exception.UserNotFoundException;
import com.login.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	

	public UserBean save(UserBean userBean)throws Exception {
		return userRepository.save(userBean);
	}

	public UserBean findByUser(UserBean userBeanObj) throws UserNotFoundException{
		return userRepository.findByUser(userBeanObj.getUsername(), userBeanObj.getPassword());
	}

	public Iterable<UserBean> getAllUsers() {
		return userRepository.findAll();
	}
}
