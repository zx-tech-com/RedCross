package com.zx.redcross.serviceimpl.group;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.group.IGroupOrderMapper;
import com.zx.redcross.dao.group.IGroupPayRecordMapper;
import com.zx.redcross.dao.my.CustomerMapper;
import com.zx.redcross.dao.my.OsDistrictMapper;
import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.GroupOrder;
import com.zx.redcross.entity.GroupPayRecord;
import com.zx.redcross.entity.OrderNumber;
import com.zx.redcross.service.group.IGroupOrderService;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.Utils;


/**
 * 
 * @author Daryl
 */
@Service
public class GroupOrderServImpl implements IGroupOrderService {

	@Autowired
	private IGroupOrderMapper groupOrderMapper;
	@Autowired
	private IGroupPayRecordMapper groupPayRecordMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private OsDistrictMapper districtMapper;

	/**
	 *	 不需要检查是否重复购买.因为可以重复购买
	 */
	@Override
	public Boolean addGroupOrder(GroupOrder order) {
		
		if(order.getCustomer() == null || order.getCustomer().getId() == null)
			BusinessExceptionUtils.throwNewBusinessException("未传入用户id");
		Customer customer = null;
		if((customer = customerMapper.getMyselfMessage(order.getCustomer().getId())) == null)
			BusinessExceptionUtils.throwNewBusinessException("用户id不存在");
		
		if(order.getOsDistrict() == null || order.getOsDistrict().getId() == null)
			BusinessExceptionUtils.throwNewBusinessException("未传入区域id");
		if(districtMapper.findOsdistrictById(order.getOsDistrict().getId()) == null)
			BusinessExceptionUtils.throwNewBusinessException("区域id不存在");
		
		order.setStatus(Constant.WAIT_TO_PAY);
		order.setDetailAddress(getDetailAddress(order));
		order.setOrderNumber(getExamOrderNumberOnline(customer));
		
		Boolean flag = groupOrderMapper.addGroupOrder(order);
		if(!flag)
			BusinessExceptionUtils.throwNewBusinessException("团购报名失败");
		GroupPayRecord record = assembleGroupPayRecord(order);
		return groupPayRecordMapper.addGroupPayRecord(record);
	}

	private String getExamOrderNumberOnline(Customer customer) {
		OrderNumber orderNumber = new OrderNumber();
		orderNumber.setBusinessType(Constant.BUSINESS_TYPE_GROUP_ORDER);
		String tel = customer.getTel();
		orderNumber.setTel(tel.substring(tel.length() - Constant.TEL_LENGTH, tel.length()));
		orderNumber.setTimeStamp(Utils.newTimeStamp());
		return orderNumber.getOrderNumber();
	}

	private GroupPayRecord assembleGroupPayRecord(GroupOrder order) {
		GroupPayRecord record = new GroupPayRecord();
		record.setGroupOrder(order);
		Customer cus = new Customer();
		cus.setId(order.getCustomer().getId());
		record.setCustomer(cus);
		record.setPayMethod(order.getPayMethod());
		//TODO 这里需要设置价格
		record.setStatus(Constant.WAIT_TO_PAY);
		record.setOrderNumber(order.getOrderNumber());
		return record;
	}

	
	private String getDetailAddress(GroupOrder order) {
		Map<String, Object> osDistrict=districtMapper.findOsdistrictById(order.getOsDistrict().getId());
		Integer level=(int) osDistrict.get("level");
		String path=(String) osDistrict.get("name");
		Boolean flage=true;
		while(flage){
			if(level!=1){
				 osDistrict=districtMapper.findOsdistrictById((Integer) osDistrict.get("upid"));
				path=(String) osDistrict.get("name") + path;
				level--;
			}else {
				flage=false;
			}
		}
		String detailAddress = order.getDetailAddress();
		detailAddress = detailAddress==null?"" : detailAddress;
		return path + detailAddress;
	}

	@Override
	public Boolean updatePayMethod(String payMethod, String orderNumber) {
		
		return groupOrderMapper.updatePayMethod(payMethod, orderNumber) 
				&& groupPayRecordMapper.updatePayMethod(payMethod, orderNumber);
	}
	

}
