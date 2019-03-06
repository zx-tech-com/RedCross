package com.zx.redcross.serviceimpl.course;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.course.ICourseMapper;
import com.zx.redcross.dao.course.IExamOrderMapper;
import com.zx.redcross.dao.course.IExamPayRecordMapper;
import com.zx.redcross.dao.my.CustomerMapper;
import com.zx.redcross.dao.my.OsDistrictMapper;
import com.zx.redcross.entity.CourseSubject;
import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.ExamOrder;
import com.zx.redcross.entity.ExamPayRecord;
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
	
	@Autowired
	private IExamPayRecordMapper recordMapper;
	
	@Autowired
	private ICourseMapper courseMapper;
	
	@Autowired
	private OsDistrictMapper	osDistrictMapper;
	
	@Override
	public Map<String, Object> getExamOrderById(Integer id) {
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
	public List<Map<String, Object>> listExamOrder(ExamOrder examOrder) {
		return mapper.listExamOrder(examOrder);
	}

	@Override
	public Boolean addExamOrder(ExamOrder examOrder) {
		
		if(examOrder.getCustomer()==null)//线下报名
			return addExamOrderOffLine(examOrder);
		else 
			return addExamOrderOnLine(examOrder);
		
	}
	
	/**
	 * 添加考试订单的同时也添加支付记录.二者同时插入，状态同时更新
	 */
	private Boolean addExamOrderOnLine(ExamOrder examOrder) {
		List<ExamOrder> orderList=new ArrayList<ExamOrder>();
		examOrder.setStatus(Constant.WAIT_TO_PAY);
		orderList = mapper.listExamOrderByCustomerId(examOrder.getCustomer().getId());
		
		if(orderList != null) {//检测是否重复提交
			for(ExamOrder preOrder : orderList) {
				if(preOrder.getCourseSubject().getId() == examOrder.getId()
						&& preOrder.getStatus() == Constant.PAY_COMPLETE)
					BusinessExceptionUtils.throwNewBusinessException("已成功报名，无需重复报名");
				else if(preOrder.getCourseSubject().getId() == examOrder.getCourseSubject().getId()) {
					examOrder.setId(preOrder.getId());
					examOrder.setDetailAddress(getDetailAddress(examOrder));//组装完整的地址路径
					examOrder.setOrderNumber(preOrder.getOrderNumber());
					return updateExamOrderStatus(examOrder);
				}
			}
		}
		
		examOrder.setDetailAddress(getDetailAddress(examOrder));//组装完整的地址路径
		examOrder.setOrderNumber(getExamOrderNumberOnline(examOrder.getCustomer().getId()));
		boolean flag = mapper.addExamOrder(examOrder);
		if(!flag)
			BusinessExceptionUtils.throwNewBusinessException("培训报名失败");
		
		ExamPayRecord record = getExamPayRecord(examOrder);//必须放在 addExamOrder之后执行,因为需要生成的id
		return recordMapper.saveExamPayRecord(record);
	}
	
	/**
	 * 线下报名无支付记录,不添加支付记录
	 * @param examOrder
	 * @return
	 */
	private Boolean addExamOrderOffLine(ExamOrder examOrder) {
		examOrder.setDetailAddress(getDetailAddress(examOrder));//组装完整的地址路径
		examOrder.setOrderNumber(getExamOrderNumberOffline(examOrder.getTel()));
		return mapper.addExamOrder(examOrder);
	}
	
	
	
	
	
	/**
	 * 根据examOrder生成ExamPayRecord
	 * @param examOrder
	 * @return
	 */
	private ExamPayRecord getExamPayRecord(ExamOrder examOrder) {
		ExamPayRecord record = new ExamPayRecord();
		record.setExamOrder(examOrder);
		Customer cus = new Customer();
		cus.setId(examOrder.getCustomer().getId());
		record.setCustomer(cus);
		record.setPayMethod(examOrder.getPayMethod());
		Map<String, Object> courseSubject = courseMapper.findCourseSubject(examOrder.getCourseSubject().getId());
		record.setAmount((BigDecimal)courseSubject.get("price"));
		record.setStatus(Constant.WAIT_TO_PAY);
		record.setOrderNumber(examOrder.getOrderNumber());
		return record;
	}

	@Override
	public Boolean updateExamOrderStatus(ExamOrder examOrder) {
		if(examOrder.getStatus() != Constant.PAY_CANCEL 
				&& examOrder.getStatus() != Constant.PAY_COMPLETE
				&& examOrder.getStatus() != Constant.WAIT_TO_PAY
				)
			BusinessExceptionUtils.throwNewBusinessException("考试报名状态不合法");
		//更新报名订单信息
		boolean flag = mapper.updateExamOrder(examOrder);
		if(!flag)
			BusinessExceptionUtils.throwNewBusinessException("考试报名状态更新失败");
		
		Map<String,Object> map = mapper.getExamOrderById(examOrder.getId());
		examOrder.setOrderNumber((String)map.get("orderNumber"));
		//更新支付记录的状态
		ExamPayRecord record = recordMapper.getExamPayRecordByOrderNumber(examOrder.getOrderNumber());
		record.setStatus(examOrder.getStatus());
		return recordMapper.updateExamPayRecord(record);
	}
	
	private String getExamOrderNumberOnline(Integer customerId) {
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
	
	
	private String getExamOrderNumberOffline(String tel) {
		OrderNumber orderNumber = new OrderNumber();
		orderNumber.setBusinessType(Constant.BUSINESS_TYPE_EXAM_ORDER);
		orderNumber.setTel(tel.substring(tel.length() - Constant.TEL_LENGTH, tel.length()));
		orderNumber.setTimeStamp(Utils.newTimeStamp());
		return orderNumber.getOrderNumber();
	}
	
	
	public String getDetailAddress(ExamOrder order) {
		Map<String, Object> osDistrict=osDistrictMapper.findOsdistrictById(order.getOsDistrict().getId());
		Integer level=(int) osDistrict.get("level");
		String path=(String) osDistrict.get("name");
		Boolean flage=true;
		while(flage){
			if(level!=1){
				 osDistrict=osDistrictMapper.findOsdistrictById((Integer) osDistrict.get("upid"));
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
	public Boolean updateExamOrderPayMethod(String payMethod,String orderNumber) {
		return mapper.updateExamOrderPayMethod(payMethod, orderNumber)
				&& recordMapper.updatePayMethod(payMethod, orderNumber);
	}

	@Override
	public CourseSubject getCourseSubjectByExamOrderOrderNumber(String orderNumber) {
		return mapper.getCourseSubjectByExamOrderOrderNumber(orderNumber);
	}
	
}
