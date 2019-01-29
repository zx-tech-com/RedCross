package com.zx.redcross.serviceimpl.my;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.my.IVideoRecordMapper;
import com.zx.redcross.entity.VideoRecord;
import com.zx.redcross.service.my.IVideoRecordServ;

/**
 * @author Daryl
 */
@Service
public class VideoRecordServImpl implements IVideoRecordServ{
	
	@Autowired
	private IVideoRecordMapper mapper;

	@Override
	public Map<String, Object> getVideoRecordByCustomerAndVideoId(VideoRecord record) {
		System.out.println(record);
		return mapper.getVideoRecordByCustomerAndVideoId(record);
	}

	@Override
	public Boolean saveVideoRecord(VideoRecord record) {
		return mapper.saveVideoRecord(record);
	}
	
	
	
	
}
