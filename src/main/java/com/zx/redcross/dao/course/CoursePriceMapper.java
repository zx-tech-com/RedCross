package com.zx.redcross.dao.course;

import java.util.List;

import com.zx.redcross.entity.CourseSubjectPrice;

public interface CoursePriceMapper {

	List<CourseSubjectPrice> listCoursePrice(Integer courseSubjectId);
	

}
