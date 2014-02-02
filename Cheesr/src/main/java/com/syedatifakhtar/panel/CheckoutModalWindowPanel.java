package com.syedatifakhtar.panel;

import java.util.Map;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import com.syedatifakhtar.cart.CheesrCartActionListener;
import com.syedatifakhtar.model.Cheese;
import com.syedatifakhtar.session.CheesrSession;

public class CheckoutModalWindowPanel extends Panel{
	
	private WebMarkupContainer webMarkupContainer;
	private AjaxFallbackButton closeWindowButton;
	private Form modalForm;
	private CheesrCartActionListener cheesrCartActionListener;
	private AjaxFallbackButton checkoutButton;
	
	public CheckoutModalWindowPanel(String id,CheesrCartActionListener cheesrCartActionListener) {
		super(id);
		// TODO Auto-generated constructor stub
		webMarkupContainer	=	new WebMarkupContainer("modalMarkup");
		webMarkupContainer.setOutputMarkupId(true);
		modalForm	=	new Form("modalForm") {
			protected void onConfigure() {
				super.onConfigure();
				addOrReplace(createLazyLoadRepeater("lazyLoadRepeater"));
			};
		};
		this.cheesrCartActionListener=cheesrCartActionListener;
		closeWindowButton	= new AjaxFallbackButton("closeButton",modalForm) {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				hideWindow();
				target.add(webMarkupContainer);
			}
		};
		checkoutButton	=	new AjaxFallbackButton("checkoutButton",modalForm) {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				if(getListener()!=null) {
					getListener().checkout();
				}
			}
			
		};
		modalForm.setOutputMarkupId(true);
		modalForm.add(closeWindowButton);
		modalForm.add(checkoutButton);
		webMarkupContainer.add(modalForm);
		webMarkupContainer.setOutputMarkupId(true);
		add(webMarkupContainer);
		setOutputMarkupId(true);
	}
	
	public CheesrCartActionListener getListener() {
		return cheesrCartActionListener;
	}
	public void showWindow() {
		AttributeModifier ariaModifier= new AttributeModifier("aria-hidden", "true");
		AttributeModifier classModifier= new AttributeModifier("class", "modal fade in");
		AttributeModifier styleModifier= new AttributeModifier("style", "display:block");
		webMarkupContainer.add(ariaModifier);
		webMarkupContainer.add(classModifier);
		webMarkupContainer.add(styleModifier);
		modalForm.configure();
	}

	public void hideWindow() {
		AttributeModifier ariaModifier= new AttributeModifier("aria-hidden", "false");
		AttributeModifier classModifier= new AttributeModifier("class", "modal fade");
		AttributeModifier styleModifier= new AttributeModifier("style", "display:none");
		webMarkupContainer.add(ariaModifier);
		webMarkupContainer.add(classModifier);
		webMarkupContainer.add(styleModifier);
	}
	
	private AjaxLazyLoadPanel createLazyLoadRepeater(String wicketID) {
		AjaxLazyLoadPanel	ajaxLazyLoadPanel=	new AjaxLazyLoadPanel(wicketID) {
			
			@Override
			public Component getLazyLoadComponent(String markupId) {
				// TODO Auto-generated method stub
				RepeatingView cartItemRepeater	=	new RepeatingView(markupId);
				try{
					System.out.println("Painting Repeater");
					CheesrSession cheesrSession	=	(CheesrSession)getSession();
						Map<Cheese,Integer> cheeseCart=cheesrSession.getCart().getCart();
					if(!cheeseCart.isEmpty()){
						for(Cheese cheese: cheeseCart.keySet()) {
							System.out.println("Painting Cheese: " + cheese.getName());
							ShoppingPanel cartOrderView	=	new ShoppingPanel(cartItemRepeater.newChildId(), cheese, cheeseCart.get(cheese), true);
							cartOrderView.attachListener(cheesrCartActionListener);
							cartItemRepeater.add(cartOrderView);
						}
					}
				}catch(Exception e) {
					System.out.println("Soemthings up with the cart bitch!");
					e.printStackTrace();
				}
				return cartItemRepeater;
			}
		};
		return ajaxLazyLoadPanel;
	}
}
