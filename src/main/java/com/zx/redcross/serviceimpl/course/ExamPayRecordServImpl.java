package com.zx.redcross.serviceimpl.course;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.course.IExamPayRecordMapper;
import com.zx.redcross.entity.ExamPayRecord;
import com.zx.redcross.exception.BusinessException;
import com.zx.redcross.pay.PayBizType;
import com.zx.redcross.pay.ali.AlipayBizContent;
import com.zx.redcross.pay.ali.AlipayService;
import com.zx.redcross.service.course.IExamPayRecordServ;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;

@Service
public class ExamPayRecordServImpl implements IExamPayRecordServ {

	@Autowired
	private IExamPayRecordMapper recordMapper;
	
	@Override
	public String getSignedParams(String examOrder) {

		String signedParams = null;
		AlipayBizContent businessType = assembleAlipayBizContent(examOrder);
		signedParams = AlipayService.generateAndSignOrderInfo(businessType);

		System.err.println(signedParams);
		
		return signedParams;
	}
	/**
	 * 组装考试报名所需的参数
	 * @param record
	 * @return
	 */
	private AlipayBizContent assembleAlipayBizContent(String orderNumber) {
		AlipayBizContent bizContent = null;
		try {
			bizContent = new AlipayBizContent(PayBizType.EXAMORDER);
			ExamPayRecord record = recordMapper.getExamPayRecordByOrderNumber(orderNumber);
			if(record == null)
				BusinessExceptionUtils.throwNewBusinessException("订单号不存在");
			if(record.getStatus() == Constant.PAY_COMPLETE)
				BusinessExceptionUtils.throwNewBusinessException("订单号 " + orderNumber + " 已经支付成功,无需重复支付");
		
			bizContent.setOutTradeNo(orderNumber);
			bizContent.setTotalAmount(String.valueOf(record.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP)));
			
		}catch(BusinessException e) {
			BusinessExceptionUtils.throwNewBusinessException(e.getMessage());
		}catch(Exception e) {
			BusinessExceptionUtils.throwNewBusinessException("组装业务支付参数异常");
		}
		return bizContent;
	}

}
