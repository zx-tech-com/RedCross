package com.zx.redcross.exception.handler;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.zx.redcross.exception.BusinessException;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

/**
 * 统一异常处理类.
 * @author Daryl
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public Map<String,Object> businessExceptionHandler(BusinessException e){
		logger.error("业务异常" + e.getMessage());
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		map.put(Constant.ERROR_MESSAGE, e.getMessage());
		return map;
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public Map<String,Object> missingServletRequestParameterHandler(MissingServletRequestParameterException e){
		logger.error(Constant.ERROR_MISSIN_GPARAMETER + e.getMessage());
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		map.put(Constant.ERROR_MESSAGE, Constant.ERROR_MISSIN_GPARAMETER);
		map.put(Constant.TIPS, e.getMessage());
		e.printStackTrace();
		return map;
	}
	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody
	public Map<String,Object> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e){
		logger.error(Constant.ERROR_PARAMETER_TYPE_ILEGAL + e.getMessage());
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		map.put(Constant.ERROR_MESSAGE, Constant.ERROR_PARAMETER_TYPE_ILEGAL);
		map.put(Constant.TIPS, e.getMessage());
		return map;
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public Map<String,Object> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e){
		logger.error(Constant.ERROR_REQUEST_METHOD_ILEGAL + e.getMessage());
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		map.put(Constant.ERROR_MESSAGE, Constant.ERROR_REQUEST_METHOD_ILEGAL);
		map.put(Constant.TIPS, e.getMessage());
		return map;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String,Object> defaultExceptionHandler(Exception e){
		logger.error(Constant.ERROR_UNKNOW + e.getMessage());
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		map.put(Constant.ERROR_MESSAGE, Constant.ERROR_UNKNOW);
		e.printStackTrace();
		return map;
	}
	
}
