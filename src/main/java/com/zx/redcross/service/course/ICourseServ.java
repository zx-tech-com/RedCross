package com.zx.redcross.service.course;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.Page;

public interface ICourseServ {
	
	List<Map<String, Object>> listCourseSubject();
	
	List<Map<String, Object>> listCourseBySubject(Integer subjectId,Page page);
	
	Map<String, Object> getCourseById(Integer id);
	
	Boolean addCourse(Course course);
	
	Boolean addCourseSubject(CourseSubject courseSubject, MultipartFile thumbnailUrl, MultipartFile certificateUrl);
	//通过subjectId查找到科目类型
	CourseSubject findCourseSubject(Integer subjectId);
	//统计完成课时
	Integer getCountRecord(Integer customerId, Integer courseId);
	//保存观看结束的课程视频记录
	void saveCountRecord(Integer customerId, Integer courseId);
	//后台管理（查询考试订单人员信息）
	List<ExamOrder> adminListExamOrder();
	//后台管理（添加考试科目）
	void saveCourseSubject(CourseSubject courseSubject);
	//后台评论（删除考试科目）
	Boolean adminDeleteCourseSubject(Integer courseSubjectId);
	//后台删除科目下面的课程
	Boolean adminDeleteCourse(Integer courseId);

	Boolean adminUpdateCourseSubject(CourseSubject courseSubject, MultipartFile thumbnailUrl,
			MultipartFile certificateUrl);



}
