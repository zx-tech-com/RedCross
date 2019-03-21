package com.zx.redcross.serviceimpl.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.course.CoursePriceMapper;
import com.zx.redcross.entity.CourseRecord;
import com.zx.redcross.entity.CourseSubjectPrice;
import com.zx.redcross.service.course.CoursePriceServ;
@Service
public class CoursePriceServImpl implements CoursePriceServ{
	@Autowired
	private CoursePriceMapper mapper;

	@Override
	public List<CourseSubjectPrice> listCoursePrice(Integer courseSubjectId) {
		return mapper.listCoursePrice(courseSubjectId);
	}

}
