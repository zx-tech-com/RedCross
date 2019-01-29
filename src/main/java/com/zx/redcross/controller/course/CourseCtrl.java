package com.zx.redcross.controller.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseRecord;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.course.ICourseServ;
import com.zx.redcross.service.my.ICourseRecordServ;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;
import com.zx.redcross.tool.StringUtils;

@RestController
@RequestMapping("/course")
public class CourseCtrl {

	@Autowired
	private ICourseServ courseServImpl;
	@Autowired
	private ICourseRecordServ courseRecordServImpl;
	
	@FrontEnd
	@RequestMapping("/listCourseSubject")
	@Open
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
	@Open
	public Map<String,Object> findCourseSubject(@RequestParam(required=true) Integer subjectId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		Map<String, Object> coSubject = courseServImpl.findCourseSubject(subjectId);
		if(null != coSubject) {
			map.put(Constant.DATA, coSubject);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}
	
	
	@BackEnd
	@RequestMapping("/getCourseSubject")
	@Open
	public Map<String,Object> getdCourseSubject(
			@RequestParam(required=true) Integer subjectId,Integer customerId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		Map<String, Object> coSubject = courseServImpl.getCourseSubjectAndPayStatus(subjectId, customerId);
		if(null != coSubject) {
			map.put(Constant.DATA, coSubject);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}
	
	

	@FrontEnd
	@RequestMapping("/listCourse")
	@Open
	public Map<String,Object> listCourseBySubject(@RequestParam(required=true) Integer subjectId,Page page) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		List<Map<String, Object>> courseList = courseServImpl.listCourseBySubject(subjectId,page);
		if(null != courseList) {
			map.put(Constant.DATA, courseList);
			map.put(Constant.PAGE_SIZE, page.getPageSize());
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}

	@FrontEnd
	@RequestMapping("/getCourse")
	@Open
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
	public Map<String,Object> getCourseRecord(
			@RequestParam Integer customerId,@RequestParam Integer courseSubjectId) {
		if(customerId == null || courseSubjectId == null)
			BusinessExceptionUtils.throwNewBusinessException(Constant.ERROR_MISSIN_GPARAMETER);
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Map<String,Object> data = MapUtils.getHashMapInstance();
		//统计完成了多少课时
		Integer count=courseServImpl.getCountRecord(customerId,courseSubjectId);
		
		//统计总共有多少课时
		Page page = new Page();
		page.setPageNo(1);
		page.setPageSize(Integer.MAX_VALUE);
		int toalCount = courseServImpl.listCourseBySubject(courseSubjectId, page).size();
		
		//获取该用户该课程分类下的下一个应该播放的视频信息
		CourseRecord record = new CourseRecord();
		record.setCustomerId(customerId);
		record.setCourseSubjectId(courseSubjectId);
		CourseRecord cr = courseRecordServImpl.getNextCourseInfo(record);
		
		data.put(Constant.TOTAL_COUNT, toalCount);
		data.put("watch", count);
		data.put("videoUrl", cr.getCourse().getVideoUrl());
		data.put("progress", cr.getCurrentMinute());
		
		map.put(Constant.DATA, data);
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);

		return map;
	}
	/**
	 * 看完视频保存记录
	 * @param course
	 * @return
	 */
	@RequestMapping("/saveCourseRecord")
	public Map<String,Object> saveCourseRecord(@RequestBody CourseRecord record) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		if(courseRecordServImpl.saveCourseRecord(record))
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		else
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
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
	public Map<String,Object> adminAddCourseSubject(CourseSubject courseSubject,
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
	public Map<String,Object> adminListCourse( Course course) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Course> Courses = courseServImpl.listCourseBySubjectSub(course);
		map.put(Constant.DATA,Courses);
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map; 
	}
	/**
	 * 添加课程
	 */
	@BackEnd
	@RequestMapping("/addCourse")
	public Map<String,Object> addCourse(Course course,MultipartFile file) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.addCourse(course,file);
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
	public Map<String,Object> adminUpdateCourse(Course course) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.adminUpdateCourse(course);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
	
	
	/**
	 * 批量删除科目
	 * @param ids
	 * @return
	 */
	@RequestMapping("/adminDeleteBatchCourseSubject")
	public Map<String,Object> adminDeleteBatchCourseSubject(Integer[] ids){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		if(ids == null || ids.length == 0) {
			BusinessExceptionUtils.throwNewBusinessException("未传递参数！");
		}
		Boolean flag = courseServImpl.adminDeleteBatchVideo(StringUtils.convertArrayToString(ids));
		map.put(Constant.STATUS, flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	
	/**
	 * 批量删除课程
	 * @param ids
	 * @return
	 */
	@RequestMapping("/adminDeleteBatchCourseVideo")
	public Map<String,Object> adminDeleteBatchCourseVideo(Integer[] ids){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		if(ids == null || ids.length == 0) {
			BusinessExceptionUtils.throwNewBusinessException("未传递参数！");
		}
		Boolean flag = courseServImpl.adminDeleteBatchCourseVideo(StringUtils.convertArrayToString(ids));
		map.put(Constant.STATUS, flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
		
	}
}
