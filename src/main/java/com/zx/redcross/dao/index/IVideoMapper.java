package com.zx.redcross.dao.index;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Video;
import com.zx.redcross.entity.VideoBuyRecord;

public interface IVideoMapper {

	//查询所有付费视频
	List<Map<String,Object>> listVideo(@Param("customerId")Integer customerId,
			@Param("page") Page page);
	
	//通过视频id查找该视屏的详细信息
	Map<String,Object> getVideo(@Param("customerId")Integer customerId, @Param("videoId")Integer videoId);
	
	//会员点击支付后会生成一条正在记录
	Boolean saveVideoBuyRecord(@Param("videoBuyRecord")VideoBuyRecord videoBuyRecord);
	
	//会员点击支付后会生成一条支付成功记录
	Boolean updateVideoBuyRecord(@Param("videoBuyRecord") VideoBuyRecord videoBuyRecord);
	
	/**
	 * 更新付费视频购买方式
	 * @return
	 */
	Boolean updateVideoBuyRecordPayMethod(@Param("payMethod")String payMethod,
			@Param("orderNumber")String orderNumber);
	
	VideoBuyRecord getVideoBuyRecordByCustomerAndVideoId(@Param("customerId")Integer customerId, @Param("videoId")Integer videoId);
	
	
	List<Video> listVideoByIds(@Param("ids")String ids);
	
	// =================================付费视频后台管理==============================
	
	//后台查询所有付费视频
	List<Video> adminListVideo(@Param("page") Page page);
	//后台添加付费视频
	Boolean adminSaveVideo(Video video);
	//后台删除付费视频
	Boolean adminDeleteVideo(Integer videoId);
	
	//后台批量删除付费视频
	Boolean adminDeleteBatchVideo(@Param("ids")String ids);
	
	//修改付费视频
	Boolean adminUpdateVideo(Video video);
	//后台查询付费视频订单
	List<VideoBuyRecord> adminListVideoBuyRecord(@Param("record")VideoBuyRecord record);

	Video getVideoById(Integer videoId);

	Integer findVideoCount();

	Video getVideoByVideoBuyOrderNumber(@Param("orderNumber") String orderNumber);
	
	
}
