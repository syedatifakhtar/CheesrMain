package com.syedatifakhtar.service;

import javax.jws.WebService;

@WebService(endpointInterface="com.syedatifakhtar.service.HelloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Hello World";
	}

}
