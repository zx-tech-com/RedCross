package com.zx.redcross.controller.index;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.Video;
import com.zx.redcross.service.index.IVideoPayRecordServ;
import com.zx.redcross.service.index.IVideoServ;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("pay")
public class VideoPayRecordCtrl {

	@Autowired
	private IVideoPayRecordServ payImpl;
	
	@Autowired
	private IVideoServ videoImpl;
	
	private final String payMethod = "1";//支付宝支付
	private final String name = "name";
	private final String price = "price";
	private final String signedParams = "signedParams";
	
	@Open
	@RequestMapping("ali/video")
	public Map<String,Object> getSignedParams(String orderNumber){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		if(videoImpl.updatePayMethod(payMethod,orderNumber)) {
			Map<String, Object> dataMap = MapUtils.getHashMapInstance();
			Video video = videoImpl.getVideoByVideoBuyOrderNumber(orderNumber);
			dataMap.put(price, video.getPrice());
			dataMap.put(name, video.getVname());
			dataMap.put(signedParams, payImpl.getSignedParams(orderNumber));
			map.put(Constant.DATA, dataMap);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}else
			BusinessExceptionUtils.throwNewBusinessException("设置支付方式失败");
		return map;
	}
	
}
