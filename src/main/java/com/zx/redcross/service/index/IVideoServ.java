package com.zx.redcross.service.index;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Video;
import com.zx.redcross.entity.VideoBuyRecord;

public interface IVideoServ {
	
	//查询所有视频
	List<Map<String, Object>> listVideo(Integer customerId,Page page);
	
	//通过视频id查找一个视频的详细信息
	Map<String, Object> getVideo(Integer customerId, Integer videoId);
	
	//会员点击支付后会生成一条正在记录
	Boolean saveVideoBuyRecord(VideoBuyRecord videoBuyRecord);
	
	//会员点击支付后会生成一条支付成功记录
	Boolean updateVideoBuyRecordStatus(VideoBuyRecord videoBuyRecord);
	
	
	
	
	
	
	
	//===========================后台管理接口========================
	//后台查询所有付费视频
	List<Video> adminListVideo(Page page);
	//添加付费视频
	Boolean adminSaveVideo(Video video, MultipartFile file/*, MultipartFile imgUrl*/);
	//删除付费视频
	Boolean adminDeleteVideo(Integer videoId);
	
	//批量删除付费视频
	Boolean adminDeleteBatchVideo(String ids);
	
	//修改付费视频
	Boolean adminUpdateVideo(Video video, MultipartFile file/*, MultipartFile imgUrl*/);
    //后台查询付费视频订单
	List<VideoBuyRecord> adminListVideoBuyRecord(VideoBuyRecord record);

	Integer findVideoCount();
	
	
}
