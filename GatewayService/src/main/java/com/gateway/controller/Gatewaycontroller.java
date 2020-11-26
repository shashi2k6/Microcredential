package com.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Gatewaycontroller {

	
	@RequestMapping({ "/" })
	public String home() {
		return "Gateway service up and running..  @ port : 9090";
	}
}
