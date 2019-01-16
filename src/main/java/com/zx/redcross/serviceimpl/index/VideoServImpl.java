package com.zx.redcross.serviceimpl.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.index.IVideoMapper;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.VideoBuyRecord;
import com.zx.redcross.service.index.IVideoServ;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;

@Service
public class VideoServImpl implements IVideoServ {

	
	@Autowired
	private IVideoMapper mapper;
	
	@Override
	public List<Map<String,Object>> listVideo(Integer customerId,Page page) {
		return mapper.listVideo(customerId,page);
	}

	@Override
	public Map<String,Object> getVideo(Integer customerId, Integer videoId) {
		return mapper.getVideo(customerId, videoId);
	}

	@Override
	public Boolean saveVideoBuyRecord(VideoBuyRecord videoBuyRecord) {
		
		videoBuyRecord.setStatus(Constant.WAIT_TO_PAY);
		if(mapper.getVideo(videoBuyRecord.getCustomer().getId(), videoBuyRecord.getVideo().getId()) != null)
			return mapper.updateVideoBuyRecord(videoBuyRecord);
		else 
			return mapper.saveVideoBuyRecord(videoBuyRecord);
	}

	@Override
	public Boolean updateVideoBuyRecord(VideoBuyRecord videoBuyRecord) {
		if(videoBuyRecord.getStatus() != Constant.PAY_CANCEL 
				&& videoBuyRecord.getStatus() != Constant.PAY_COMPLETE
				&& videoBuyRecord.getStatus() != Constant.WAIT_TO_PAY
				)
			BusinessExceptionUtils.throwNewBusinessException("状态不合法");
		return mapper.updateVideoBuyRecord(videoBuyRecord);
	}
	

}
