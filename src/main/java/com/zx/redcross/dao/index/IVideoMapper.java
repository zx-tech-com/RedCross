package com.zx.redcross.dao.index;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Video;
import com.zx.redcross.entity.VideoBuyRecord;

public interface IVideoMapper {

	//查询所有付费视频
	List<Map<String,Object>> listVideo(Integer customerId,
			@Param("page") Page page);
	
	//通过视频id查找该视屏的详细信息
	Map<String,Object> getVideo(@Param("customerId")Integer customerId, @Param("videoId")Integer videoId);
	
	//会员点击支付后会生成一条正在记录
	Boolean saveVideoBuyRecord(@Param("videoBuyRecord")VideoBuyRecord videoBuyRecord);
	
	//会员点击支付后会生成一条支付成功记录
	Boolean updateVideoBuyRecord(@Param("videoBuyRecord") VideoBuyRecord videoBuyRecord);
	
	
	
	// =================================付费视频后台管理==============================
	
	//后台查询所有付费视频
	List<Video> adminListVideo();
	//后台添加付费视频
	Boolean adminSaveVideo(Video video);
	//后台删除付费视频
	Boolean adminDeleteVideo(Integer videoId);
	//修改付费视频
	Boolean adminUpdateVideo(Video video);
	//后台查询付费视频订单
	List<VideoBuyRecord> adminListVideoBuyRecord();

	Video getVideoById(Integer videoId);
	
	
}
