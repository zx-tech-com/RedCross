package com.zx.redcross.dao.my;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.VideoRecord;

public interface IVideoRecordMapper {
	
	Map<String,Object> getVideoRecordByCustomerAndVideoId(@Param("record") VideoRecord record);
	Boolean saveVideoRecord(@Param("record") VideoRecord record);
	//查找是否有观看视频记录
	Integer findVideoRecordCount(@Param("record")VideoRecord record);
	//修改观看视频的观看记录
	Boolean updateVideoRecord(@Param("record")VideoRecord record);
	
}
