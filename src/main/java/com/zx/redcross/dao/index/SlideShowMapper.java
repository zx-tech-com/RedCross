package com.zx.redcross.dao.index;

import java.util.List;

import com.zx.redcross.entity.SlideShow;

public interface SlideShowMapper {
	//轮播图列表
	List<SlideShow> listSlideShow();
	//添加一张图
	Boolean addSlideShow(SlideShow slideShow);
	//修改一张轮播图
	Boolean updateSlideShow(SlideShow slideShow);
	//删除一张轮播图
	Boolean deleteSlideShow(Integer id);
	//通过ID查找该轮播图的详细信息
	SlideShow getSlideShowById(Integer id);

}
