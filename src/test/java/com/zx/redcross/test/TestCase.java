package com.zx.redcross.test;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zx.redcross.dao.my.CustomerMapper;
import com.zx.redcross.dao.my.OsDistrictMapper;
import com.zx.redcross.entity.Customer;
import com.zx.redcross.entity.OsDistrict;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestCase {
	@Resource
	BasicDataSource dbcp2;
	@Resource
	CustomerMapper customerMapper;
	@Resource
	OsDistrictMapper osDistrictMapper;
	@Test
	public void testDs() throws SQLException{
		
		System.out.println(dbcp2.getConnection());
	}
	//鏌ヨ鎵�鏈変細鍛�
	@Test
	public void findAllCustmer(){
		List<Customer> customers=customerMapper.finAllCustomer();
		for(Customer customer:customers){
			System.out.println(customer);
		}
	}
	//閫氳繃id鏌ユ壘浼氬憳
	@Test
	public void findCustomerByid(){
		Customer customer=customerMapper.findCustomerById(1);
		System.out.println(customer.getDistrictId());
		//鏍规嵁鑾峰彇鐨勫尯鍩焛d鏉ヨ幏鍙栧叏閮ㄧ殑鍦板潃鍚嶅瓧
		Boolean flage=true;
		//鏍规嵁澶栭敭鍏宠仈鑾峰彇涓�涓湴鍧�
		OsDistrict osDistrict=osDistrictMapper.findOsdistrictById(customer.getDistrictId());
		Integer level=(int) osDistrict.getLevel();
		String path=osDistrict.getName();
		System.out.println(osDistrict);
		while(flage){
			if(level!=1){
				 osDistrict=osDistrictMapper.findOsdistrictById(osDistrict.getUpid());
				path=osDistrict.getName()+path;
				level--;
			}else {
				flage=false;
			}
		}
		System.out.println(path);
	}
	/**
	 * 娉ㄥ唽浼氬憳
	 */
	@Test
	public void  register(){
		Customer customer=new Customer();
		customer.setTel("18297859336");
		customer.setPassword("123456");
		customer.setDistrictId(3560);
		OsDistrict osDistrict=osDistrictMapper.findOsdistrictById(customer.getDistrictId());
		Integer level=(int) osDistrict.getLevel();
		String path=osDistrict.getName();
		System.out.println(osDistrict);
		Boolean flage=true;
		while(flage){
			if(level!=1){
				 osDistrict=osDistrictMapper.findOsdistrictById(osDistrict.getUpid());
				path=osDistrict.getName()+path;
				level--;
			}else {
				flage=false;
			}
		}
		customer.setDetailAddress(path);
		customerMapper.saveCustomer(customer);
	}
}
