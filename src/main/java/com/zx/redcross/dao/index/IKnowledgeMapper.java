package com.zx.redcross.dao.index;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Knowledge;
import com.zx.redcross.entity.KnowledgeType;
import com.zx.redcross.entity.Page;
import com.zx.redcross.entity.Video;
import com.zx.redcross.entity.VideoBuyRecord;

/**
 * 急救知识
 * @author Daryl
 */
public interface IKnowledgeMapper {
	
	Knowledge getKnowledgeById(@Param("id")Integer id);
	
	List<Knowledge> listKnowledgeByType(@Param("typeId") Integer typeId,@Param("page")Page page);
	
	boolean saveKnowledge(@Param("knowledge")Knowledge knowledge);	
	
	List<KnowledgeType> listKnowledgeType();
	//后台管理添加知识类型
	Boolean adminSaveKnowledgeType(KnowledgeType knowledgeType);
	//后台管理删除知识类型
	Boolean adminDeleteKnowledgeType(@Param("knowledgeTypeId")Integer knowledgeTypeId);
	////后台管理修改知识类型
	Boolean adminUpdateKnowledgeType(KnowledgeType knowledgeType);
	//后台删除知识
	Boolean adminDeleteKnowledge(Integer knowledgeId);
	//后台修改知识
	Boolean adminUpdateKnowledge(@Param("knowledge")Knowledge knowledge);
	//查询所有付费视频
	List<Video> listVideo(Integer customerId);
	//通过视频id查找该视屏的详细信息
	Video getVideo(@Param("customerId")Integer customerId, @Param("videoId")Integer videoId);
	//会员点击支付后会生成一条正在记录
	Boolean saveVideoBuyRecord(@Param("videoBuyRecord")VideoBuyRecord videoBuyRecord);
	//会员点击支付后会生成一条支付成功记录
	Boolean uptateVideoBuyRecord(Integer videoBuyRecordId);


}
