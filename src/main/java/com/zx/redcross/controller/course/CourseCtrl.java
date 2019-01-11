package com.zx.redcross.controller.course;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.course.ICourseServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("/course")
public class CourseCtrl {

	@Autowired
	private ICourseServ courseServImpl;
	
	@RequestMapping("/listCourseSubject")
	public Map<String,Object> listCourseSubject() {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<CourseSubject> courseSubjectList = courseServImpl.listCourseSubject();
		if(null != courseSubjectList) {
			map.put(Constant.DATA, courseSubjectList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}

	@RequestMapping("/listCourse")
	public Map<String,Object> listCourseBySubject(
			@RequestParam Integer subjectId,@RequestParam Page page) {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<Course> courseList = courseServImpl.listCourseBySubject(subjectId,page);
		if(null != courseList) {
			map.put(Constant.DATA, courseList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}

	@RequestMapping("/getCourse")
	public Map<String,Object> getCourseById(@RequestParam Integer id) {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Course course = courseServImpl.getCourseById(id);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != course) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, course);
		}
		return map;
	}

	@RequestMapping("/addCourse")
	public Map<String,Object> addCourse(@RequestParam Course course) {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.addCourse(course);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
		
	}

	@RequestMapping("/addCourseSubject")
	public Map<String,Object> saveCourseSubject(@RequestParam CourseSubject courseSubject) {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.addCourseSubject(courseSubject);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
		
	}
	
}
