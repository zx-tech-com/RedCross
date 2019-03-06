package com.zx.redcross.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.zx.redcross.tool.Constant;

public class SMS_service {
	
	//初始化ascClient
	public static IAcsClient InitAscClient() throws ClientException {
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", SMS_config.ACCESSKEYID,
				SMS_config.ACCESSKEYSECRET);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou",SMS_config.PRODUCT, SMS_config.DOMAIN);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		return acsClient;
	}
	
	//接收发送过来的手机号码
	public static SendSmsRequest getPhoneNumbers(String tel){
		 //组装请求对象
		 SendSmsRequest request = new SendSmsRequest();
		 //使用post提交
		 request.setMethod(MethodType.POST);
		 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
		 request.setPhoneNumbers(tel);
		 //必填:短信签名-可在短信控制台中找到
		 request.setSignName(SMS_config.SIGNNAME);
		 //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
		 request.setTemplateCode(SMS_config.TEMPLATECODE);
		 return request;
	}
	
	//判断短信的发送结果
	public static String getRequest(String tel) throws ClientException {
		IAcsClient acsClient=InitAscClient();
		SendSmsRequest request=getPhoneNumbers(tel);
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			return Constant.STATUS_SUCCESS;
		}else {
			return sendSmsResponse.getCode();
		}

	}
	
}
