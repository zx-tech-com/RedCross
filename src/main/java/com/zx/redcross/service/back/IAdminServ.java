package com.zx.redcross.service.back;

import com.zx.redcross.entity.Admin;

public interface IAdminServ {
	
	Admin getAdminByUsernameAndPassword(Admin admin);
	
	boolean addAdmin(Admin admin);
	
	boolean updateAdminPassword(Admin admin);
	
	boolean login(Admin admin);

	Admin getAdminByUsername(String username);
	
	boolean checkIfUsernameExist(String username);
	
}
