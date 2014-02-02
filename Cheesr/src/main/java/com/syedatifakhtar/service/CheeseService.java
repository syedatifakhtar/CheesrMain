package com.syedatifakhtar.service;

import java.util.List;

import com.syedatifakhtar.model.Cheese;

public interface CheeseService {

	
	public List<Cheese> findAll();
	public Cheese getCheese(long id);
	public long saveCheese(Cheese cheese);
	public void updateCheese(Cheese cheese);
	public void deleteCheese(Cheese cheese);
}
