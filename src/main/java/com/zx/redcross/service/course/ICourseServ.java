package com.zx.redcross.service.course;

import java.util.List;

import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.Page;

public interface ICourseServ {
	
	List<CourseSubject> listCourseSubject();
	
	List<Course> listCourseBySubject(Integer subjectId,Page page);
	
	Course getCourseById(Integer id);
	
	Boolean addCourse(Course course);
	
	Boolean addCourseSubject(CourseSubject courseSubject);
}
