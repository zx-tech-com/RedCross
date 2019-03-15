package com.zx.redcross.controller.index;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.service.index.KnowledgeFavoriteServ;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("/knowledgeFavorite")
public class FavoriteKnowledge {
	@Autowired
	private KnowledgeFavoriteServ knowledgeFavoriteServImpl;
	/**
	 * 收藏取消收藏
	 */
	@FrontEnd
	@RequestMapping("/saveKnowledgeFavorite")
	public Map<String,Object> saveKnowledgeFavorite(@RequestParam(required=true)Integer customerId,@RequestParam(required=true)Integer knowledgeId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//通过用户id和评论id查询看是否有收藏，有则删除，没有就添加收藏
		Integer count=knowledgeFavoriteServImpl.findFavoriteCountByKnowledgeIdAndCustomerId(knowledgeId, customerId);
		if(count==0) {
			//说明没有收藏，那么添加收藏
			knowledgeFavoriteServImpl.saveKnowledgeFavorite(knowledgeId, customerId);
			map.put(Constant.DATA,Constant.THUMB_SUCCESS);
		}else {
			//删除收藏
			knowledgeFavoriteServImpl.deleteKnowledgeFavorite(knowledgeId, customerId);
			map.put(Constant.DATA,Constant.THUMB_FAILURE);
		}
		map.put(Constant.STATUS,Constant.STATUS_SUCCESS);
		return map;
	}

}
