package com.zx.redcross.service.my;

import java.util.Map;

import com.zx.redcross.entity.VideoRecord;

/**
 * @author Daryl
 */
public interface IVideoRecordServ {
	
	Map<String,Object> getVideoRecordByCustomerAndVideoId( VideoRecord record);
	Boolean saveVideoRecord( VideoRecord record);
	
}
