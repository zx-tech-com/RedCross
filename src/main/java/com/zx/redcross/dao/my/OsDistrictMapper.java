package com.zx.redcross.dao.my;

import java.util.List;

import com.zx.redcross.entity.OsDistrict;

public interface OsDistrictMapper {
	//根据Id查找一个OsDistrict
	public OsDistrict findOsdistrictById(Integer id);
	//通过区域id查找下一级区域
	public List<OsDistrict> findByUpid(Integer id);
}
