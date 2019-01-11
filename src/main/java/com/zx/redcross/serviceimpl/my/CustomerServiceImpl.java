package com.zx.redcross.serviceimpl.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.my.CustomerMapper;
import com.zx.redcross.dao.my.OsDistrictMapper;
import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.OsDistrict;
import com.zx.redcross.service.my.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private OsDistrictMapper	osDistrictMapper;
	@Autowired
	private CustomerMapper	customerMapper;
	
	@Override
	public OsDistrict findOsdistrictById(Integer districtId) {
		// TODO Auto-generated method stub
		return osDistrictMapper.findOsdistrictById(districtId);
	}

	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerMapper.saveCustomer(customer);
	}

	@Override
	public Integer findCustomerByTel(String tel) {
		// TODO Auto-generated method stub
		return customerMapper.findCustomerByTel(tel);
	}

	@Override
	public List<OsDistrict> findByUpid(Integer id) {
		// TODO Auto-generated method stub
		return osDistrictMapper.findByUpid(id);
	}

	@Override
	public Customer findCustomer(String tel, String password) {
		// TODO Auto-generated method stub
		return customerMapper.findCustomer(tel,password);
	}


}
