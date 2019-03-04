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
	public List<Map<String, Object>> listCourseBySubject(Integer customerId,Integer subjectId, Page page) {
		return courseMapper.listCourseBySubject(customerId,subjectId,page);
	}

	@Override
	public Map<String, Object> getCourseById(Integer id,Integer customerId) {
		return courseMapper.getCourseById(id,customerId);
	}

	@Override
	public Boolean addCourse(Course course,MultipartFile file) {
		String videoAbsoluteBasePath = Constant.VIDEO_ABSOLUTE_BASE_PATH + Constant.COURSE_SUBJECT;
		course.setVideo(false);
		if(file != null) {
			course.setIsVideo(true);
			course.setVideoUrl(FileUtils.saveFile(videoAbsoluteBasePath, file));
			course.setThumbnailUrl(FileUtils.fetchImgFromVideo(course.getVideoUrl()));
		}
		if(!courseMapper.saveCourse(course)) {
			FileUtils.removeFile(course.getVideoUrl());
			FileUtils.removeFile(course.getThumbnailUrl());
			return false;
		}
		return true;
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
	public Map<String, Object> findCourseSubject(Integer subjectId) {
		return courseMapper.findCourseSubject(subjectId);
	}

	@Override
	public Integer getCountRecord(Integer customerId, Integer courseSubjectId) {

		return courseMapper.getCountRecord(customerId,courseSubjectId);
	}

/*	@Override
	public void saveCountRecord(Integer customerId, Integer courseId) {
		courseMapper.saveCountRecord(customerId,courseId);
	}*/

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
		Map<String, Object> course=courseMapper.getCourseById(courseId,0);
		if(!courseMapper.adminDeleteCourse(courseId)) {
			return false;
		};
		String videoUrl =(String) course.get("videoUrl");
		if(null != videoUrl && videoUrl.trim().length() >0) {
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
		
		
		//删除之前的图片
		if(imgUrl!=null) {
			String thumbnailUrl = (String) courseSubjectSub.get("thumbnailUrl");
			System.out.println(thumbnailUrl);
			if(thumbnailUrl!=null
					&&thumbnailUrl.length()>0) {
				FileUtils.removeFile(thumbnailUrl);	
			}
		}
		if(ccieUrl!=null) {
			String certificateUrl = (String) courseSubjectSub.get("certificateUrl");
			System.out.println(certificateUrl);
			if(certificateUrl!=null
					&&certificateUrl.length()>0) {
				FileUtils.removeFile(certificateUrl);
			}
		}
		return true;
	}

	@Override
	public Boolean adminUpdateCourse(Course course) {
		return courseMapper.adminUpdateCourse(course);
	}

	@Override
	public Map<String, Object> getCourseSubjectAndPayStatus(Integer subjectId, Integer customerId) {
		return courseMapper.getCourseSubjectAndPayStatus(subjectId, customerId);
	}

	@Override
	public Boolean adminDeleteBatchVideo(String ids) {
		if(ids == null || ids.trim().length() == 0) {
			return true;
		}
		//1 首先获取ids对应的videoURL
		List<CourseSubject> list = courseMapper.listCourseSubjectByIds(ids);
		//2尝试删除ids记录
		Boolean flag = courseMapper.adminDeleteBatchCourseSubject(ids);
		//3尝试删除相应的video
		if(flag) {
			for(CourseSubject courseSubject : list) {
				FileUtils.removeFile(courseSubject.getThumbnailUrl());
				FileUtils.removeFile(courseSubject.getCertificateUrl());
				return flag;
			}
			
		}
		return flag;
	}

	@Override
	public Boolean adminDeleteBatchCourseVideo(String ids) {
		if(ids == null || ids.trim().length() == 0) {
			return true;
		}
		//1 首先获取ids对应的videoURL
		List<Course> list = courseMapper.listCourseVideoByIds(ids);
		//2尝试删除ids记录
		Boolean flag = courseMapper.adminDeleteBatchCourseVideo(ids);
		//3尝试删除相应的video
		if(flag) {
			for(Course  course : list) {
				FileUtils.removeFile(course.getVideoUrl());
				return flag;
			}
			
		}
		return flag;
	}

	@Override
	public List<Course> listCourseBySubjectSub(Course course) {
		return courseMapper.listCourseBySubjectSub(course);
	}
}
