package com.sxt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.sxt.dto.UserDto;
import com.sxt.pojo.User;
import com.sxt.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userService;
	
	@RequestMapping("/query")
	public String query (User user,Model model) {
		List<User> list=userService.query(user);
		model.addAttribute("list", list);
		return "user/user";
	}
	
	@RequestMapping("/queryPage")
	public String queryPage(UserDto userDto,Model model) {
		PageInfo<User> pageModel=userService.queryPage(userDto);
		
		//key必须命名为pageModel，因为要在显示分页的pageBar.jsp文件中用
		model.addAttribute("pageModel", pageModel);
		return "user/user";
	}
	
	@RequestMapping("/userUpdate")
	public String userUpdatePage(Integer id,Model model ) throws Exception{
		//查询添加或者更新需要的数据
		userService.getUpdateUserInfo(id, model);
		return "/user/userUpdate";
	}
	
	@RequestMapping(value="/saveOrUpdate")
	public String saveOrUpdate(UserDto userDto) throws Exception {
		System.out.println(userDto.toString());
		userService.saveOrUpdate(userDto);
		return "redirect:/user/queryPage";
	}
	
	@RequestMapping("/delete")
	public String deleteUser(Integer id) throws Exception{
		userService.deleteUser(id);
		return "redirect:/user/query";
	}

}
