package com.zx.redcross.test;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestCase {
	@Resource
	BasicDataSource dbcp2;
	@Test
	public void testDs() throws SQLException{
		System.out.println(dbcp2.getConnection());
	}
}
