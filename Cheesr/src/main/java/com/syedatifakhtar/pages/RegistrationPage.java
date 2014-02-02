package com.syedatifakhtar.pages;

import java.io.Serializable;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.syedatifakhtar.BasePage;
import com.syedatifakhtar.model.User;
import com.syedatifakhtar.service.UserService;

public class RegistrationPage extends BasePage {

	@SpringBean(name = "userService")
	private UserService userService;

	private EmailTextField email;
	private PasswordTextField password;
	private TextField<String> username;
	private Form<UserRegistraionInput> registrationForm;
	private Button registerButton;
	private UserRegistraionInput userRegistraionInput;

	public RegistrationPage(PageParameters pageParams) {
		super(pageParams);
		Injector.get().inject(this);
		init();
		attachComponents();
	}

	private void init() {
		userRegistraionInput = new UserRegistraionInput();
		CompoundPropertyModel<UserRegistraionInput> userFormInputModel = new CompoundPropertyModel<UserRegistraionInput>(
				userRegistraionInput);
		registrationForm = new Form("registrationForm", userFormInputModel);
		email = new EmailTextField("email");
		password = new PasswordTextField("password");
		username = new TextField<String>("username");
		registerButton = new Button("registerButton") {
			@Override
			public void onSubmit() {
				if (userService != null
						&& !(userRegistraionInput.password == null
								|| userRegistraionInput.username == null || userRegistraionInput.email == null)
						&& !(userRegistraionInput.password.equals("") || userRegistraionInput.username
								.equals(""))) {
					User newUser = new User();
					newUser.setActive(false);
					newUser.setAddress("Mordor");
					newUser.setUsername(userRegistraionInput.username);
					newUser.setPassword(userRegistraionInput.password);
					newUser.setEmail(userRegistraionInput.email);
					System.out
							.println("-----Saving New User-------------"
									+ "\nUsername: "
									+ newUser.getUsername()
									+ "\nPassword: "
									+ newUser.getPassword()
									+ "\nEmail: "
									+ newUser.getPassword()
									+ "\n\n\n-------------------------------------------------------");
					userService.saveUser(newUser);
				}
				setResponsePage(SignInPage.class);
			}
		};
	}

	private void attachComponents() {
		registrationForm.add(email);
		registrationForm.add(password);
		registrationForm.add(username);
		registrationForm.add(registerButton);
		add(registrationForm);
	}

	/*
	 * A class that will serve as the CompoundPropertyModel for entering user
	 * credentials
	 */
	private class UserRegistraionInput implements Serializable {
		private String username;
		private String email;
		private String password;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


}
