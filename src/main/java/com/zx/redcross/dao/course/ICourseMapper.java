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
	
	List<Map<String,Object>> listCourseBySubject(@Param("subjectId") Integer subjectId,@Param("page") Page page);
	
	Map<String,Object> getCourseById(@Param("id") Integer id);
	
	Boolean saveCourse(@Param("course") Course course);
	
	Boolean saveCourseSubject(@Param("courseSubject") CourseSubject courseSubject);
	//通过subjectId查找到科目类型
	CourseSubject findCourseSubject(@Param("subjectId")Integer subjectId);
	//查询观看完视频的集数
	Integer getCountRecord(@Param("customerId")Integer customerId, @Param("courseId")Integer courseId);
	//保存观看结束的课程视频记录
	void saveCountRecord(@Param("customerId")Integer customerId,@Param("courseId") Integer courseId);
	//后台查询考试订单信息
	List<ExamOrder> adminListExamOrder();
	//后台删除考试科目
	Boolean adminDeleteCourseSubject(@Param("courseSubjectId")Integer courseSubjectId);
	//后台删除考试科目的课程
	Boolean adminDeleteCourse(@Param("courseId")Integer courseId);

}
