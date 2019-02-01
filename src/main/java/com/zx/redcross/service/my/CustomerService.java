package com.zx.redcross.service.my;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.entity.Customer;

public interface CustomerService {
	//通过最底层的地址的详细信息
	Map<String, Object> findOsdistrictById(Integer districtId);
	void saveCustomer(Customer customer);
	//通过手机号码查询是否已近注册
	Integer findCustomerByTel(String tel);
	//通过区域id查找下一级区域
	List<Map<String, Object>> findByUpid(Integer id);
	//通过手机号码和密码查找用户
	Customer findCustomer(String tel, String password);
	//删除自己的发帖
	Boolean deleteMyTopic(Integer topicId, Integer customerId);
	//删除自己的评论
	Boolean deleteMyTopicComent(Integer topicComentId, Integer customerId);
	//查询是否是自己的评论
	Integer findMyTopicComent(Integer topicComentId, Integer customerId);
	
	Boolean updatePersonalInfoWithNoAvatarUrl(Customer customer);
	
	Boolean updatePersonalAvatarUrl(Integer customerId,MultipartFile avatar);
	
	/**
	 * 根据osDistrict和detailAddress生成完整的地址
	 * @param customer
	 * @return
	 */
	String getDetailAddress(Customer customer);
	
	
	Customer getMyselfMessage(Integer customerId);
	//查询是否是自己的发帖
	Integer findMyTopic(Integer topicId, Integer customerId);
}
