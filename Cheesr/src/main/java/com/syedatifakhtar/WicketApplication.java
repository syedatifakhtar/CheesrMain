package com.syedatifakhtar;

import org.apache.wicket.Session;
import org.apache.wicket.core.util.file.WebApplicationPath;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.IResourceSettings;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.file.IResourceFinder;

import com.syedatifakhtar.pages.CheeseShoppingPage;
import com.syedatifakhtar.pages.HomePage;
import com.syedatifakhtar.pages.RegistrationPage;
import com.syedatifakhtar.pages.SignInPage;
import com.syedatifakhtar.service.MessengerService;
import com.syedatifakhtar.session.CheesrSession;
import com.syedatifakhtar.utils.MountedMapperWithoutPageComponentInfo;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.syedatifakhtar.Start#main(String[])
 */
@SuppressWarnings("all")
public class WicketApplication extends WebApplication {

	@SpringBean(name = "messengerService")
	public MessengerService messengerService;

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}
	
	@Override
	public Session newSession(Request request, Response response) {
		// TODO Auto-generated method stub
		return new CheesrSession(request);
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		// IMPORTANT - Installs the SpringComponentInjector - Allows POJOs to be
		// inserted using Injector.get().inject(this)
		getComponentInstantiationListeners().add(
				new SpringComponentInjector(this));

		// add your configuration here
		IResourceSettings resourceSettings = getResourceSettings();
		WebApplicationPath webAppPath = new WebApplicationPath(
				getServletContext(), "/WEB-INF/markup/");
		IResourceFinder pageResources = webAppPath;
		System.out.println("Using resource path: " + webAppPath.toString());
		resourceSettings.getResourceFinders().add(pageResources);
		mount(new MountedMapperWithoutPageComponentInfo("/", HomePage.class));
		mount(new MountedMapperWithoutPageComponentInfo("/ShoppingPage", CheeseShoppingPage.class));
		mount(new MountedMapperWithoutPageComponentInfo("/SignIn", SignInPage.class));
		mount(new MountedMapperWithoutPageComponentInfo("/Register", RegistrationPage.class));
		
	}

	public MessengerService getMessengerService() {
		return messengerService;
	}

	public void setMessengerService(MessengerService messengerService) {
		this.messengerService = messengerService;
	}

}
