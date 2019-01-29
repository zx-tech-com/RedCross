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
	

	List<Map<String, Object>> listCourseBySubject(Integer subjectId, Page page);
	
	Map<String, Object> getCourseById(Integer id);
	
	Boolean addCourse(Course course, MultipartFile file);
	
	Boolean addCourseSubject(CourseSubject courseSubject,MultipartFile imgUrl,MultipartFile ccieUrl);
	//通过subjectId查找到科目类型
	Map<String, Object> findCourseSubject(Integer subjectId);
	//统计完成课时
	Integer getCountRecord(Integer customerId, Integer courseSubjectId);
	//保存观看结束的课程视频记录,转移到ICourseRecordServ中。
//	void saveCountRecord(Integer customerId, Integer courseId);
	
	/**
	 * @param subjectId
	 * @param customerId 可选的，用于表示该用户是否已经购买该科目
	 * @return
	 */
	Map<String,Object> getCourseSubjectAndPayStatus(Integer subjectId,Integer customerId);
	
	
	//后台管理（查询考试订单人员信息）
	List<ExamOrder> adminListExamOrder();
	//后台管理（添加考试科目）
	void saveCourseSubject(CourseSubject courseSubject);
	//后台评论（删除考试科目）
	Boolean adminDeleteCourseSubject(Integer courseSubjectId);
	//后台删除科目下面的课程
	Boolean adminDeleteCourse(Integer courseId);

	Boolean adminUpdateCourseSubject(CourseSubject courseSubject, MultipartFile imgUrl,
			MultipartFile ccieUrl);
	//后台修改科目下面的课程
	Boolean adminUpdateCourse(Course course);
	//批量删除科目
	Boolean adminDeleteBatchVideo(String ids);
	//批量删除课程
	Boolean adminDeleteBatchCourseVideo(String ids);


	List<Course> listCourseBySubjectSub(Course course);

	



}
