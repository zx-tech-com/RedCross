package com.zx.redcross.serviceimpl.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.course.IExamOrderMapper;
import com.zx.redcross.dao.my.CustomerMapper;
import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.OrderNumber;
import com.zx.redcross.entity.Page;
import com.zx.redcross.service.course.IExamOrderServ;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.Utils;

/**
 * 考试报名
 * @author Daryl
 */
@Service
public class ExamOrderServImpl implements IExamOrderServ {

	@Autowired
	private IExamOrderMapper mapper;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public ExamOrder getExamOrderById(Integer id) {
		return mapper.getExamOrderById(id);
	}

	@Override
	public List<ExamOrder> listExamOrderByCustomerId(Integer customerId) {
		return mapper.listExamOrderByCustomerId(customerId);
	}

	@Override
	public List<ExamOrder> listExamOrderByOsDistrictId(Integer districtId, Page page) {
		return mapper.listExamOrderByOsDistrictId(districtId, page);
	}

	@Override
	public List<ExamOrder> listExamOrder( Page page) {
		return mapper.listExamOrder(page);
	}

	@Override
	public Boolean addExamOrder(ExamOrder examOrder) {
		examOrder.setStatus(Constant.WAIT_TO_PAY);
		List<ExamOrder> orderList = mapper.listExamOrderByCustomerId(examOrder.getCustomer().getId());
		if(orderList != null) {
			for(ExamOrder preOrder : orderList) {
				if(preOrder.getCourseSubject().getId() == examOrder.getId()
						&& preOrder.getStatus() == Constant.PAY_COMPLETE)
					BusinessExceptionUtils.throwNewBusinessException("已成功报名，不可重复报名");
				else if(preOrder.getCourseSubject().getId() == examOrder.getCourseSubject().getId()) {
					examOrder.setId(preOrder.getId());
					return updateExamOrderStatus(examOrder);
				}
			}
		}
		examOrder.setOrderNumber(getExamOrderNumber(examOrder.getCustomer().getId()));
		return mapper.addExamOrder(examOrder);
	}

	@Override
	public Boolean updateExamOrderStatus(ExamOrder examOrder) {
		if(examOrder.getStatus() != Constant.PAY_CANCEL 
				&& examOrder.getStatus() != Constant.PAY_COMPLETE
				&& examOrder.getStatus() != Constant.WAIT_TO_PAY
				)
			BusinessExceptionUtils.throwNewBusinessException("状态不合法");
		return mapper.updateExamOrderStatus(examOrder);
	}
	
	private String getExamOrderNumber(Integer customerId) {
		Customer customer = null;
		if(customerId == null || (customer = customerMapper.findCustomerById(customerId)) == null)
			BusinessExceptionUtils.throwNewBusinessException("下单失败，用户id不合法");
		OrderNumber orderNumber = new OrderNumber();
		orderNumber.setBusinessType(Constant.BUSINESS_TYPE_EXAM_ORDER);
		String tel = customer.getTel();
		orderNumber.setTel(tel.substring(tel.length() - Constant.TEL_LENGTH, tel.length()));
		orderNumber.setTimeStamp(Utils.newTimeStamp());
		return orderNumber.getOrderNumber();
	}
	
}
