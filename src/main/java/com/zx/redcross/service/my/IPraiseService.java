package com.zx.redcross.service.my;

import java.util.List;
import java.util.Map;

import com.zx.redcross.entity.Page;

public interface IPraiseService {

	List<Map<String, Object>> ListPraiseByCustomerId(Integer customerId, Page page);



}
