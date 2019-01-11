package com.zx.redcross.tool;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;

/**
 * Token的生成及校验
 * @author Daryl
 */
public class JWTUtils {

	/**
	 * 秘钥
	 */
	private final static byte[] SECRET = Constant.SECRET.getBytes();
	
	
	/**
	 * 生成一个Token 参见:<br/>
	 * @see <a href="https://www.jianshu.com/p/75208a68c3b9">说明</a>
	 * @param payloadMap
	 * @return
	 * @throws JOSEException
	 */
    public static String creatToken(Map<String,Object> payloadMap) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        Payload payload = new Payload(new JSONObject(payloadMap));
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        JWSSigner jwsSigner = new MACSigner(SECRET);
        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }
    
    /**
     * 解析token
     */
    public static Map<String,Object> validateToken(String token) throws ParseException, JOSEException, java.text.ParseException {

    	Map<String, Object> resultMap = MapUtils.getHashMapInstance();
    	if(token == null) {//无token信息
    		resultMap.put(Constant.TOKEN, Constant.TOKEN_INVALID);
    		return resultMap;
    	}
    	
        JWSObject jwsObject = JWSObject.parse(token);
        Payload payload=jwsObject.getPayload();
        JWSVerifier jwsVerifier = new MACVerifier(SECRET);

        if (jwsObject.verify(jwsVerifier)) {//验证通过
        	JWTUtils.checkIfTokenExpired(payload,resultMap);
        }else {
        	resultMap.put(Constant.TOKEN, Constant.TOKEN_INVALID);
        }
        return resultMap;

    }
    
    private static void checkIfTokenExpired(Payload payload,Map<String,Object> resultMap) {
    	 JSONObject jsonObject = payload.toJSONObject();
         if (jsonObject.containsKey(Constant.EXPIRES)) {//包括过期时间
             Long expTime = Long.valueOf(jsonObject.get(Constant.EXPIRES).toString());
             Long nowTime = new Date().getTime();
             if (nowTime > expTime) {//已过期
                 resultMap.put(Constant.TOKEN,Constant.TOKEN_EXPIRED);
             }else {//未过期
            	 resultMap.put(Constant.TOKEN,Constant.TOKEN_VALID);
            	 resultMap.put(Constant.PAY_LOAD, jsonObject);
             }
            	 
         }else {//不包含过期时间
         	resultMap.put(Constant.TOKEN,Constant.TOKEN_VALID);
         	resultMap.put(Constant.PAY_LOAD, jsonObject);
         }
    }

	
    public static Map<String,Object> prepareTokenParams(String customerId) {
    	
    	Map<String,Object> map = MapUtils.getHashMapInstance();
    	map.put(Constant.CUSTOMERID, customerId);
    	Calendar date = Calendar.getInstance();
		date.add(Constant.HOUR_FIELD, Constant.NUMBER_8);
		map.put(Constant.EXPIRES, date.getTime());
    	return map;
    	
    }
    
}
