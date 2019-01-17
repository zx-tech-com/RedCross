package com.zx.redcross.serviceimpl.course;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.dao.course.ICourseMapper;
import com.zx.redcross.entity.Course;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.course.ICourseServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.FileUtils;

@Service
public class CourseServImpl implements ICourseServ{
	
	@Autowired
	private ICourseMapper courseMapper;
	
	@Override
	public List<Map<String, Object>> listCourseSubject() {
		return courseMapper.listCourseSubject();
	}

	@Override
	public List<Map<String, Object>> listCourseBySubject(Integer subjectId,Page page) {
		return courseMapper.listCourseBySubject(subjectId,page);
	}

	@Override
	public Map<String, Object> getCourseById(Integer id) {
		return courseMapper.getCourseById(id);
	}

	@Override
	public Boolean addCourse(Course course) {
		return courseMapper.saveCourse(course);
	}

	@Override
	public Boolean addCourseSubject(CourseSubject courseSubject,MultipartFile imgUrl, MultipartFile ccieUrl) {
		String imgAbsoluteBasePath = Constant.IMG_ABSOLUTE_BASE_PATH + Constant.COURSE_SUBJECT;		
		
		//存储图片
		if(imgUrl!=null) {
			courseSubject.setThumbnailUrl(FileUtils.saveFile(imgAbsoluteBasePath, imgUrl));
		}
		
		if(ccieUrl!=null) {
			courseSubject.setCertificateUrl(FileUtils.saveFile(imgAbsoluteBasePath, ccieUrl));
		}
		
		if(!courseMapper.saveCourseSubject(courseSubject)) {
			FileUtils.removeFile(courseSubject.getThumbnailUrl());
			FileUtils.removeFile(courseSubject.getCertificateUrl());
			return false;
		}
		return true;
	}

	@Override
	public CourseSubject findCourseSubject(Integer subjectId) {

		return (CourseSubject) courseMapper.findCourseSubject(subjectId);
	}

	@Override
	public Integer getCountRecord(Integer customerId, Integer courseId) {

		return courseMapper.getCountRecord(customerId,courseId);
	}

	@Override
	public void saveCountRecord(Integer customerId, Integer courseId) {
		courseMapper.saveCountRecord(customerId,courseId);
	}

	@Override
	public List<ExamOrder> adminListExamOrder() {
		return courseMapper.adminListExamOrder();
	}

	@Override
	public void saveCourseSubject(CourseSubject courseSubject) {
		courseMapper.saveCourseSubject(courseSubject);
	}

	@Override
	public Boolean adminDeleteCourseSubject(Integer courseSubjectId) {
		Map<String, Object> courseSubject=courseMapper.findCourseSubject(courseSubjectId);
		if(!courseMapper.adminDeleteCourseSubject(courseSubjectId)) {
			return false;
		}
		//删除之前的图片
		if(null!=courseSubject.get("thumbnailUrl")
				&&((String) courseSubject.get("thumbnailUrl")).length()>0) {
			FileUtils.removeFile((String)courseSubject.get("thumbnailUrl"));	
		}
		if(courseSubject.get("certificateUrl")!=null
				&&((String) courseSubject.get("certificateUrl")).length()>0) {
			FileUtils.removeFile((String) courseSubject.get("certificateUrl"));
		}
		return true;
	}

	@Override
	public Boolean adminDeleteCourse(Integer courseId) {
		Map<String, Object> course=courseMapper.getCourseById(courseId);
		if(!courseMapper.adminDeleteCourse(courseId)) {
			return false;
		};
		String videoUrl =(String) course.get("videoUrl");
		if(null != videoUrl && videoUrl.length() >0) {
			FileUtils.removeFile(videoUrl);
		}
		return true;
	}

	@Override
	public Boolean adminUpdateCourseSubject(CourseSubject courseSubject
			,MultipartFile imgUrl, MultipartFile ccieUrl) {
		String imgAbsoluteBasePath = Constant.IMG_ABSOLUTE_BASE_PATH + Constant.COURSE_SUBJECT;		
		//存储图片
		if(imgUrl!=null) {
			courseSubject.setThumbnailUrl(FileUtils.saveFile(imgAbsoluteBasePath, imgUrl));
		}
				
		if(ccieUrl!=null) {
			courseSubject.setCertificateUrl(FileUtils.saveFile(imgAbsoluteBasePath, ccieUrl));
		}
		Map<String, Object> courseSubjectSub=courseMapper.findCourseSubject(courseSubject.getId());
		
		if(!courseMapper.adminUpdateCourseSubject(courseSubject)) {
			FileUtils.removeFile(courseSubject.getThumbnailUrl());
			FileUtils.removeFile(courseSubject.getCertificateUrl());
			return false;
		}
		
		String thumbnailUrl = (String) courseSubjectSub.get("thumbnailUrl");
		//删除之前的图片
		if(thumbnailUrl!=null
				&&thumbnailUrl.length()>0) {
			FileUtils.removeFile(thumbnailUrl);	
		}
		String certificateUrl = (String) courseSubjectSub.get("certificateUrl");
		if(certificateUrl!=null
				&&certificateUrl.length()>0) {
			FileUtils.removeFile(certificateUrl);
		}
		return true;
	}

	@Override
	public Boolean adminUpdateCourse(Course course) {
		return courseMapper.adminUpdateCourse(course);
	}
}
