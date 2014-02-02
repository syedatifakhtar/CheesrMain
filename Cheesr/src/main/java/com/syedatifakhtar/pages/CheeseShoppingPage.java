package com.syedatifakhtar.pages;
import java.util.Map;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.syedatifakhtar.BasePage;
import com.syedatifakhtar.cart.CheesrCart;
import com.syedatifakhtar.cart.CheesrCartActionListener;
import com.syedatifakhtar.model.Cheese;
import com.syedatifakhtar.panel.CheckoutModalWindowPanel;
import com.syedatifakhtar.panel.ShoppingPanel;
import com.syedatifakhtar.service.CheeseOrderService;
import com.syedatifakhtar.service.CheeseService;
import com.syedatifakhtar.session.CheesrSession;

public class CheeseShoppingPage extends BasePage{

	@SpringBean(name="cheeseService")
	CheeseService cheeseService;
	
	@SpringBean(name="cheeseOrderService")
	CheeseOrderService cheeseOrderService;
	
	private RepeatingView shoppingItemRepeater;
	private WebMarkupContainer shoppingItemRepeaterContainer;
	private AjaxButton checkoutButton;
	private Form<Void> checkoutForm;
	private CheesrCart cheesrCart;
	private CheckoutModalWindowPanel checkoutModalWindow;
	
	private CheesrCartActionListener	cheesrCartActionListener	=	new CheesrCartActionListener() {
		@Override
		public void removeCheese(Cheese cheese) {
			cheesrCart.removeFromCart(cheese);
			System.out.println("Removing cheese : " + cheese.getName());
		}
		
		@Override
		public void addCheese(Cheese cheese, Integer quantity) {
			cheesrCart.addToCart(cheese, quantity);
			System.out.println("Added cheese : " + cheese.getName() + " \t Quantity:" + quantity.longValue());
		}
		
		@Override
		public void checkout() {
			cheeseOrderService.saveOrder(cheesrCart.getOrder(), cheesrCart.getCart());
		}
	}; 
	public CheeseShoppingPage(PageParameters pageParams) {
		super(pageParams);
		Injector.get().inject(this);
		init();
		attachComponents();
		CheesrSession cheesrSession	=	(CheesrSession)getSession();
		this.cheesrCart=cheesrSession.getCart();
	}
	
	private void init() {
		shoppingItemRepeaterContainer	=	new WebMarkupContainer("shoppingItemRepeaterContainer");
		shoppingItemRepeaterContainer.setOutputMarkupId(true);
		shoppingItemRepeater	=	new RepeatingView("shoppingItemRepeater");
		for(Cheese cheese:cheeseService.findAll()){
			ShoppingPanel shoppingPanel	=new ShoppingPanel(shoppingItemRepeater.newChildId(), cheese);
			shoppingPanel.attachListener(cheesrCartActionListener);
			shoppingItemRepeater.add(shoppingPanel);
			checkoutForm	=	new Form("checkoutForm");
		checkoutButton	=	new AjaxButton("checkoutButton",checkoutForm) {
			protected void onSubmit(AjaxRequestTarget target,Form<?> form) {
				System.out.println("---------------------Printing Cheese Cart---------------------------");
				Map<Cheese,Integer> cheeseCart	=	cheesrCart.getCart();
				for(Cheese cheese:cheeseCart.keySet()) {
					System.out.println(cheese.getName() + " \t Quantity: " + cheeseCart.get(cheese));
				}
				checkoutModalWindow.showWindow();
				target.add(checkoutModalWindow);
			};
			};
		};
		checkoutModalWindow	=	new CheckoutModalWindowPanel("checkoutModalWindow",cheesrCartActionListener);

	}
	private void attachComponents() {
		shoppingItemRepeaterContainer.add(shoppingItemRepeater);
		checkoutForm.add(checkoutButton);
		add(checkoutForm);
		add(shoppingItemRepeaterContainer);

		add(checkoutModalWindow);
	}

}
