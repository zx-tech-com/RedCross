package com.zx.redcross.controller.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Video;
import com.zx.redcross.entity.VideoBuyRecord;
import com.zx.redcross.entity.VideoRecord;
import com.zx.redcross.service.index.IVideoServ;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;
import com.zx.redcross.tool.StringUtils;

/**
 * 付费视频模块
 * 
 * @author 罗勇,Daryl
 */
@RestController
@RequestMapping("/video")
public class VideoCtrl {

	@Autowired
	private IVideoServ videoServImpl;
	

	/**
	 * 视频列表，查询所有视频，并判断用户是否购买了
	 * 
	 * @param customerId
	 * @return
	 */
	@FrontEnd
	@RequestMapping("/listVideo")
	@Open
	public Map<String, Object> listVideo(Integer customerId, Page page) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<Map<String, Object>> videos = videoServImpl.listVideo(customerId, page);
		map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		if (null != videos) {
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, videos);
			map.put(Constant.PAGE_SIZE, page.getPageSize());
		}
		return map;
	}

	@FrontEnd
	@RequestMapping("/getVideo")
	@Open
	public Map<String, Object> getVideo(Integer customerId, @RequestParam Integer videoId) {
		if (videoId == null)
			BusinessExceptionUtils.throwNewBusinessException("videoId必填");
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Map<String, Object> video = videoServImpl.getVideo(customerId, videoId);
		if (null != video) {
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
			map.put(Constant.DATA, video);
		} else {
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		}
		return map;
	}

	@FrontEnd
	@RequestMapping("/saveVideoBuyRecord")
	public Map<String, Object> saveVideoBuyRecord(@RequestBody VideoBuyRecord videoBuyRecord) {
		// 保存购买记录（实际保存的是正在购买状态为1）
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Map<String, Object> dataMap = MapUtils.getHashMapInstance();

		map.put(Constant.STATUS, Constant.STATUS_FAILURE);

		Boolean flag = videoServImpl.saveVideoBuyRecord(videoBuyRecord);
		if (flag) {
			dataMap.put(Constant.ID, videoBuyRecord.getId());
			map.put(Constant.DATA, dataMap);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}

	@FrontEnd
	@RequestMapping("/updateVideoBuyRecord")
	public Map<String, Object> updateVideoBuyRecord(@RequestBody VideoBuyRecord videoBuyRecord) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Boolean flag = videoServImpl.updateVideoBuyRecord(videoBuyRecord);
		map.put(Constant.STATUS, flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	
	// =================================付费视频后台管理==============================
	/**
	 * 付费视频列表
	 */
	@BackEnd
	@RequestMapping("/adminListVideo")
	public Map<String, Object> adminListVideo(Page page) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<Video> videos = videoServImpl.adminListVideo(page);
		if (null != videos) {
			map.put(Constant.DATA, videos);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		} else {
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		}
		return map;
	}

	/**
	 * 添加付费视频
	 */
	@BackEnd
	@RequestMapping("/adminSaveVideo")
	public Map<String, Object> adminSaveVideo(Video video, MultipartFile file,MultipartFile imgUrl) {
		// 未处理视频上传过程
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Boolean flag = videoServImpl.adminSaveVideo(video, file,imgUrl);
		map.put(Constant.STATUS, flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}

	/**
	 * 删除付费视频
	 */
	@BackEnd
	@RequestMapping("/adminDeleteVideo")
	public Map<String, Object> adminDeleteVideo(Integer videoId) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Boolean flag = videoServImpl.adminDeleteVideo(videoId);
		map.put(Constant.STATUS, flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	
	/**
	 * 批量删除付费视频
	 */
	@BackEnd
	@RequestMapping("/adminDeleteBatchVideo")
	public Map<String, Object> adminDeleteBatchVideo(Integer[] ids) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		if(ids == null || ids.length == 0)
			BusinessExceptionUtils.throwNewBusinessException("未传递参数！");
		
		Boolean flag = videoServImpl.adminDeleteBatchVideo(StringUtils.convertArrayToString(ids));
		map.put(Constant.STATUS, flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	

	/**
	 * 修改付费视频
	 */
	@BackEnd
	@RequestMapping("/adminUpdateVideo")
	public Map<String, Object> adminUpdateVideo(Video video,MultipartFile file,MultipartFile imgUrl) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Boolean flag = videoServImpl.adminUpdateVideo(video,file,imgUrl);
		map.put(Constant.STATUS, flag ? Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}

	/**
	 * 付费视频订单列表
	 * 
	 * @return
	 */
	@BackEnd
	@RequestMapping(value="/adminListVideoBuyRecord",method=RequestMethod.POST)
	public Map<String, Object> adminListVideoBuyRecord(@RequestBody VideoBuyRecord record) {
		System.err.println(JSON.toJSONString(record));
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<VideoBuyRecord> videoBuyRecords = videoServImpl.adminListVideoBuyRecord(record);
		map.put(Constant.DATA, videoBuyRecords);
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}

}
