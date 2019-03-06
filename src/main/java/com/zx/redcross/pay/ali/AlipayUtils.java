package com.zx.redcross.pay.ali;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

/**
 *<p>类  说  明: TODO
 **/
public class AlipayUtils {

    /**
     * 支付宝消息验证地址
     */
    private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

    /**
     *<p>方法说明: TODO 签名验证
     *<p>参数说明: @param params 通知返回来的参数数组
     *<p>参数说明: @param sign   比对的签名结果
     *<p>参数说明: @throws AlipayApiException
     *<p>返回说明: boolean 签名验证结果
     **/
    public static boolean rsaCheck(Map<String, String> params) throws AlipayApiException {
        return AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);
    }

    /**
     * 
     *<p>方法说明: 把需要发送的参数对按照顺序组装成 &key=value格式
     *<p>参数说明: @param params 需要发送的参数
     *<p>参数说明: @throws AlipayApiException
     */
    public static String getSignContent(Map<String, String> params) throws AlipayApiException {
    	return AlipaySignature.getSignContent(params);
    }
    
    
    /**
     *<p>方法说明: TODO 进行签名
     *<p>参数说明: @param params 需要签名的参数
     *<p>参数说明: @throws AlipayApiException
     *<p>返回说明: String 签名字符串
     **/
    public static String rsaSign (Map<String, String> params) throws AlipayApiException{
        String content = AlipaySignature.getSignCheckContentV2(params);
        return AlipaySignature.rsaSign(content, AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);
    }

    /**
     *<p>方法说明: TODO 解密
     *<p>参数说明: @param params 密文参数
     *<p>参数说明: @throws AlipayApiException
     *<p>返回说明: String 解密字符串
     **/
	public static String rsaDecrypt(Map<String, String> params) throws AlipayApiException{
	    return AlipaySignature.checkSignAndDecrypt(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.APP_PRIVATE_KEY, true, true, AlipayConfig.SIGN_TYPE);
	}
    /**
     * 获取远程服务器ATN结果,验证返回URL
     *
     * @param notifyId 通知校验ID
     * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
     *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
     */
    public static String verifyResponse(String notifyId) {
        // 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
        String urlValue = HTTPS_VERIFY_URL + "partner=" + AlipayConfig.SELLER_ID + "&notify_id=" + notifyId;
        return checkUrl(urlValue);
    }

    /**
     * 获取远程服务器ATN结果
     *
     * @param urlValue 指定URL路径地址
     * @return 服务器ATN结果 验证结果集： <br>
     * 			invalid命令参数不对 ,请检测返回处理中partner和key是否为空 <br>
     * 			true返回正确信息 <br>
     * 			false检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
     */
    private static String checkUrl(String urlValue) {
        String inputLine = "";
        try {
            URL url = new URL(urlValue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            inputLine = in.readLine().toString();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputLine;
    }

    /**
     *<p>方法说明: TODO 获取支付宝回调地址
     **/
/*    public static String getNotifyUrl(HttpServletRequest request){
        StringBuffer requestURL = request.getRequestURL();
        return requestURL.substring(0,requestURL.indexOf("/front/"))+AlipayConfig.NOTIFY_URL;
    }*/


}