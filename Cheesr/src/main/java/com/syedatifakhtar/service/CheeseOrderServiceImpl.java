package com.syedatifakhtar.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;

import com.syedatifakhtar.dao.CheeseDAO;
import com.syedatifakhtar.dao.OrderDAO;
import com.syedatifakhtar.model.Cheese;
import com.syedatifakhtar.model.CheeseOrder;
import com.syedatifakhtar.model.Order;

public class CheeseOrderServiceImpl implements CheeseOrderService{

	@Autowired
	private MailSender mailSender;
	
	private OrderDAO orderDAO;
	private CheeseDAO cheeseDAO;
	
	
	public CheeseDAO getCheeseDAO() {
		return cheeseDAO;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setCheeseDAO(CheeseDAO cheeseDAO) {
		this.cheeseDAO = cheeseDAO;
	}

	
	@Transactional
	@Override
	public List<Order> findall() {
		// TODO Auto-generated method stub
		return orderDAO.findall();
	}

	@Transactional
	@Override
	public Order getOrder(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public long saveOrder(Order order,Map<Cheese,Integer> cheeseQuantityMap) {
		Set<CheeseOrder> cheeseOrders	=	order.getCheeseOrder();
		CheeseOrder cheeseOrder;
		StringBuffer mailContent	=	new StringBuffer();
		mailContent.append("Hi " + order.getPersonName() + ",\n\n\n");
		mailContent.append("You ordered the following items:\n\n----------------------------------------------------------------------------------------------------\n\n");
		long total = 0;
		for(Cheese cheese: cheeseQuantityMap.keySet()) {
			System.out.println("Savin cheese order for " + order.getPersonName() + " Cheese: " + cheese.getName() + " QUantity: " + cheeseQuantityMap.get(cheese));
			cheeseOrder	=	new CheeseOrder();
			cheeseOrder.setOrder(order);
			cheeseOrder.setCheese(cheese);
			cheeseOrder.setQuantity(cheeseQuantityMap.get(cheese));
			cheeseOrder.setItemPrice(cheese.getPrice());
			cheeseOrders.add(cheeseOrder);
			mailContent.append("Cheese: " + cheese.getName() + " Quantity: " + cheeseQuantityMap.get(cheese) + " Item Price:" + cheese.getPrice());
			mailContent.append("\n\n\n\n----------------------------------------------------------------------------------------------------\n\n");
			total	= total + cheeseQuantityMap.get(cheese) * cheese.getPrice();	
		}
		order.setTotal(total);
		mailContent.append("\nTotal Bill: Rs " + total);
		long orderid= orderDAO.saveOrder(order);
		System.out.println("OrderId--->" + orderid);
		
		SimpleMailMessage message	=	new SimpleMailMessage();
		message.setTo("syedatifakhtar@gmail.com");
		message.setSubject("You have mail!!");
		message.setText(mailContent.toString());
		mailSender.send(message);
		return orderid;
	}

	@Transactional
	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void deleteOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
}
