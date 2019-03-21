package com.zx.redcross.controller.course;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.CourseRecord;
import com.zx.redcross.entity.CourseSubjectPrice;
import com.zx.redcross.service.course.CoursePriceServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("/coursePrice")
public class CoursePriceCtrl {
	
	@Autowired
	private CoursePriceServ coursePriceServImpl;
	

	@RequestMapping("/listCoursePrice")
	@Open
	public Map<String,Object> listCoursePrice(Integer courseSubjectId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<CourseSubjectPrice> courseSubjectPrices = coursePriceServImpl.listCoursePrice(courseSubjectId);
		if(null != courseSubjectPrices) {
			map.put(Constant.DATA, courseSubjectPrices);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}

}
