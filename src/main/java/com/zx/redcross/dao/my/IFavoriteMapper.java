package com.zx.redcross.dao.my;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zx.redcross.entity.Favorite;
import com.zx.redcross.entity.Page;

public interface IFavoriteMapper {
	
	
	@Insert("insert into favorite(customer_id,topic_id) "
			+ "values(#{favorite.customer.id},#{favorite.topic.id})")
	@Options(useGeneratedKeys=true,keyProperty="id")
	Boolean saveFavorite(@Param("favorite") Favorite favorite);
	
	List<Favorite> listFavoriteByCustomerId(
			@Param("customerId") Integer customerId,@Param("page") Page page);
	
	@Delete("delete from favorite where customer_id "
			+ "= #{favorite.customer.id} AND topic_id = #{favorite.topic.id}")
	Boolean removeFavorite(@Param("favorite") Favorite favorite);
	
	@Select("select * FROM favorite where customer_id "
			+ "= #{favorite.customer.id} AND topic_id = #{favorite.topic.id}")
	Favorite getFavoriteByCustomerIdAndTopicId(@Param("favorite") Favorite favorite);
	
}
