package com.zx.redcross.tool;

import java.util.Map;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.zx.redcross.entity.TokenInfo;

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
    public static String creatToken(Map<String,Object> payloadMap){
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        Payload payload = new Payload(new JSONObject(payloadMap));
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        JWSSigner jwsSigner;
		try {
			jwsSigner = new MACSigner(SECRET);
			jwsObject.sign(jwsSigner);
		} catch (KeyLengthException e) {
			e.printStackTrace();
			BusinessExceptionUtils.throwNewBusinessException("秘钥长度不合理");
		} catch (JOSEException e) {
			e.printStackTrace();
			BusinessExceptionUtils.throwNewBusinessException("token生成错误");
		}
        return jwsObject.serialize();
    }
    
    /**
     * 解析token
     */
    public static Map<String,Object> validateToken(String token) throws ParseException, JOSEException, java.text.ParseException {

    	Map<String, Object> resultMap = MapUtils.getHashMapInstance();
    	if(token == null || TokenManager.getToken(token) == null) {//无token信息
    		resultMap.put(Constant.TOKEN_STATUS, Constant.TOKEN_INVALID);
    		return resultMap;
    	}
    	//tokenMap中存在该token
        JWSObject jwsObject = JWSObject.parse(token);
        //Payload payLoad = jwsObject.getPayload();
        JWSVerifier jwsVerifier = new MACVerifier(SECRET);

        if (jwsObject.verify(jwsVerifier)) {//验证通过
        	JWTUtils.checkIfTokenExpired(token,resultMap);
        	//检查是否过期
        }else {
        	resultMap.put(Constant.TOKEN_STATUS, Constant.TOKEN_INVALID);
        }
        return resultMap;

    }
    
    private static void checkIfTokenExpired(String token,Map<String,Object> resultMap) {
    	
         if (TokenManager.isExpired(token)) {//已过期
             resultMap.put(Constant.TOKEN_STATUS,Constant.TOKEN_EXPIRED);
         }else {//未过期
        	 resultMap.put(Constant.TOKEN_STATUS,Constant.TOKEN_VALID);
//        	 resultMap.put(Constant.PAY_LOAD, jsonObject);
         }
    }

	
    public static Map<String,Object> prepareTokenParams(TokenInfo tokenInfo) {
    	
    	if(tokenInfo.getCustomerId() == null)
    		BusinessExceptionUtils.throwNewBusinessException("用户登录错误");
		
    	return tokenInfo.toMap();
    	
    }
    
    /*public static Map<String,Object> prepareTokenParams(Integer customerId) {
    	
    	if(customerId == null)
    		BusinessExceptionUtils.throwNewBusinessException("用户登录错误");
    	
    	Map<String,Object> map = MapUtils.getHashMapInstance();
    	map.put(Constant.CUSTOMERID, customerId);
    	Calendar date = Calendar.getInstance();
		date.add(Constant.HOUR_FIELD, Constant.NUMBER_8);
		map.put(Constant.EXPIRES, date.getTime().getTime());
		
    	return map;
    	
    }*/
    
}
