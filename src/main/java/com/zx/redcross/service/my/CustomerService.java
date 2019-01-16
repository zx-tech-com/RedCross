package com.zx.redcross.service.my;

import java.util.List;

import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.OsDistrict;

public interface CustomerService {
	//通过最底层的地址的详细信息
	OsDistrict findOsdistrictById(Integer districtId);
	void saveCustomer(Customer customer);
	//通过手机号码查询是否已近注册
	Integer findCustomerByTel(String tel);
	//通过区域id查找下一级区域
	List<OsDistrict> findByUpid(Integer id);
	//通过手机号码和密码查找用户
	Customer findCustomer(String tel, String password);
	//删除自己的发帖
	void deleteMyTopic(Integer topicId, Integer customerId);
	//删除自己的评论
	void deleteMyTopicComent(Integer topicId, Integer customerId);
	//查询是否是自己的评论
	Integer findMyTopicComent(Integer topicComentId, Integer customerId);
}