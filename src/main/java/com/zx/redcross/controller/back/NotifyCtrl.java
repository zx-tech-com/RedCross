package com.zx.redcross.controller.back;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.Open;
import com.zx.redcross.exception.BusinessException;
import com.zx.redcross.pay.ali.AlipayUtils;
import com.zx.redcross.service.course.IExamPayRecordServ;
import com.zx.redcross.service.index.IVideoPayRecordServ;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;
import com.zx.redcross.tool.StringUtils;

/**
 * 微信支付宝的回调接口
 * @author Daryl
 *
 */
@RestController
public class NotifyCtrl {

	private Logger logger = LogManager.getLogger(NotifyCtrl.class);

	@Autowired
	private IVideoPayRecordServ videoPayRecordServImpl;
	@Autowired
	private IExamPayRecordServ examPayRecordServImpl;
	
	@Open
	@RequestMapping("alipay/authResult")
	public String getPayNotify(HttpServletRequest request) throws Exception {

		Map<String, String> map = getParameterMap(request);
		StringBuilder logStr = new StringBuilder("\r\n==========新的authResult===========");
		String result = "";
		try {
			// 判断是否是支付宝发来的异步通知
			if (!StringUtils.isNotEmpty(map.get("notify_id"))) {
				logStr.append("\r\n非支付宝发送的通知");
				result = "failure";
			}
			
			//判断notify_id是否可靠
			if (!AlipayUtils.verifyResponse(map.get("notify_id")).equals("true")) {
				logStr.append("\r\n异步通知返回的notify_id不匹配");
				result = "failure";
			}
			
			//判断是否是支付宝发来的异步通知
	        if (!AlipayUtils.rsaCheck(map)) {
	        	logStr.append("\r\n支付宝支付回调签名验证失败");
	        	result = "failure";
	        }
			if(result.equals("failure")) {
				logger.debug(logStr);
				return result;
			}
	        //判断交易状态
	        String tradeStatus = map.get("trade_status");
	        String buyerId = map.get("buyer_logon_id");
	        String amount = map.get("total_amount");
	        String notifyId = map.get("notify_id");
	        String outTradeNo = map.get("out_trade_no");
	        logStr.append("\r\n买家支付宝账号 : " + buyerId);
	        logStr.append("\r\n支付金额 : " + amount);
	        logStr.append("\r\n支付宝notify_id: " + notifyId);
	        logStr.append("\r\n订单号: " + outTradeNo);
	        if ("WAIT_BUYER_PAY".equals(tradeStatus)) {
	        	logStr.append("\r\n交易创建成功，等待买家付款");
	            result = "success";
	        }
	        if ("TRADE_CLOSED".equals(tradeStatus)) {
	        	logStr.append("\r\n未付款交易超时关闭，或支付完成后全额退款");
	        	result = "success";
	        }
	        if ("TRADE_SUCCESS".equals(tradeStatus)) {
	        	logStr.append("\r\n交易支付成功");
	        	updateOrderStatus(outTradeNo);//业务数据更新
	        	result = "success";
	        }
	        if ("TRADE_FINISHED".equals(tradeStatus)) {
	        	logStr.append("\r\n交易结束，不可退款");
	        	result = "success";
	        }
		}catch(BusinessException e) {
			logStr.append("\r\n" + e.getMessage());
			result = "failure";
		}catch(Exception e) {
			result = "failure";
		}finally {
			logger.debug(logStr);
		}
		return result;
	}

	private void updateOrderStatus(String orderNumber) {
		if(!StringUtils.isNotBlank(orderNumber))
			BusinessExceptionUtils.throwNewBusinessException("订单号为空");
		String businessType = orderNumber.substring(0, 2);
		switch(businessType) {
		case Constant.BUSINESS_TYPE_EXAM_ORDER :
				examPayRecordServImpl.updateStatusByOrderNumber(orderNumber);
			break;
		case Constant.BUSINESS_TYPE_VIDEO_ORDER :
				videoPayRecordServImpl.updateStatusByOrderNumber(orderNumber);
			break;
		default :
			BusinessExceptionUtils.throwNewBusinessException("订单类型不存在");
		}
	}

	/**
	 * <p>
	 * 方法说明: TODO 获取请求参数
	 * <p>
	 * 返回说明: Map<String,String> parameterMap
	 **/
	private  Map<String, String> getParameterMap(HttpServletRequest request) {
		Map<String, String> receiveMap = MapUtils.getStringHashMapInstance();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			String[] values = requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			receiveMap.put(name, valueStr);
		}
		return receiveMap;
	}

}
