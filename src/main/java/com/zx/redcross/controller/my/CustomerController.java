package com.zx.redcross.controller.my;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.OsDistrict;
import com.zx.redcross.service.my.CustomerService;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController("")
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService	customerService;
	/**
	 * 注册用户
	 */
	@RequestMapping("/register")
	public Map<String,Object> registerCustomer(Customer customer,String adress){
		customer.setTel("13625667522");
		customer.setPassword("123456");
		customer.setDistrictId(3562);		
		/**
		 * 获取注册用户的手机号码，判断是否已经注册过了
		 */
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Integer count=customerService.findCustomerByTel(customer.getTel());
		if(count!=0){
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
			return map;		
		}else{
			OsDistrict osDistrict=customerService.findOsdistrictById(customer.getDistrictId());
			Integer level=(int) osDistrict.getLevel();
			String path=osDistrict.getName();
			System.out.println(osDistrict);
			Boolean flage=true;
			while(flage){
				if(level!=1){
					 osDistrict=customerService.findOsdistrictById(osDistrict.getUpid());
					path=osDistrict.getName()+path;
					level--;
				}else {
					flage=false;
				}
			}
			customer.setDetailAddress(path);
			customerService.saveCustomer(customer);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;
	}
	/**
	 * 已知选择的省份是安徽省，获取下一级的市/县
	 */
	@RequestMapping("/district")
	public Map<String,Object> getDistrict(Integer id){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		List<OsDistrict> dis=customerService.findByUpid(id);
		map.put(Constant.DATA, dis);
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}
	/**
	 * 登录
	 */
	@RequestMapping("/login")
	public Map<String,Object> login(String tel,String password){
		Map<String,Object> map=MapUtils.getHashMapInstance();
		//通过手机号码和密码查询是否存在用户
		Customer customer=customerService.findCustomer(tel,password);
		if(customer==null){
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);	
		}else{
			System.out.println(customer);
			map.put(Constant.DATA, customer);
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		}
		return map;	
	}
}
