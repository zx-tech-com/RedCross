package com.zx.redcross.serviceimpl.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.zx.redcross.dao.course.GroupOrderMapper;
import com.zx.redcross.service.course.GroupOrderServ;
@Service
public class GroupOrderServImpl implements GroupOrderServ{
	@Autowired
	private GroupOrderMapper mapper;

}
