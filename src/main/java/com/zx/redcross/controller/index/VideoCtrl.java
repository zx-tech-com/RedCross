package com.zx.redcross.controller.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.VideoBuyRecord;
import com.zx.redcross.service.index.IVideoServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

/**
 * 付费视频模块
 * @author 罗勇,Daryl
 */
@RestController
@RequestMapping("/video")
public class VideoCtrl {
	
	@Autowired
	private IVideoServ videoServImpl;
	
	
	/**
	 * 视频列表，查询所有视频，并判断用户是否购买了
	 * @param customerId
	 * @return
	 */
	@FrontEnd
	@RequestMapping("/listVideo")
	public Map<String,Object> listVideo(Integer customerId,Page page) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Map<String,Object>> videos = videoServImpl.listVideo(customerId,page);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != videos) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, videos);
			map.put(Constant.PAGE_SIZE, page.getPageSize());
		}
		return map;
	}
	
	@FrontEnd
	@RequestMapping("/getVideo")
	public Map<String,Object> getVideo(Integer customerId,Integer videoId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Map<String, Object> video = videoServImpl.getVideo(customerId,videoId);
		if(null != video) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, video);
		}else {
			map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		}
		return map;
	}
	
	@FrontEnd
	@RequestMapping("/saveVideoBuyRecord")
	public Map<String,Object> saveVideoBuyRecord(@RequestBody VideoBuyRecord videoBuyRecord) {
		//保存购买记录（实际保存的是正在购买状态为1）
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = videoServImpl.saveVideoBuyRecord(videoBuyRecord);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	@FrontEnd
	@RequestMapping("/updateVideoBuyRecord")
	public Map<String,Object> updateVideoBuyRecord(@RequestBody VideoBuyRecord videoBuyRecord) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = videoServImpl.updateVideoBuyRecord(videoBuyRecord);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
}
