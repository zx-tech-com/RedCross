package com.zx.redcross.serviceimpl.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.course.ICourseMapper;
import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.course.ICourseServ;

@Service
public class CourseServImpl implements ICourseServ{
	
	@Autowired
	private ICourseMapper courseMapper;
	
	@Override
	public List<CourseSubject> listCourseSubject() {
		return courseMapper.listCourseSubject();
	}

	@Override
	public List<Course> listCourseBySubject(Integer subjectId,Page page) {
		return courseMapper.listCourseBySubject(subjectId,page);
	}

	@Override
	public Course getCourseById(Integer id) {
		return courseMapper.getCourseById(id);
	}

	@Override
	public Boolean saveCourse(Course course) {
		return courseMapper.saveCourse(course);
	}

	@Override
	public Boolean saveCourseSubject(CourseSubject courseSubject) {
		return courseMapper.saveCourseSubject(courseSubject);
	}

}
