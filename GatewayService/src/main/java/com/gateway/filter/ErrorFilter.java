package com.gateway.filter;

import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ErrorFilter  extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
		System.out.println("Post Filter : "+response.getStatus());
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "Error";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
