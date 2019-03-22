package com.sxt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxt.dto.UserDto;
import com.sxt.mapper.RoleMapper;
import com.sxt.mapper.UserMapper;
import com.sxt.pojo.Role;
import com.sxt.pojo.RoleExample;
import com.sxt.pojo.User;
import com.sxt.pojo.UserExample;
import com.sxt.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	@Override
	public List<User> query(User user) {
		UserExample example=new UserExample();
		return userMapper.selectByExample(example);
	}

	@Override
	public void addUser(User user) throws Exception {
		userMapper.insertSelective(user);
	}

	@Override
	public void updateUser(User user) throws Exception {
		userMapper.updateByPrimaryKey(user);

	}

	@Override
	public void deleteUser(int id) throws Exception {
		//1、先根据userId删除t_user_role表里的关联关系
		userMapper.deleteRoleIdByUserId(id);
		//2、再删除用户信息
		userMapper.deleteByPrimaryKey(id);

	}

	@Override
	public void getUpdateUserInfo(Integer userId, Model model) throws Exception {
		RoleExample roleExample=new RoleExample();
		List<Role> roles=roleMapper.selectByExample(roleExample);
		System.out.println("执行了getUpdateUserInfo，userId为："+userId);
		
		if (userId!=null&&userId>0) {//传过来了id，就表示要修改数据。首先根据id查询用户信息
			User user=userMapper.selectByPrimaryKey(userId);
			//查询用户具有的角色信息
			List<Integer> roleIds =userMapper.selectRoleIdByUserId(userId);
			model.addAttribute("user", user);
			model.addAttribute("roleIds", roleIds);
			
			System.out.println("修改前，该用户信息和角色信息："+user+roleIds.toString());
		}
		model.addAttribute("roles", roles);
	}

	@Override
	public void saveOrUpdate(UserDto userDto) throws Exception {
		System.out.println("执行了saveOrUpdate方法");
		//获取User对象
		User user=userDto.getUser();
		//获取关联的角色信息
		List<Integer> roleIds=userDto.getRoleIds();
		//判断是添加还是修改数据
		if (user.getUserId()!=null&&user.getUserId()>0) {
			//表示userId存在，说明是要更新
			userMapper.updateByPrimaryKeySelective(user);
			//根据用户ID删除关联的角色信息
			userMapper.deleteRoleIdByUserId(user.getUserId());
			//最后保存用户和角色的关联关系
			if (roleIds!=null&&roleIds.size()>0) {
				for (Integer roleId : roleIds) {
					userMapper.insertUserIdAndRoleId(user.getUserId(), roleId);
				}
			}
			
		}else {
			//不存在id说明是添加数据
			//先添加用户数据，获取生成的userId
			userMapper.insert(user);
			//再保存用户和角色的对应关系，在一个事务中处理
			if (roleIds!=null&&roleIds.size()>0) {
				for (Integer roleId : roleIds) {
					userMapper.insertUserIdAndRoleId(user.getUserId(), roleId);
				}
			}
		}
	}

	@Override
	public PageInfo<User> queryPage(UserDto userDto) {
		PageHelper.startPage(userDto.getPageNum(),userDto.getPageSize());
		List<User> list=this.query(userDto.getUser());
		PageInfo<User> pageInfo=new PageInfo<>(list);
		return pageInfo;
	}

}
