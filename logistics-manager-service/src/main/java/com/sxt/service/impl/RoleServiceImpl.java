package com.sxt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sxt.mapper.RoleMapper;
import com.sxt.pojo.Role;
import com.sxt.pojo.RoleExample;
import com.sxt.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Resource
	private RoleMapper mapper;

	@Override
	public List<Role> query(Role role) {
		RoleExample example=new RoleExample();
		if (role!=null&&(!role.getRoleName().equals(""))&&role.getRoleName()!=null) {
			example.createCriteria().andRoleNameLike("%"+role.getRoleName()+"%");
		}
		return mapper.selectByExample(example);
	}

	//insertSelective对应的sql语句加入了NULL校验，即只会插入数据不为null的字段值。
	//insert则会插入所有字段，会插入null。
	@Override
	public void addRole(Role role) throws Exception {
		mapper.insertSelective(role);
	}

	@Override
	public void updateRole(Role role) throws Exception {
		mapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public void deleteRole(int id) throws Exception {
		mapper.deleteByPrimaryKey(id);
	}
	
	
	
}
