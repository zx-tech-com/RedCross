package com.zx.redcross.controller.course;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.zx.redcross.entity.Video;
import com.zx.redcross.service.course.ICourseServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.FileUtils;
import com.zx.redcross.tool.MapUtils;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/course")
@Api(tags="所有课程相关接口")
public class CourseCtrl {

	@Autowired
	private ICourseServ courseServImpl;
	
	@FrontEnd
	@RequestMapping("/listCourseSubject")
//	@ApiOperation(value = "列举所有培训信息",httpMethod= "GET",produces="application/json")
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
	/*@ApiOperation(value = "列举某个培训下的所有课程信息",httpMethod= "GET",produces="application/json")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "subjectId", value = "课程(培训)id", required = true, paramType = "query", dataType = "String")
	})*/
	public Map<String,Object> listCourseBySubject(@RequestParam(required=true) Integer subjectId,Page page) {
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
//	@RequestMapping("/getCourse")
//	@ApiOperation(value="获取课程的具体信息",httpMethod= "GET",produces="application/json")
	public Map<String,Object> getCourseById(@RequestParam Integer id) {
			/*@ApiParam(name="id",value="课程id",required=true)*/ 
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Course course = courseServImpl.getCourseById(id);
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
	public Map<String,Object> getCourseRecord(Integer customerId,Integer courseId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//统计完成了多少课时
		Integer count=courseServImpl.getCountRecord(customerId,courseId);
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
	@BackEnd
	@RequestMapping("/addCourse")
	public Map<String,Object> addCourse(@RequestBody Course course) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.addCourse(course);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	
	
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
		List<CourseSubject> courseSubjectList=courseServImpl.listCourseSubject();
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
	public Map<String,Object> adminAddCourseSubject(@RequestBody CourseSubject courseSubject,MultipartFile file) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		String	absoluteBasePath=Constant.IMG_ABSOLUTE_BASE_PATH+ Constant.COURSE_SUBJECT;
		String path=FileUtils.saveFile(absoluteBasePath, file);
		courseSubject.setThumbnailUrl(path);
		Boolean flag = courseServImpl.addCourseSubject(courseSubject);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
	/**
	 * 删除考试科目
	 * @return
	 */
	@BackEnd
	@RequestMapping("/adminDeleteCourseSubject")
	public Map<String,Object> adminDeleteCourseSubject(@RequestBody Integer courseSubjectId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.adminDeleteCourseSubject(courseSubjectId);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
	
//	/**
//	 * 修改考试科目
//	 * @return
//	 */
//	@BackEnd
//	@RequestMapping("/adminUpdateCourseSubject")
//	public Map<String,Object> adminUpdateCourseSubject(@RequestBody CourseSubject courseSubject,MultipartFile file) {
//		Map<String,Object> map = MapUtils.getHashMapInstance();
//		//通过科目id查询修改到科目
//		CourseSubject courseSubject2=
//		
//		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
//		return map; 
//	}
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
	public Map<String,Object> adminListCourse(@RequestBody Integer subjectId,Page page) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Course> Courses = courseServImpl.listCourseBySubject(subjectId,page);
		map.put(Constant.DATA,Courses);
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map; 
	}
	/**
	 * 删除课程
	 */
	@BackEnd
	@RequestMapping("/adminDeleteCourse")
	public Map<String,Object> adminDeleteCourse(@RequestBody Integer courseId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.adminDeleteCourse(courseId);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
	/**
	 * 处理付费视频
	 * @param 
	 * @return
	 */
	/**
	 * 付费视频列表
	 */
	@BackEnd
	@RequestMapping("/adminListVideo")
	public Map<String,Object> adminListVideo(){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Video> videos= courseServImpl.adminListVideo();
		if(null!=videos) {
			map.put(Constant.DATA, videos);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}else {
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		}
		return map;
	}
	/**
	 * 添加付费视频
	 */
	@BackEnd
	@RequestMapping("/adminSaveVideo")
	public Map<String,Object> adminSaveVideo(Video video){
		//未处理视频上传过程
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag= courseServImpl.adminSaveVideo(video);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	/**
	 * 删除付费视频
	 */
	@BackEnd
	@RequestMapping("/adminDeleteVideo")
	public Map<String,Object> adminDeleteVideo(@RequestBody Integer videoId) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.adminDeleteVideo(videoId);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}	
	/**
	 * 修改付费视频
	 */
	@BackEnd
	@RequestMapping("/adminUpdateVideo")
	public Map<String,Object> adminUpdateVideo(@RequestBody Video video) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Boolean flag = courseServImpl.adminUpdateVideo(video);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map; 
	}
	
}
