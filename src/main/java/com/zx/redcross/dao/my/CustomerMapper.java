package com.zx.redcross.dao.my;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Customer;

public interface CustomerMapper {
	//查询所有会员
	public List<Customer> finAllCustomer();
	//通过Id查找会员
	public Customer findCustomerById(Integer id);
	//添加会员，注册会员
	public void saveCustomer(Customer customer);
	//通过手机号码查找
	public Integer findCustomerByTel(String tel);
	public Customer findCustomer(@Param("tel")String tel,@Param("password") String password);
}
