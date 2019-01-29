package com.zx.redcross.service.my;

import java.util.List;

import com.zx.redcross.entity.CourseRecord;

/**
 * @author Daryl
 */
public interface ICourseRecordServ {
	
	List<CourseRecord> listCourseRecordByCustomerAndSubjectId(CourseRecord record);
	
	/**
	 * 直接获取用户的下一个待播放视频
	 * @param record
	 * @return
	 */
	CourseRecord getNextCourseInfo(CourseRecord record);
	
	Boolean saveCourseRecord(CourseRecord record);
}
