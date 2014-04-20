package main.java.com.syedatifakhtar;

import javax.jws.WebService;

import CheesrCommons.HelloCXF;


@WebService
public class HelloCXFImpl implements HelloCXF{

	@Override
	public String sayHello() {
		// TODO Auto-generated method stub
		System.out.println("Ok i was called!!You can celebrate now!");
		return "Hello Dave!";
	}

}
