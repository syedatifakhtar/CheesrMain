package com.syedatifakhtar.pages;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.syedatifakhtar.BasePage;
import com.syedatifakhtar.WicketApplication;
import com.syedatifakhtar.dao.OrderDAO;
import com.syedatifakhtar.model.Cheese;
import com.syedatifakhtar.model.CheeseOrder;
import com.syedatifakhtar.model.Order;
import com.syedatifakhtar.panel.CheeseActionPanel;
import com.syedatifakhtar.service.CheeseOrderService;
import com.syedatifakhtar.service.CheeseService;

public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;
	private String messageFromService;
	private final RepeatingView cheeseRepeater;
	private final List<Cheese> cheeses;
	private final AjaxFallbackLink<Void> addCheeseAjaxLink;
	private final WebMarkupContainer cheeseRepeaterContainer;
	
	@SpringBean(name="cheeseService")
	private CheeseService cheeseService;
	
	@SpringBean(name="cheeseOrderService")
	private CheeseOrderService cheeseOrderService;
	
	@SpringBean(name="orderDAO")
	private OrderDAO orderDAO;
	
	
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public HomePage(final PageParameters parameters) {
		super(parameters);
		Injector.get().inject(this);
		WicketApplication myApplication = (WicketApplication) getApplication();
		messageFromService = myApplication.getMessengerService().getMessage();
		System.out.println(messageFromService);
		add(new Label("howdymessage", messageFromService));
		cheeses = cheeseService.findAll();
		cheeseRepeater = new RepeatingView("cheeseRepeater");
		for (Cheese cheese : cheeses) {
			cheeseRepeater.add(new CheeseActionPanel(cheeseRepeater
					.newChildId(), cheese));
		}
		cheeseRepeater.setOutputMarkupId(true);
		cheeseRepeaterContainer = new WebMarkupContainer(
				"cheeseRepeaterContainer"); //A hack to allow the repeater to repaint itself
		cheeseRepeaterContainer.setOutputMarkupId(true);
		addCheeseAjaxLink = new AjaxFallbackLink<Void>(
				"addCheeseAjaxFallbackLink") {
			@Override
			public void onClick(AjaxRequestTarget requestTarget) {
				Cheese addedCheese = new Cheese();
				CheeseActionPanel cheeseActionPanel = new CheeseActionPanel(
						cheeseRepeater.newChildId(), addedCheese,
						CheeseActionPanel.Mode.CREATE);
				cheeseRepeater.add(cheeseActionPanel);
				requestTarget.add(cheeseRepeaterContainer);
			}
		};

		add(addCheeseAjaxLink);
		cheeseRepeaterContainer.add(cheeseRepeater);
		add(cheeseRepeaterContainer);
		printDummyOrders();

	}

	public void printDummyOrders() {
		
		for(Order order:cheeseOrderService.findall()) {
			System.out.println("OrderID :" + Long.toString(order.getOrderID()) + " BY: " + order.getPersonName());
			System.out.println("Bought");
			for(CheeseOrder cheeseOrder:order.getCheeseOrder()){
				System.out.println("CHEESE NAME: " + cheeseOrder.getPk().getCheese().getName() + " QUANTITY: " + cheeseOrder.getQuantity());
			}
		}
	}

	public void createDummyOrders() {
		
		Order order = new Order();
		order.setCreatedDate(new Date());
		order.setPersonName("Atif");
		order.setPersonPhone("7840847298");
		
		Map<Cheese,Integer> cheeseQuantityHashMap	=	new HashMap<Cheese,Integer>();
		for(Cheese cheese:cheeseService.findAll()) {
			int randomNumber	=	1 + (int)(Math.random() * 24);
			cheeseQuantityHashMap.put(cheese,new Integer(randomNumber));
		}
		
		cheeseOrderService.saveOrder(order, cheeseQuantityHashMap);
		
	}
	public CheeseService getCheeseService() {
		return cheeseService;
	}

	public void setCheeseService(CheeseService cheeseService) {
		this.cheeseService = cheeseService;
	}
	
}
