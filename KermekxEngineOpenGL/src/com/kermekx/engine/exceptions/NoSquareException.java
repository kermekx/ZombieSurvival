package com.kermekx.engine.exceptions;

import com.kermekx.engine.log.KELogger;

public class NoSquareException extends Exception {

	private static final long serialVersionUID = -6892250940456837284L;

	public NoSquareException(String string) {
		super(string);
		KELogger.logException(this);
	}

}
