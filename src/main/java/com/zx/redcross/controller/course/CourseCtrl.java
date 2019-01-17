package com.zx.redcross.controller.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.course.ICourseServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("/course")
public class CourseCtrl {

	@Autowired
	private ICourseServ courseServImpl;
	
	@FrontEnd
	@RequestMapping("/listCourseSubject")
	public Map<String,Object> listCourseSubject() {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<Map<String, Object>> courseSubjectList = courseServImpl.listCourseSubject();
		if(null != courseSubjectList) {
			map.put(Constant.DATA, courseSubjectList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}
	@RequestMapping("/findCourseSubject")
	public Map<String,Object> findCourseSubject(@RequestParam(required=true) Integer subjectId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		CourseSubject coSubject = courseServImpl.findCourseSubject(subjectId);
		if(null != coSubject) {
			map.put(Constant.DATA, coSubject);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}
	
	

	@FrontEnd
	@RequestMapping("/listCourse")
	public Map<String,Object> listCourseBySubject(@RequestParam(required=true) Integer subjectId,Page page) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<Map<String, Object>> courseList = courseServImpl.listCourseBySubject(subjectId,page);
		if(null != courseList) {
			map.put(Constant.DATA, courseList);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}

	@FrontEnd
	@RequestMapping("/getCourse")
	public Map<String,Object> getCourseById(@RequestParam Integer id) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Map<String, Object> course = courseServImpl.getCourseById(id);
		map.put(Constant.STATUS,Constant.STATUS_FAILURE);
		if(null != course) {
			map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, course);
		}
		return map;
	}
	
	
	/**
	 * 判断用户看的课程视频多少集，以及那些集看了
	 * @param course
	 * @return
	 */
	@RequestMapping("/getCourseRecord")
	public Map<String,Object> getCourseRecord(Integer customerId,Integer courseSubjectId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//统计完成了多少课时
		Integer count=courseServImpl.getCountRecord(customerId,courseSubjectId);
		map.put(Constant.DATA, count);
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
//		//查询课程那一集是否观看了
//		List<CourseRecord> courseRecords=courseServImpl.findAllRecord(customerId,course);
		return map;
	}
	/**
	 * 看完视频保存记录
	 * @param course
	 * @return
	 */
	@RequestMapping("/saveCourseRecord")
	public Map<String,Object> saveCourseRecord(Integer customerId,Integer courseId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		courseServImpl.saveCountRecord(customerId,courseId);
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}
	
	
	
	
	
	
	
	
	//===============================后台管理需要用到的接口===================================

	/**
	 * 后台查询报考人员信息
	 * @param 
	 * @return
	 */
	@BackEnd
	@RequestMapping("/adminListExamOrder")
	public Map<String,Object> adminListExamOrder() {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<ExamOrder> examOrders=courseServImpl.adminListExamOrder();
		map.put(Constant.DATA, examOrders);
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}
	
	/**
	 * 处理考试科目
	 * @param 
	 * @return
	 */
	/**
	 * 查询考试科目
	 * @return
	 */
	@BackEnd
	@RequestMapping("/adminListCourseSubject")
	public Map<String,Object> adminListCourseSubject() {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Map<String, Object>> courseSubjectList=courseServImpl.listCourseSubject();
		map.put(Constant.DATA, courseSubjectList);
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}
	/**
	 * 添加考试科目
	 * @return
	 */
	@BackEnd
	@RequestMapping("/adminAddCourseSubject")
	public Map<String,Object> adminAddCourseSubject( CourseSubject courseSubject,
					@Param(value = "imgUrl")MultipartFile imgUrl,@Param(value = "ccieUrl")MultipartFile ccieUrl ) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.addCourseSubject(courseSubject,imgUrl,ccieUrl);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
	/**
	 * 删除考试科目
	 * @return
	 */
	@BackEnd
	@RequestMapping("/adminDeleteCourseSubject")
	public Map<String,Object> adminDeleteCourseSubject(Integer courseSubjectId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.adminDeleteCourseSubject(courseSubjectId);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
	
	/**
	 * 修改考试科目
	 * @return
	 */
	@BackEnd
	@RequestMapping("/adminUpdateCourseSubject")
	public Map<String,Object> adminUpdateCourseSubject(CourseSubject courseSubject,
			@Param(value = "imgUrl")MultipartFile imgUrl,@Param(value = "ccieUrl")MultipartFile ccieUrl) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag=courseServImpl.adminUpdateCourseSubject(courseSubject,imgUrl,ccieUrl);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
	
	
	/**
	 * 处理科目课程
	 * @param 
	 * @return
	 */
	/**
	 * 查询科目所有的课程
	 */
	@BackEnd
	@RequestMapping("/adminListCourse")
	public Map<String,Object> adminListCourse( Integer subjectId,Page page) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Map<String, Object>> Courses = courseServImpl.listCourseBySubject(subjectId,page);
		map.put(Constant.DATA,Courses);
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map; 
	}
	/**
	 * 添加课程
	 */
	@BackEnd
	@RequestMapping("/addCourse")
	public Map<String,Object> addCourse(Course course) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.addCourse(course);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	/**
	 * 删除课程
	 */
	@BackEnd
	@RequestMapping("/adminDeleteCourse")
	public Map<String,Object> adminDeleteCourse( Integer courseId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.adminDeleteCourse(courseId);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
	
	/**
	 * 修改课程
	 */
	@BackEnd
	@RequestMapping("/adminUpdateCourse")
	public Map<String,Object> adminUpdateCourse( Course course) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.adminUpdateCourse(course);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
}
