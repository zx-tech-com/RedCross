package com.zx.redcross.service.course;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Video;

public interface ICourseServ {
	
	List<CourseSubject> listCourseSubject();
	
	List<Course> listCourseBySubject(Integer subjectId,Page page);
	
	Course getCourseById(Integer id);
	
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
	//后台查询所有付费视频
	List<Video> adminListVideo();
	//添加付费视频
	Boolean adminSaveVideo(Video video);
	//删除付费视频
	Boolean adminDeleteVideo(Integer videoId);
	//修改付费视频
	Boolean adminUpdateVideo(Video video);
	//后台修改考试科目
	Boolean adminUpdateCourseSubject(CourseSubject courseSubject, MultipartFile thumbnailUrl, MultipartFile certificateUrl);





}
