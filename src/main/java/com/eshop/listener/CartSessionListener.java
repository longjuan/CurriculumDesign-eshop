package com.eshop.listener;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eshop.entity.CartProduct;
import com.eshop.entity.User;
import com.eshop.service.ShopCartService;

public class CartSessionListener implements HttpSessionListener{
	@Autowired
	private ShopCartService shopCartService;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		try {
			ApplicationContext ctx = WebApplicationContextUtils
					.getWebApplicationContext(se.getSession().getServletContext());
			shopCartService = (ShopCartService) ctx.getBean("shopCartServiceImpl");
			System.out.println(ctx);
			if(session.getAttribute("user")!=null) {
				String username = ((User)session.getAttribute("user")).getUsername();
				List<CartProduct> cart = (List<CartProduct>)session.getAttribute("ShopCart");
				shopCartService.saveCart(username, cart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
