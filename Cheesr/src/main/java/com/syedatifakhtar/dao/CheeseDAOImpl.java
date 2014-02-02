package com.syedatifakhtar.dao;

import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.syedatifakhtar.model.Cheese;

@SuppressWarnings({"unchecked", "rawtypes"})
public class CheeseDAOImpl implements CheeseDAO{
	
	@SpringBean(name="mySessionFactory")
	private SessionFactory mySessionFactory;
	
	@Override
	public List<Cheese> findAll() {
		Session session	=	mySessionFactory.getCurrentSession();
		List cheeses= session.createQuery("from Cheese").list();
		return (List<Cheese>)cheeses;
	}
	
	@Override
	public Cheese getCheese(long id) {
		Session session	=	mySessionFactory.getCurrentSession();
		Cheese cheese=(Cheese)session.get(Cheese.class, new Long(id));
		return cheese;
	}

	@Override
	public long saveCheese(Cheese cheese) {
		Session session	=	mySessionFactory.getCurrentSession();
		Long generatedID=(Long)session.save(cheese);
		return generatedID.longValue();
	}

	@Override
	public  void updateCheese(Cheese cheese) {
		Session session	=	mySessionFactory.getCurrentSession();
		session.saveOrUpdate(cheese);
	}
	
	@Override
	public void deleteCheese(Cheese cheese) {
		Session session	=	mySessionFactory.getCurrentSession();
		session.delete(cheese);
	}
	
	public SessionFactory getMySessionFactory() {
		return mySessionFactory;
	}

	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}


}
