package com.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController

public class RestController {
	
	@Value("${app.name:Spring cloud}")
	private String appname;
	
	@RequestMapping("/")
	public String home() {
		return "Spring cloud config server up and running.."+appname;
	}

}
