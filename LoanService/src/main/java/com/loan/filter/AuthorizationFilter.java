package com.loan.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class AuthorizationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String authHeader = req.getHeader("authorization");

		if ("OPTIONS".equals(req.getMethod())) {
			res.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request, response);
		} else {
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				throw new ServletException("Invalid authorization header");
			}

			RestTemplate restTemplate = new RestTemplate();
			String token = authHeader.substring(7);
			HttpHeaders headers = createHttpHeaders(token);
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> responses = restTemplate.exchange("http://localhost:8081/istokenvalid", HttpMethod.GET, entity, String.class);
			System.out.println("Result - status ("+ responses.getStatusCode() + ") has body: " + responses.hasBody());
			if(!responses.getBody().equalsIgnoreCase("valid")) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN, String.format("You are not autorized to perform the action"));
				return;
			}

			//req.setAttribute("claims", claims);
			chain.doFilter(request, response);
		}
	}

	private HttpHeaders createHttpHeaders(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("token", " " + token);
		return headers;
	}

}