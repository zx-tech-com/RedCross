package com.zx.redcross.dao.course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.Page;

public interface ICourseMapper {
	
	List<Map<String,Object>> listCourseSubject();
	
	List<Map<String,Object>> listCourseBySubject(@Param("customerId") Integer customerId,@Param("subjectId") Integer subjectId,@Param("page") Page page);
	
	Map<String,Object> getCourseById(@Param("id") Integer id,@Param("customerId") Integer customerId);
	
	Boolean saveCourse(@Param("course") Course course);
	
	Boolean saveCourseSubject(@Param("courseSubject") CourseSubject courseSubject);
	//通过subjectId查找到科目类型 
	Map<String,Object> findCourseSubject(
			@Param("subjectId")Integer subjectId);
	//查询观看完视频的集数
	Integer getCountRecord(@Param("customerId")Integer customerId, @Param("courseSubjectId")Integer courseSubjectId);
	//保存观看结束的课程视频记录，转移到ICourseRecordMapper中
	//void saveCountRecord(@Param("customerId")Integer customerId,@Param("courseId") Integer courseId);
	
	//参数customerID是可选的，用于表示该用户是否已经购买该科目)
	Map<String,Object> getCourseSubjectAndPayStatus(
			@Param("subjectId")Integer subjectId,@Param("customerId")Integer customerId);
	
	
	//后台查询考试订单信息
	List<ExamOrder> adminListExamOrder();
	//后台删除考试科目
	Boolean adminDeleteCourseSubject(@Param("courseSubjectId")Integer courseSubjectId);
	//后台删除考试科目的课程
	Boolean adminDeleteCourse(@Param("courseId")Integer courseId);

	boolean adminUpdateCourseSubject(CourseSubject courseSubject);
	//后台修改考试科目的课程
	Boolean adminUpdateCourse(@Param("course")Course course);

	List<CourseSubject> listCourseSubjectByIds(@Param("ids")String ids);

	Boolean adminDeleteBatchCourseSubject(@Param("ids")String ids);

	List<Course> listCourseVideoByIds(@Param("ids")String ids);

	Boolean adminDeleteBatchCourseVideo(@Param("ids")String ids);

	List<Course> listCourseBySubjectSub(@Param("course")Course course);

}
