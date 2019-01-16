package com.zx.redcross.service.course;

import java.util.List;

import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.Page;

public interface ICourseServ {
	
	List<CourseSubject> listCourseSubject();
	
	List<Course> listCourseBySubject(Integer subjectId,Page page);
	
	Course getCourseById(Integer id);
	
	Boolean addCourse(Course course);
	
	Boolean addCourseSubject(CourseSubject courseSubject);
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


}
