package com.zx.redcross.dao.my;

import java.util.List;
import java.util.Map;

public interface OsDistrictMapper {
	//根据Id查找一个OsDistrict
	public Map<String,Object> findOsdistrictById(Integer id);
	//通过区域id查找下一级区域
	public List<Map<String,Object>> findByUpid(Integer id);
}
