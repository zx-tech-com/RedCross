package com.zx.redcross.dao.group;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.GroupOrder;

/**
 * 	团购相关
 * @author Daryl
 *
 */
public interface IGroupOrderMapper {

	Boolean addGroupOrder(@Param("groupOrder") GroupOrder order) ;
	
	/**
	 * 只更新payMethod字段
	 * @param orderNumber
	 * @param payMethod
	 */
	boolean updatePayMethod(@Param("payMethod")String payMethod,
			@Param("orderNumber")String orderNumber);
}
