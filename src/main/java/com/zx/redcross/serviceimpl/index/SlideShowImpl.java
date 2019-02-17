package com.zx.redcross.serviceimpl.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.dao.index.SlideShowMapper;
import com.zx.redcross.entity.SlideShow;
import com.zx.redcross.service.index.SlideShowServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.FileUtils;
@Service
public class SlideShowImpl implements SlideShowServ{
	@Autowired
	private SlideShowMapper mapper;
	
	@Override
	public List<SlideShow> listSlideShow() {
		return mapper.listSlideShow();
	}

	@Override
	public Boolean addSlideShow(SlideShow slideShow,MultipartFile img) {
		String imgAbsoluteBasePath = Constant.IMG_ABSOLUTE_BASE_PATH + Constant.Slide_Show;
		if(img!=null) {
			slideShow.setImgUrl(FileUtils.saveFile(imgAbsoluteBasePath, img));
		}
		if(!mapper.addSlideShow(slideShow)) {
			FileUtils.removeFile(slideShow.getImgUrl());
			return false;
		}
		return true;	
	}

	@Override
	public Boolean updateSlideShow(SlideShow slideShow, MultipartFile img) {
		String imgAbsoluteBasePath = Constant.IMG_ABSOLUTE_BASE_PATH + Constant.Slide_Show;
		SlideShow slideShowSub=mapper.getSlideShowById(slideShow.getId());
		if(img!=null) {
			slideShow.setImgUrl(FileUtils.saveFile(imgAbsoluteBasePath, img));
		}
		if(!mapper.updateSlideShow(slideShow)) {
			FileUtils.removeFile(slideShow.getImgUrl());
			return false;
		}
		if(img!=null) {
			FileUtils.removeFile(slideShowSub.getImgUrl());
		}
		return true;
	}

	@Override
	public Boolean deleteSlideShow(Integer id) {
		//通过ID获取该轮播图的详细信息
		SlideShow slideShow=mapper.getSlideShowById(id);
		if(!mapper.deleteSlideShow(id)) {
			return false;	
		}
		FileUtils.removeFile(slideShow.getImgUrl());	
		return true;
	}

}
