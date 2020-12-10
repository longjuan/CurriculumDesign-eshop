package com.eshop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshop.entity.CartProduct;
import com.eshop.entity.Order;
import com.eshop.entity.User;
import com.eshop.service.OrderService;
import com.eshop.service.ShopCartService;

@RequestMapping("/customer")
@Controller
public class ShopCartController {
	@Autowired
	private ShopCartService shopCartService;
	@Autowired
	private OrderService orderService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addCartProduct", method = RequestMethod.POST)
	public String addCartProduct(HttpServletRequest request, @RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "quantity", required = true) int quantity) {
		HttpSession session = request.getSession();
		session.setAttribute("ShopCart",
				shopCartService.addCartProduct(id, quantity, (List<CartProduct>) session.getAttribute("ShopCart")));
		session.setAttribute("ShopCartVersion", (int) session.getAttribute("ShopCartVersion") + 1);
		return "redirect:/customer/shopCart";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delectCartProduct", method = RequestMethod.GET)
	public String delectCartProduct(HttpServletRequest request, @RequestParam(value = "id", required = true) int id) {
		HttpSession session = request.getSession();
		session.setAttribute("ShopCart",
				shopCartService.delectCartProduct(id, (List<CartProduct>) session.getAttribute("ShopCart")));
		session.setAttribute("ShopCartVersion", (int) session.getAttribute("ShopCartVersion") + 1);
		return "redirect:/customer/shopCart";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/shopCart", method = RequestMethod.GET)
	public ModelAndView showShopCart(HttpServletRequest request, ModelAndView model) {
		model.addObject("ShopCart",
				shopCartService.fillInfo((List<CartProduct>) request.getSession().getAttribute("ShopCart")).stream()
						.filter(a -> a.getProduct() != null).collect(Collectors.toList()));
		model.setViewName("shopCart");
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/settlement", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> settlement(HttpServletRequest request,
			@RequestParam(value = "ShopCartVersion", required = true) int ShopCartVersion) {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		if ((int) session.getAttribute("ShopCartVersion") != ShopCartVersion) {
			map.put("message", "购物车有更新，请刷新后重试");
			return map;
		}
		if (((ArrayList<CartProduct>) session.getAttribute("ShopCart")).size() == 0) {
			map.put("message", "购物车中无商品，请添加商品后重试");
			return map;
		}
		List<CartProduct> newCartInfo = shopCartService
				.fillInfo((List<CartProduct>) request.getSession().getAttribute("ShopCart")).stream()
				.filter(a -> a.getProduct() != null).collect(Collectors.toList());
		if (!orderService.placeOrder(newCartInfo)) {
			map.put("message", "库存不足或下单人数太多，请刷新后重试");
			return map;
		}
		orderService.saveOrder(new Order(newCartInfo, ((User) session.getAttribute("user")).getUsername()));
		session.setAttribute("ShopCartVersion", (int) session.getAttribute("ShopCartVersion") + 1);
		session.setAttribute("ShopCart", new ArrayList<CartProduct>());
		map.put("message", "结算成功");
		map.put("callback", "/eshop/customer/order");
		return map;
	}

}
