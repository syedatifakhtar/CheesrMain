package com.syedatifakhtar.session;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.syedatifakhtar.cart.CheesrCart;
import com.syedatifakhtar.cart.CheesrCartImpl;
import com.syedatifakhtar.model.User;

public class CheesrSession extends WebSession{

	private CheesrCart cheesrCart;
	private User sessionUser = null;
	
	public static CheesrSession get() {
		return (CheesrSession) Session.get();	//Covariance,neat trick from the wicket in action book
	}
	public CheesrSession(Request request) {
		super(request);
		init();
	}
	
	public void init() {
		cheesrCart	=	new CheesrCartImpl();
	}
	
	public CheesrCart getCart() {
		return cheesrCart;
	}
	
	/*
	 * TODO : Use DB to authenticate and fetch user details
	 */
	public void setUser(String password,String email) {
		if(email!=null && password!=null) {
			if(!email.equals("") && !password.equals("")) {
				sessionUser	=	new User();
				sessionUser.setEmail(email);
				sessionUser.setPassword(password);
				sessionUser.setUsername(email.split("@")[0].toUpperCase()); // Dirty way to set username
			}
		}
	}
	
	/*
	 * Quick way to check if the user is authenticated
	 */
	public boolean isAuthenticated() {
		System.out.println("Sesiouser isNotNull?: " + (sessionUser!=null));
		return sessionUser!=null;
	}
	
	public String getUsername() {
		if(sessionUser==null)
			return null;
		else {
			return sessionUser.getUsername();
		}
	}

}
