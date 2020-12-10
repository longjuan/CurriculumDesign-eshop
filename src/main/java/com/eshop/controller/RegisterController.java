package com.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eshop.entity.User;
import com.eshop.service.UserService;

@Controller
public class RegisterController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterPage() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(User user, Model model) {
		if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
			model.addAttribute("message", "用户名和密码不能为空");
			return "register";
		}
		if(userService.addUser(user)) {
			model.addAttribute("message", "注册成功，请登录");
			return "login";
		}else {
			model.addAttribute("message", "用户名已经存在");
			return "register";
		}
	}

}