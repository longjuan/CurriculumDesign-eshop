package com.eshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eshop.entity.User;
import com.eshop.service.OrderService;

@RequestMapping("/customer")
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView showShopCart(HttpServletRequest request,ModelAndView model) {
		model.addObject("orders", orderService.getOrder(((User)request.getSession().getAttribute("user")).getUsername()));
		model.setViewName("order");
		return model;
	}
}
