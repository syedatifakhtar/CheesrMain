package com.syedatifakhtar.pages;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.syedatifakhtar.BasePage;
import com.syedatifakhtar.session.CheesrSession;

public class SignInPage extends BasePage {
	
	private EmailTextField emailInput;
	private PasswordTextField passwordInput;
	private Form<UserFormInput> signInForm;
	private Button signInButton;
	private UserFormInput userFormInput;
	
	public SignInPage(PageParameters pageParams) {
		super(pageParams);
		init();
		attachComponents();
	}

	private void init() {
		userFormInput = new UserFormInput();
		CompoundPropertyModel<UserFormInput> userFormInputModel	=	new CompoundPropertyModel<UserFormInput>(userFormInput);
		signInForm	=	new Form("signInForm", userFormInputModel);
		emailInput	=	new EmailTextField("emailInput");
		passwordInput	=	new PasswordTextField("passwordInput");
		signInButton	=	new Button("signInButton") {
			@Override
			public void onSubmit() {
				CheesrSession.get().setUser(userFormInput.passwordInput, userFormInput.passwordInput);
				setResponsePage(HomePage.class);
			}
		};
	}
	
	private void attachComponents() {
		signInForm.add(emailInput);
		signInForm.add(passwordInput);
		signInForm.add(signInButton);
		add(signInForm);
	}
	
	/*
	 * A class that will serve as the CompoundPropertyModel for entering user credentials
	 */
	private class UserFormInput implements Serializable {
		private String emailInput;
		private String passwordInput;
	}
}
