package com.syedatifakhtar.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.syedatifakhtar.dao.CheeseDAO;
import com.syedatifakhtar.model.Cheese;

public class CheeseServiceImpl implements CheeseService{

	private CheeseDAO cheeseDAO;
	
	@Transactional
	@Override
	public List<Cheese> findAll() {
		return cheeseDAO.findAll();
	}

	@Transactional
	@Override
	public Cheese getCheese(long id) {
		return cheeseDAO.getCheese(id);
	}

	@Transactional
	@Override
	public long saveCheese(Cheese cheese) {
		// TODO Auto-generated method stub
		return cheeseDAO.saveCheese(cheese);
	}

	@Transactional
	@Override
	public void updateCheese(Cheese cheese) {
		cheeseDAO.updateCheese(cheese);
	}
	
	@Transactional
	@Override
	public void deleteCheese(Cheese cheese) {
		cheeseDAO.deleteCheese(cheese);
		
	}
	
	public CheeseDAO getCheeseDAO() {
		return cheeseDAO;
	}

	public void setCheeseDAO(CheeseDAO cheeseDAO) {
		this.cheeseDAO = cheeseDAO;
	}
}
