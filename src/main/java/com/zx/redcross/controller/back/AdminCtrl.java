package com.zx.redcross.controller.back;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.redcross.annotation.BackEnd;
import com.zx.redcross.annotation.Open;
import com.zx.redcross.entity.Admin;
import com.zx.redcross.service.back.IAdminServ;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.MapUtils;

@RestController
@RequestMapping("back")
public class AdminCtrl {

	@Autowired
	private IAdminServ adminServImpl;
	
	@Open
	@RequestMapping("login")
	public Map<String,Object> login(Admin admin,HttpSession session){
		if(admin.getUsername() == null || admin.getUsername().trim().length() == 0)
			BusinessExceptionUtils.throwNewBusinessException("用户名必填");
		Map<String, Object> map = MapUtils.getHashMapInstance();
		if(adminServImpl.login(admin)) {
			map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
			session.setAttribute(Constant.ADMIN, admin.getUsername());
		}else {
			map.put(Constant.STATUS, Constant.STATUS_FAILURE);
			BusinessExceptionUtils.throwNewBusinessException("账号密码不匹配");
		}
		return map;
	}
	
	@BackEnd
	@RequestMapping("logout")
	public Map<String,Object> logout(HttpSession session){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		session.invalidate();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		return map;
	}
	
	@BackEnd
	@RequestMapping("modifypassword")
	public Map<String,Object> modifyPassword(Admin admin){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, adminServImpl.updateAdminPassword(admin)?
				Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	@BackEnd
	@RequestMapping("newAdmin")
	public Map<String,Object> addAdmin(Admin admin){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, adminServImpl.addAdmin(admin)?
				Constant.STATUS_SUCCESS : Constant.STATUS_FAILURE);
		return map;
	}
	
	@BackEnd
	@RequestMapping("checkUsernameAvaliable")
	public Map<String,Object> addAdmin(@RequestParam(required=true) String username){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		map.put(Constant.DATA, adminServImpl.checkIfUsernameExist(username));
		return map;
	}
	
	@BackEnd
	@RequestMapping("deleteAdmin")
	public Map<String,Object> deleteAdmin(@RequestParam(required=true) Integer id){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		map.put(Constant.DATA, adminServImpl.deleteAdmin(id));
		return map;
	}
	
	@BackEnd
	@RequestMapping("findAllAdmin")
	public Map<String,Object> findAllAdmin(){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<Admin> admins=adminServImpl.findAllAdmin();
		map.put(Constant.STATUS, Constant.STATUS_SUCCESS);
		map.put(Constant.DATA, admins);
		return map;
	}
	
}
