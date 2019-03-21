package com.sxt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制器
 * @author Kyo
 *
 */
@Controller
public class PageController {

	/**
	 * 跟目录就跳转到main.jsp页面
	 * @return
	 */
	@RequestMapping("/")
	public String showMain() {
		return "main";
	}
	
	
	/**
	 * restful 风格
	 *    传递的是什么数据就跳转到对应的页面
	 * @param path
	 * @return
	 */
	@RequestMapping("/{path}")
	public String showPage(@PathVariable String path) {
		return path;
	}
	
	@RequestMapping("/top")
	public String showTop(String path) {
		return "top";
	}
	
	
}
