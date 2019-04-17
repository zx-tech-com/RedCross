package com.zx.redcross.serviceimpl.group;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.group.IGroupPayRecordMapper;
import com.zx.redcross.entity.GroupPayRecord;
import com.zx.redcross.exception.BusinessException;
import com.zx.redcross.pay.PayBizType;
import com.zx.redcross.pay.ali.AlipayBizContent;
import com.zx.redcross.pay.ali.AlipayService;
import com.zx.redcross.service.group.IGroupPayRecordService;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;

@Service
public class GroupPayRecordServImpl implements IGroupPayRecordService {

	@Autowired
	private IGroupPayRecordMapper payMapper;
	
	@Override
	public String getSignedParams(String orderNumber) {

		String signedParams = null;
		AlipayBizContent businessType = assembleAlipayBizContent(orderNumber);
		signedParams = AlipayService.generateAndSignOrderInfo(businessType);
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
			bizContent = new AlipayBizContent(PayBizType.GROUPORDER);
			GroupPayRecord record = payMapper.getGroupPayRecordByOrderNumber(orderNumber);
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
	@Override
	public boolean updateStatusByOrderNumber(String orderNumber) {
		if(payMapper.getGroupPayRecordByOrderNumber(orderNumber) == null)
			BusinessExceptionUtils.throwNewBusinessException("订单号不存在");
		return payMapper.updateStatusByOrderNumber(orderNumber);
	}

}
