package com.zx.redcross.controller.my;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.Customer;
import com.zx.redcross.service.my.CustomerService;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.JWTUtils;
import com.zx.redcross.tool.MapUtils;

@RestController("")
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService	customerService;
	/**
	 * 注册用户
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@Open
	public Map<String,Object> registerCustomer(@RequestBody Customer customer){
		/**
		 * 获取注册用户的手机号码，判断是否已经注册过了
		 */
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Integer count=customerService.findCustomerByTel(customer.getTel());
		if(count!=0){
			BusinessExceptionUtils.throwNewBusinessException("该手机号已注册");
		}else{
			customer.setDetailAddress(customerService.getDetailAddress(customer));
			customerService.saveCustomer(customer);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}
	
	/**
	 * 已知选择的省份是安徽省，获取下一级的市/县
	 */
	@RequestMapping("/district")
	@Open
	public Map<String,Object> getDistrict(@RequestParam Integer id){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		List<Map<String, Object>> dis=customerService.findByUpid(id);
		map.put(Constant.DATA, dis);
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}
	/**
	 * 账号密码登录 
	 */
	@FrontEnd
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Open
	public Map<String,Object> login(@RequestParam String tel,@RequestParam String password){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		//通过手机号码和密码查询是否存在用户
		Customer customer=customerService.findCustomer(tel,password);
		if(customer==null){
			BusinessExceptionUtils.throwNewBusinessException("账号密码不匹配");
		}else{
			map.put(Constant.DATA, customer);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
			map.put(Constant.TOKEN, JWTUtils.creatToken(JWTUtils.prepareTokenParams(customer.getId())));
		}
		return map;	
	}
	/**
	 * 查询改手机号是否已注册
	 * @param tel
	 * @return
	 */
	@RequestMapping("/register/options")
	@Open
	public Map<String,Object> checkIfRegisterBefore(@RequestParam(required=true)String tel){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		Integer count=customerService.findCustomerByTel(tel);
		if(count > 0)
			BusinessExceptionUtils.throwNewBusinessException("该手机号已注册");
		return map;
	}
	
	@RequestMapping("/loginWithAuthCode")
	@Open
	public Map<String,Object> loginWithAuthCode(String tel,String authCode){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		return map;	
	}
	
	@RequestMapping(value="/updateCustomerInfo", method=RequestMethod.POST)
	public Map<String,Object> updatePersonalInfoWithNoAvatarUrl(@RequestBody Customer customer){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		if(customerService.updatePersonalInfoWithNoAvatarUrl(customer))
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		else
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}
	
	@RequestMapping("/updateAvatar")
	public Map<String,Object> updatePersonalAvatarUrl(
			@RequestParam Integer customerId,@RequestParam MultipartFile avatar){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		if(customerService.updatePersonalAvatarUrl(customerId, avatar))
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		else
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
			
		return map;
	}
	
	
	@RequestMapping("/getMyselfMessage")
	public Map<String,Object> getMyselfMessage(Integer customerId){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		Customer customer=customerService.getMyselfMessage(customerId);
		if(customer!=null){
			map.put(Constant.DATA, customer);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}else {
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}	
		return map;
	}
	
	
	/**
	 * 查询是否为自己的发帖
	 */
	@RequestMapping("/findMyTopic")
	public Map<String,Object> findMyTopic(Integer topicId,Integer customerId){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		Integer count=customerService.findMyTopic(topicId,customerId);
		if(count==1) {
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}else {
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		}
		return map;		
	}
	
	
	
	
	/**
	 * 删除自己的发帖
	 */
	@RequestMapping("/deleteMyTopic")
	public Map<String,Object> deleteMyTopic(Integer topicId,Integer customerId){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		Boolean flag=customerService.deleteMyTopic(topicId,customerId);
		map.put(Constant.STATUS,flag?
				Constant.STATUS_SUCCESS:Constant.STATUS_FAILURE);
		return map;	
	}
	
	/**
	 * 查询是否为自己的评论
	 */
	@RequestMapping("/findMyTopicComent")
	public Map<String,Object> findMyTopicComent(Integer topicComentId,Integer customerId){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		Integer count=customerService.findMyTopicComent(topicComentId,customerId);
		if(count==1) {
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}else {
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
		}
		return map;		
	}
	
	/**
	 * 删除自己的评论
	 */
	@RequestMapping("/deleteMyTopicComent")
	public Map<String,Object> deleteMyTopicComent(Integer topicComentId,Integer customerId){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		Boolean flag=customerService.deleteMyTopicComent(topicComentId,customerId);
		map.put(Constant.STATUS,flag?
				Constant.STATUS_SUCCESS:Constant.STATUS_FAILURE);
		return map;		
	}
}
