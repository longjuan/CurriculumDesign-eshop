package com.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eshop.entity.Product;
import com.eshop.service.ProductService;

@Controller
public class DetailController {
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView getDetail(ModelAndView model, @RequestParam(value = "id", required = true) int id) {
		Product product = productService.getDetail(id);
		product.setParameter(product.getParameter().replaceAll("\n", "<br>").replaceAll("\t", "&emsp;"));
		model.addObject("product", product);
		model.setViewName("detail");
		return model;
	}
}
