package CheesrCommons;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface HelloCXF {
	
	@WebMethod
	public String sayHello();

}
