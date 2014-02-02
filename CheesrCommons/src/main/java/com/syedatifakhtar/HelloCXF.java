package main.java.com.syedatifakhtar;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
interface HelloCXF {
	
	@WebMethod
	String sayHello();

}
