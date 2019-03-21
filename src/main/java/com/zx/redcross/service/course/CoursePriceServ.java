package com.zx.redcross.service.course;

import java.util.List;

import com.zx.redcross.entity.CourseSubjectPrice;

public interface CoursePriceServ {

	List<CourseSubjectPrice> listCoursePrice(Integer courseSubjectId);

}
