package com.sxt.dto;

import java.util.List;

import com.sxt.pojo.User;

/**
 * 数据传输对象，只是为了方便保存用户+角色
 * @author Kyo
 *
 */
public class UserDto extends BasePage{

	private List<Integer> roleIds;
	private User user;
	
	public UserDto() {
	}

	public UserDto(List<Integer> roleIds, User user) {
		super();
		this.roleIds = roleIds;
		this.user = user;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserDto [roleIds=" + roleIds + ", user=" + user + "]";
	}

	
	
}
