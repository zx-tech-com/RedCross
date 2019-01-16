package com.zx.redcross.service.index;

import java.util.List;
import java.util.Map;

import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.VideoBuyRecord;

public interface IVideoServ {
	
	//查询所有视频
	List<Map<String, Object>> listVideo(Integer customerId,Page page);
	
	//通过视频id查找一个视频的详细信息
	Map<String, Object> getVideo(Integer customerId, Integer videoId);
	
	//会员点击支付后会生成一条正在记录
	Boolean saveVideoBuyRecord(VideoBuyRecord videoBuyRecord);
	
	//会员点击支付后会生成一条支付成功记录
	Boolean updateVideoBuyRecord(VideoBuyRecord videoBuyRecord);
}
