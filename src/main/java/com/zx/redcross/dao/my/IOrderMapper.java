package com.zx.redcross.dao.my;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Page;

public interface IOrderMapper {
	
	List<Map<String,Object>> ListOrderByCustomerId(
			@Param("customerId") Integer customerId,@Param("page") Page page);
	
	
}
