//package com.loan.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.google.gson.Gson;
//import com.loan.bean.UserBean;
//import com.loan.service.UserService;
//
//@CrossOrigin
//@RestController
//public class LoginController {
//
//	@Value("${app.name:Spring loan application}")
//	private String appname;
//
//	@Autowired
//	UserService userService;
//
//	@RequestMapping("/")
//	public String login() {
//		//User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		//String name = user.getUsername();
//		return  "Loan applicaion : "+appname; 
//	}
//
//	@PostMapping("/register")
//	public ResponseEntity<String> register(@RequestBody UserBean user) {
//		try {
//			userService.save(user);
//			return new ResponseEntity<String>("User created Successfully", HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<String>("User creation failed", HttpStatus.OK);
//		}
//	}
//
//	@GetMapping("/getusers")
//	public ResponseEntity<String>  getAllUsers() {
//		Iterable<UserBean> users =  userService.getAllUsers();
//		String json = new Gson().toJson(users );
//		return new ResponseEntity<String>(json, HttpStatus.OK);
//	}
//}
