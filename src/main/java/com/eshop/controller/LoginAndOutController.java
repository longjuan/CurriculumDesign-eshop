package com.eshop.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eshop.entity.CartProduct;
import com.eshop.entity.User;
import com.eshop.service.ShopCartService;
import com.eshop.service.UserService;

@Controller
public class LoginAndOutController {
	@Autowired
	private UserService userService;
	@Autowired
	private ShopCartService shopCartService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request,
			@RequestParam(value = "switch", required = false) boolean switchac) {
		HttpSession session = request.getSession();
		if (switchac) {
			User user = (User) session.getAttribute("user");
			if(user != null && user.getUsertype() == 1) {
				shopCartService.saveCart(user.getUsername(), (List<CartProduct>) session.getAttribute("ShopCart"));
			}
			remove3Attribute(session);
			return "login";
		}
		remove3Attribute(session);
		String username_cookie = "";
		String password_cookie = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			Map<Object, String> collect = Stream.of(cookies).collect(Collectors.toMap( e -> e.getName(),  e -> e.getValue()));
			username_cookie = collect.get("username");
			password_cookie = collect.get("password");
			User result = userService.checkUserByNaP(new User(username_cookie, password_cookie, null));
			if (result != null) {
				session.setAttribute("user", result);
				session.setAttribute("ShopCart", shopCartService.getDBCart(result.getUsername()));
				session.setAttribute("ShopCartVersion", 1);
				return "redirect:/";
			} else {
				return "login";
			}
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, @RequestParam(value = "autoLogin", required = false) String autoLogin, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
			model.addAttribute("message", "用户名和密码不能为空");
			return "login";
		}
		User result = userService.checkUserByNaP(user);
		if (result == null) {
			model.addAttribute("message", "用户名或密码错误");
			return "login";
		} else {
			if (autoLogin != null) {
				Cookie usrnCookie = new Cookie("username", user.getUsername());
				Cookie pswCookie = new Cookie("password", user.getPassword());
				usrnCookie.setMaxAge(60 * 20);
				pswCookie.setMaxAge(60 * 20);
				response.addCookie(usrnCookie);
				response.addCookie(pswCookie);
			}
			session.setAttribute("user", result);
			session.setAttribute("ShopCart", shopCartService.getDBCart(result.getUsername()));
			session.setAttribute("ShopCartVersion", 1);
			return "redirect:/";
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null && user.getUsertype() == 1) {
			shopCartService.saveCart(user.getUsername(), (List<CartProduct>) session.getAttribute("ShopCart"));
		}
		remove3Attribute(session);
		return "redirect:/";
	}
	
	private void remove3Attribute(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("ShopCart");
		session.removeAttribute("ShopCartVersion");
	}
}
