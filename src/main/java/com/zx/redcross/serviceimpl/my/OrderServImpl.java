package com.zx.redcross.serviceimpl.my;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.my.IOrderMapper;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.my.IOrderServ;

@Service
public class OrderServImpl implements IOrderServ{

	@Autowired
	private IOrderMapper mapper;
	
	
	@Override
	public List<Map<String, Object>> ListOrderByCustomerId(String status,Integer customerId, Page page) {
		
		return mapper.ListOrderByCustomerId(status,customerId, page);
	}

}
