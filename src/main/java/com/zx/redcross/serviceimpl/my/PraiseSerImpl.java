package com.zx.redcross.serviceimpl.my;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.my.IPraiseMapper;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.my.IPraiseService;
@Service
public class PraiseSerImpl implements IPraiseService{
	
	@Autowired
	private IPraiseMapper praiseMapper;
	
	
	@Override
	public List<Map<String, Object>> ListPraiseByCustomerId(Integer customerId, Page page) {
		return praiseMapper.listPraiseByCustomerId(customerId,page);
	}

}
