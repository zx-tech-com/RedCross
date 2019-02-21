package com.zx.redcross.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zx.redcross.entity.TokenInfo;

/**
 * token管理
 * @author Daryl
 *
 */
public class TokenManager {
	
	private static Logger logger = LogManager.getLogger(com.zx.redcross.tool.TokenManager.class);

	private TokenManager() {
		//无法实例化
	}
	
	private static class TokenMapSingleton{
		private final static ConcurrentHashMap<String,TokenInfo> tokenMap = new ConcurrentHashMap<String,TokenInfo>();
	}
	
	public static TokenInfo addNewToken(String key,TokenInfo token) {
		if(key == null || token == null) return null;//不让map抛空指针异常
		logger.debug("新用户登录 ： " + token.toString() + ", 当前共 " + (TokenMapSingleton.tokenMap.size() + 1) + " 位用户");
		return TokenMapSingleton.tokenMap.put(key, token);
	}
	
	public static TokenInfo getToken(String key) {
		return TokenMapSingleton.tokenMap.get(key);
	}
	
	public static void refreshValidTime(String key) {
		TokenInfo token = getToken(key);
		if(token != null)
			token.refreshValidTime();
	}
	
	public static Integer getTokenSize() {
		return TokenMapSingleton.tokenMap.size();
	}
	
	/**
	 * token是否已经过期
	 * @return true 过期，false未过期
	 */
	public static boolean isExpired(String key) {
		TokenInfo token = getToken(key);
		if(token != null)
			return token.isExpired();
		return true;
	}
	
	public static void removeToken(String key) {
		if(key == null) return;//不让map抛空指针异常
		TokenInfo token = TokenMapSingleton.tokenMap.remove(key);
		if(token == null)
			return;
		logger.debug("用户退出 ： " + token.toString() + ", 当前共 " + TokenMapSingleton.tokenMap.size() + " 位用户");
	}
	
	public static void remveExpiredToken() {
		
		logger.debug("开始清除超时Token, 清除前共 " + TokenManager.getTokenSize() + " 个token");
		
		List<String> tokenList = new ArrayList<String>();
		Map<String,TokenInfo> map =  TokenMapSingleton.tokenMap;
		for(Entry<String,TokenInfo> entry : map.entrySet()) {
			if(entry.getValue().isExpired())
				tokenList.add(entry.getKey());
		}
		for(String token : tokenList)
			removeToken(token);
		
		logger.debug("完成清除超时Token, 清除后共 " + TokenManager.getTokenSize() + " 个token");
	}
}
