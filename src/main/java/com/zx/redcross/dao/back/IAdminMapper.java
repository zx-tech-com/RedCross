package com.zx.redcross.dao.back;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zx.redcross.entity.Admin;

public interface IAdminMapper {
	
	@Select("SELECT * FROM admin WHERE username=#{admin.username} AND passwd = MD5(#{admin.passwd})")
	Admin getAdminByUsernameAndPassword(@Param("admin") Admin admin);
	
	@Insert("INSERT INTO admin(username,passwd) VALUES(#{admin.username},MD5(#{admin.passwd}))")
	@Options(useGeneratedKeys=true,keyProperty="id")
	boolean addAdmin(@Param("admin") Admin admin);
	
	@Update("UPDATE admin SET passwd = MD5(#{admin.passwd}) WHERE username=#{admin.username}")
	boolean updateAdminPassword(@Param("admin") Admin admin);

	@Select("SELECT * FROM admin WHERE username=#{username}")
	Admin getAdminByUsername(String username);
}
