package com.kermekx.engine.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KELogger {
	
	private final static KELogger INSTANCE = new KELogger();
	
	private final Logger logger;
	
	public KELogger() {
		logger = Logger.getLogger(KELogger.class.getName());
		logger.setLevel(Level.ALL);
	}
	
	public static void logInfo(String info) {
		INSTANCE.logger.log(Level.INFO, info);
	}

	public static void logException(Exception e) {
		INSTANCE.logger.log(Level.SEVERE, e.getMessage(), e);
	}
}
