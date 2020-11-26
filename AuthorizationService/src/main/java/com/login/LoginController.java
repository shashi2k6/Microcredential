package com.login;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.login.bean.UserBean;
import com.login.exception.LoginServiceException;
import com.login.jwtutil.JWTUtil;
import com.login.service.UserService;

@CrossOrigin
@RestController
@RefreshScope
public class LoginController {

	@Value("${app.name:Authorization application}")
	private String appname;

	@Autowired
	UserService userService;


	@RequestMapping( "/" )
	public String authHome() {
		return appname+ "  service up and running at port : 8081";
	}


	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserBean userBean) {
		try {
			userService.save(userBean);
			return new ResponseEntity<String>("User created Successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("User creation failed", HttpStatus.OK);
		}
	}

	@GetMapping("/getusers")
	public ResponseEntity<String>  getAllUsers() {
		Iterable<UserBean> users =  userService.getAllUsers();
		String json = new Gson().toJson(users );
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	@Autowired
	public JWTUtil jWTUtil;


	@RequestMapping(value = "/login", method = RequestMethod.POST) 
	public ResponseEntity<?> authenticate(@RequestBody UserBean userBean) {
		try {
			UserBean userBeanResult;
			if(null!=userBean.getUsername() && null!= userBean.getPassword()) {
				userBeanResult = userService.findByUser(userBean);
			}else {
				throw new LoginServiceException("Invalid request username & password cannot be null.");
			}
			if(null==userBeanResult) {
				throw new LoginServiceException("User dosen't exists. in our database.");
			}else if(userBean.getPassword().equalsIgnoreCase(userBeanResult.getPassword())){
				Map<String,String> map=jWTUtil.createToken(userBean.getUsername());
				
				return new  ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
			}else {
				throw new LoginServiceException("User name & password dosen't match.. please verify.");
			}

		} catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\""+e.getMessage()+"\"}",HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value = "/istokenvalid", method = RequestMethod.GET) 
	public String isTokenValid(@RequestHeader String token) {
		String isTokenValid = "valid";
		try {
			isTokenValid = jWTUtil.isTokenValid(token);
		} catch (Exception e) {
			return "Validation failed";
		}
		return isTokenValid;
	}


	//
	//	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	//	public ResponseEntity<?> authticate(@RequestBody AuthenticationRequest authenticationRequest) {
	//		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
	//				authenticationRequest.getPassword()));
	//		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	//		String jwt = jWTUtil.generateToken(userDetails);
	//		return ResponseEntity.ok(new AutheticationResponse(jwt));
	//	}


}
