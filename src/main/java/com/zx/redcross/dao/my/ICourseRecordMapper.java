package com.zx.redcross.dao.my;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseRecord;

public interface ICourseRecordMapper {

	List<CourseRecord> listCourseRecordByCustomerAndSubjectId(@Param("record") CourseRecord record);
	
	Boolean saveCourseRecord(@Param("record") CourseRecord record);
	
	Course getFirstIndexCourse (Integer subjectId);

	Integer findCourseRecordCount(@Param("record")CourseRecord record);

	Boolean updateCourseRecord(@Param("record")CourseRecord record);
	
}
