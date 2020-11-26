package com.loan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.loan.bean.UserBean;

@Service
public interface UserRepository extends CrudRepository<UserBean, Long> {
	
	@Query(value = "SELECT * FROM Users u where u.username=? and u.password=? ", nativeQuery = true)
	public Optional<UserBean> findByUser(String username, String password);

}
