package com.loan.test;

import java.util.Collection;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loan.bean.UserBean;
import com.loan.controller.LoanController;
import com.loan.service.UserService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanControllerTest {

	@MockBean
	UserService userService;
	
	@MockBean
	DataSource datasource;

	private UserBean userBean;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		userBean = new UserBean("shashi", "pass", true);
	}

	@Test
	public void testUserExists() throws Exception {
		Mockito.when(userService.findByUser("shashi", "pass")).thenReturn(false);
		boolean userExist = userService.findByUser("shashi", "pass");
		Assert.assertEquals(false, userExist);

	}

	@Test
	public void testSaveUser() throws Exception {
		Mockito.when(userService.save(userBean)).thenReturn(userBean);
		boolean userExist = userService.findByUser("shashi", "pass");
		Assert.assertEquals(false, userExist);

	}
	
	@Test
	public void getAllUsers() throws Exception {
		Iterable<UserBean> userExist = userService.getAllUsers();
		Assert.assertEquals(0, count(userExist));

	}
	
	public int count(Iterable<UserBean> userExist) {
		if (userExist instanceof Collection) {
	        return ((Collection<?>) userExist).size();
	    }
		return 0;
	}

	private static String convertJsonToString(Object object) throws JsonProcessingException {
		String result;
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonText = mapper.writeValueAsString(object);
			result = jsonText;
		} catch (JsonProcessingException e) {
			result = "json processing error";
		}
		return result;
	}
}
