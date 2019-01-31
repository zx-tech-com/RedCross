package com.zx.redcross.serviceimpl.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.back.IAdminMapper;
import com.zx.redcross.entity.Admin;
import com.zx.redcross.service.back.IAdminServ;

@Service
public class IAdminServImpl implements IAdminServ{

	@Autowired
	private IAdminMapper mapper;
	
	
	@Override
	public boolean login(Admin admin) {
		return mapper.getAdminByUsernameAndPassword(admin) != null;
	}
	
	
	@Override
	public Admin getAdminByUsernameAndPassword(Admin admin) {
		return mapper.getAdminByUsernameAndPassword(admin);
	}

	@Override
	public boolean addAdmin(Admin admin) {
		return mapper.addAdmin(admin);
	}

	@Override
	public boolean updateAdminPassword(Admin admin) {
		return mapper.updateAdminPassword(admin);
	}


	@Override
	public Admin getAdminByUsername(String username) {
		return mapper.getAdminByUsername(username);
	}


	@Override
	public boolean checkIfUsernameExist(String username) {
		return mapper.getAdminByUsername(username) == null;
	}

}
