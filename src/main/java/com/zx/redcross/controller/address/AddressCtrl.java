package com.zx.redcross.controller.address;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.FrontEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.Address;
import com.zx.redcross.entity.Customer;
import com.zx.redcross.service.address.AddressServ;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("/address")
public class AddressCtrl {
	@Autowired
	private AddressServ addressServImpl;
	/**
	 * 添加地址
	 */
	@RequestMapping(value="/saveAddress",method=RequestMethod.POST)
	@FrontEnd
	public Map<String,Object> saveAddress(@RequestBody Address address){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//判断添加的是否为默认地址，如果是默认的，则修改之前的默认地址
		if(address.getFlag()==1) {
			//将之前的默认地址变成正常地址
			addressServImpl.updateAddress();
		}
		address.setDetailAddress(addressServImpl.getDetailAddress(address));
		Boolean flag=addressServImpl.saveAddress(address);
		map.put(Constant.STATUS,flag?
				Constant.STATUS_SUCCESS:Constant.STATUS_FAILURE);
		return map;
	}
	
	
	@RequestMapping(value="/updateAddress",method=RequestMethod.POST)
	@FrontEnd
	public Map<String,Object> updateAddress(@RequestBody Address address){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//判断修改的是否为默认地址，如果是默认的，则修改之前的默认地址
		if(address.getFlag()==1) {
			//将之前的默认地址变成正常地址
			addressServImpl.updateAddress();
		}
		address.setDetailAddress(addressServImpl.getDetailAddress(address));
		Boolean flag=addressServImpl.updateAddressAll(address);
		map.put(Constant.STATUS,flag?
				Constant.STATUS_SUCCESS:Constant.STATUS_FAILURE);
		return map;
	}
	
	
	
	@RequestMapping(value="/findAllAddress",method=RequestMethod.GET)
	@FrontEnd
	public Map<String,Object> findAllAddressByCustomerId(Integer customerId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		List<Address> addresss=addressServImpl.findAllAddressByCustomerId(customerId);
		map.put(Constant.DATA, addresss);
		map.put(Constant.STATUS_SUCCESS, Constant.STATUS_SUCCESS);
		return map;
	}
	
	
	@RequestMapping(value="/getAddress",method=RequestMethod.GET)
	@FrontEnd
	public Map<String,Object> getAddressByAddressId(Integer addressId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Address addresss=addressServImpl.getAddressByAddressId(addressId);
		map.put(Constant.DATA, addresss);
		map.put(Constant.STATUS_SUCCESS, Constant.STATUS_SUCCESS);
		return map;
	}
	
	
}
