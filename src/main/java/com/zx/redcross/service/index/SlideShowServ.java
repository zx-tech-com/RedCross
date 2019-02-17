package com.zx.redcross.service.index;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.entity.SlideShow;

public interface SlideShowServ {

	List<SlideShow> listSlideShow();

	Boolean addSlideShow(SlideShow slideShow, MultipartFile img);

	Boolean updateSlideShow(SlideShow slideShow, MultipartFile img);

	Boolean deleteSlideShow(Integer id);

}
