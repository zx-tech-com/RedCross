package com.zx.redcross.dao.course;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.Page;

public interface ICourseMapper {
	
	List<CourseSubject> listCourseSubject();
	
	List<Course> listCourseBySubject(@Param("subjectId") Integer subjectId,@Param("page") Page page);
	
	Course getCourseById(@Param("id") Integer id);
	
	Boolean saveCourse(@Param("course") Course course);
	
	Boolean saveCourseSubject(@Param("courseSubject") CourseSubject courseSubject);
}
