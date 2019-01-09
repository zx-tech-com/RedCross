package com.zx.redcross.tool;

import com.zx.redcross.exception.BusinessException;

public class BusinessExceptionUtils {
	
	public static void throwNewBusinessException(String message) {
		throw new BusinessException(message);
	}
	
}
