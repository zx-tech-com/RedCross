package com.zx.redcross.controller.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.SlideShow;
import com.zx.redcross.service.index.SlideShowServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("/slideshow")
public class SlideShowCtrl {
	@Autowired
	private SlideShowServ SlideShowImpl;
	
	/**
	 * 轮播图列表
	 * @return
	 */
	@RequestMapping("/listSlideShow")
	@Open
	public Map<String,Object> listSlideShow() {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<SlideShow> slideShows = SlideShowImpl.listSlideShow();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		map.put(Constant.DATA, slideShows);
		return map;
	}
	
	/**
	 * 添加轮播图
	 * @param slideShow
	 * @return
	 */
	@BackEnd
	@RequestMapping("/addSlideShow")
	@Open
	public Map<String,Object> addSlideShow(SlideShow slideShow,MultipartFile img){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		Boolean flag=SlideShowImpl.addSlideShow(slideShow,img);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;	
	}
	
	/**
	 * 修改一张轮播图
	 * @param slideShow
	 * @param img
	 * @return
	 */
	@BackEnd
	@RequestMapping("/updateSlideShow")
	@Open
	public Map<String,Object> updateSlideShow(SlideShow slideShow,MultipartFile img){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		Boolean flag=SlideShowImpl.updateSlideShow(slideShow,img);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;	
		
	}
	
	@BackEnd
	@RequestMapping("/deleteSlideShow")
	@Open
	public Map<String,Object> updateSlideShow(Integer id){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		Boolean flag=SlideShowImpl.deleteSlideShow(id);
		map.put(Constant.STATUS,flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;	
		
	}
	
}
