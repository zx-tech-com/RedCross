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
import com.zx.redcross.entity.Topic;

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
	@Test
	public void findAllCustmer(){
		List<Customer> customers=customerMapper.finAllCustomer();
		for(Customer customer:customers){
			System.out.println(customer);
		}
	}
	
	@Test
	public void test1(){
		Topic topic=new Topic();
		Customer customer=new Customer();
		customer.setId(123);
		topic.setCustomer(customer);
		
		System.out.println(topic);
		}
}
