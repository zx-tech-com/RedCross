package com.zx.redcross.service.group;

import com.zx.redcross.entity.GroupOrder;

public interface IGroupOrderService {

	Boolean addGroupOrder(GroupOrder order) ;
	
	Boolean updatePayMethod(String payMethod,String orderNumber);
}
