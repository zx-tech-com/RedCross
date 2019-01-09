package com.zx.redcross.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zx.redcross.tool.Constant;

/**
 * 用于修饰url字段,自动为字段加上访问前缀
 * @author Daryl
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Url {
	String value() default Constant.ACCESS_BASE_PATH;
}
