package com.zx.redcross.serviceimpl.my;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.my.ICourseRecordMapper;
import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseRecord;
import com.zx.redcross.service.my.ICourseRecordServ;

@Service
public class CourseRecordServImpl implements ICourseRecordServ {

	@Autowired
	private ICourseRecordMapper courseRecordmapper;
	

	@Override
	public List<CourseRecord> listCourseRecordByCustomerAndSubjectId(CourseRecord record) {
		return courseRecordmapper.listCourseRecordByCustomerAndSubjectId(record);
	}

	@Override
	public Boolean saveCourseRecord(CourseRecord record) {
		Integer count=courseRecordmapper.findCourseRecordCount(record);
		if(count==0) {
			return courseRecordmapper.saveCourseRecord(record);
		}else {
			return courseRecordmapper.updateCourseRecord(record);
		}
	}

	@Override
	public CourseRecord getNextCourseInfo(CourseRecord record) {
		List<CourseRecord> recordList = courseRecordmapper.listCourseRecordByCustomerAndSubjectId(record);
		recordList.sort(new Comparator<CourseRecord>() {
			@Override
			public int compare(CourseRecord record1, CourseRecord record2) {
				return record2.getCourse().getCindex() - record1.getCourse().getCindex();
			}
		});
		
		if(recordList.size() > 0) 
			return recordList.get(0);
		else {
			Course c = courseRecordmapper.getFirstIndexCourse(record.getCourseSubjectId());
			CourseRecord cr = new CourseRecord();
			cr.setCourse(c);
			return cr;
		}
	}

}
