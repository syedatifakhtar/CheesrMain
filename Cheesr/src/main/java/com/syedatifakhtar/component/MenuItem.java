package com.syedatifakhtar.component;

import java.io.Serializable;


public class MenuItem implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Class redirectToPage;
	public MenuItem(String name,Class redirectToPage) {
		this.name=name;
		this.redirectToPage=redirectToPage;
	}
	public String getName() {
		return name;
	}
	public Class getRedirectToPage() {
		return redirectToPage;
	}
}
