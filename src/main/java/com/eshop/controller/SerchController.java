package com.eshop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eshop.service.ProductService;

@Controller
public class SerchController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/serch", method = RequestMethod.GET)
	public ModelAndView serch(ModelAndView model,
			@RequestParam(value = "q",required = true) String q,
			@RequestParam(value = "page",required = false,defaultValue = "1") int page
			) {
		if(page <= 0) {
			page = 1;
		}
		model.setViewName("serch");
		model.addObject("q", q);
		model.addObject("page", page);
		Map<String, Object> result = productService.serch(q, (page-1)*8, 8);
		model.addObject("products",result.get("products"));
		model.addObject("num", result.get("num"));
		return model;
	}
}
