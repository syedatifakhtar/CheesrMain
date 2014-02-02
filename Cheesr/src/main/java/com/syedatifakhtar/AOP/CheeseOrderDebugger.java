package com.syedatifakhtar.AOP;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class CheeseOrderDebugger {

	private static final Logger logger	=	Logger.getLogger(CheeseOrderDebugger.class);
	public void savingOrder() {
	}
	
	public void printOrderInfo() {
		logger.info("----------------Saving Cheese Order-----------------");
		
	}
	
	public void printOrderCompleted() {
		logger.info("---------------Completed order---------------");
	}
}
