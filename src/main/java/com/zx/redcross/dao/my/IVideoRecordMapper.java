package com.zx.redcross.dao.my;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.VideoRecord;

public interface IVideoRecordMapper {
	
	Map<String,Object> getVideoRecordByCustomerAndVideoId(@Param("record") VideoRecord record);
	Boolean saveVideoRecord(@Param("record") VideoRecord record);
	
}
