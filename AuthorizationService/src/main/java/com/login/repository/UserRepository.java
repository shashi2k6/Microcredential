package com.login.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.login.bean.UserBean;

@Service
public interface UserRepository extends CrudRepository<UserBean, Long> {
	
	@Query(value = "SELECT * FROM Users u where u.username=? and u.password=? ", nativeQuery = true)
	public UserBean findByUser(String username, String password);
	

	@Query(value = "SELECT * FROM Users u where u.username=?  ", nativeQuery = true)
	public UserBean findByUserName(String username);


}

