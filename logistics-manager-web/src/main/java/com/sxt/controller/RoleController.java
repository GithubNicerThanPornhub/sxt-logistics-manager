package com.sxt.controller;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sxt.pojo.Role;
import com.sxt.service.IRoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource
	private IRoleService service;
	
	@RequestMapping("/query")
	public String query(Role role,Model model) {
		System.out.println("执行了query方法");
		role.setRoleName("员");//随意设置了一个参数方便展示数据，可删
		List<Role> list=service.query(role);
		model.addAttribute("list", list);
		return "role/role";//这里是返回到role.jsp页面
	}
	
	
	
	
}
