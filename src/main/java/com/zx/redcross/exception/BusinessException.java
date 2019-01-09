package com.zx.redcross.exception;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BusinessException(String reason) {
		super(reason);
	}
	
}
