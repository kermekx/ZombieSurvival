package com.kermekx.engine.exceptions;

import com.kermekx.engine.log.KELogger;

public class InvalidProductException extends Exception {

	private static final long serialVersionUID = 1265581738913680805L;
	
	public InvalidProductException(String string) {
		super(string);
		KELogger.logException(this);
	}

}
