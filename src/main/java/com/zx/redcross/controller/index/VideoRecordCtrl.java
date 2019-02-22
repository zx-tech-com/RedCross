package com.zx.redcross.controller.index;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.VideoRecord;
import com.zx.redcross.service.my.IVideoRecordServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
public class VideoRecordCtrl {

	@Autowired
	private IVideoRecordServ videoRecordServImpl;
	
	@RequestMapping("/saveVideoRecord")
	@Open
	public Map<String,Object> saveVideoRecord(@RequestBody VideoRecord record) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		if(videoRecordServImpl.saveVideoRecord(record))
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		else
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		return map;
	}
	
	@RequestMapping("/getVideoProgress")
	public Map<String,Object> getVideoProgress(@RequestBody VideoRecord record) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		map.put(Constant.DATA, videoRecordServImpl.getVideoRecordByCustomerAndVideoId(record));
		return map;
	}
	
}
