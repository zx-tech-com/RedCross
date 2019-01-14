package com.zx.redcross.controller.course;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.course.ICourseServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/course")
@Api(tags="所有课程相关接口")
public class CourseCtrl {

	@Autowired
	private ICourseServ courseServImpl;
	
	@FrontEnd
	@RequestMapping("/listCourseSubject")
	@ApiOperation(value = "列举所有培训信息",httpMethod= "GET",produces="application/json")
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

	@FrontEnd
	@RequestMapping("/listCourse")
	@ApiOperation(value = "列举某个培训下的所有课程信息",httpMethod= "GET",produces="application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "subjectId", value = "课程(培训)id", required = true, paramType = "query", dataType = "String")
	})
	public Map<String,Object> listCourseBySubject(
			Integer subjectId,@ModelAttribute Page page) {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<Course> courseList = courseServImpl.listCourseBySubject(subjectId,page);
		if(null != courseList) {
			map.put(Constant.DATA, courseList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}

	@FrontEnd
	@RequestMapping("/getCourse")
	@ApiOperation(value="获取课程的具体信息",httpMethod= "GET",produces="application/json")
	public Map<String,Object> getCourseById(
			@ApiParam(name="id",value="课程id",required=true) @RequestParam Integer id) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Course course = courseServImpl.getCourseById(id);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != course) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, course);
		}
		return map;
	}
	
	//===============================后台管理需要用到的接口===================================
	@BackEnd
	@RequestMapping("/addCourse")
	public Map<String,Object> addCourse(@RequestBody Course course) {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.addCourse(course);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
		
	}
	
	@BackEnd
	@RequestMapping("/addCourseSubject")
	public Map<String,Object> saveCourseSubject(@RequestBody CourseSubject courseSubject) {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.addCourseSubject(courseSubject);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
	
}
