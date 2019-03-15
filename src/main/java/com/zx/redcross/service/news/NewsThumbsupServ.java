package com.zx.redcross.service.news;
/**
 * 急救常识点赞
 * @author ly
 *
 */
public interface NewsThumbsupServ {
	
	Boolean saveNewsThumbsup(Integer newsId, Integer customerId);

	Boolean deleteNewsThumbsup(Integer newsId, Integer customerId);

	Integer findCountThumbsupByNewsIdAndCustomerId(Integer newsId, Integer customerId);

}
