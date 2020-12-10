package com.eshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.eshop.common.Const;
import com.eshop.common.MyUtils;
import com.eshop.entity.Product;
import com.eshop.entity.User;
import com.eshop.service.ProductService;

@Controller
@RequestMapping("/merchant")
public class MerchantManageController {
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public ModelAndView showShopCart(HttpServletRequest request, ModelAndView model) {
		model.addObject("products",
				productService.getBySaleusername(((User) request.getSession().getAttribute("user")).getUsername()));
		model.setViewName("mPManage");
		return model;
	}

	@RequestMapping(value = "/deleteMyProduct", method = RequestMethod.GET)
	public String deleteMyProduct(HttpServletRequest request, @RequestParam(value = "id", required = true) int id) {
		productService.deleteMyProduct(((User) request.getSession().getAttribute("user")).getUsername(), id);
		return "redirect:/merchant/manage";
	}

	@RequestMapping(value = "/addMyProduct", method = RequestMethod.GET)
	public String showAddMyProduct() {
		return "merchantAddP";
	}

	@RequestMapping(value = "/addMyProduct", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addMyProduct(@RequestParam(value = "img", required = true) MultipartFile[] file,
			@RequestParam(value = "pricedouble", required = true) double pricedouble, Product product,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		product.setPrice((int) Math.round(pricedouble * 100));
		product.setSaleusername(((User) request.getSession().getAttribute("user")).getUsername());
		if (MyUtils.haveNull(product.getParameter(), product.getPname(), product.getPrice(), product.getSaleusername(),
				product.getStock())) {
			map.put("message", "请填写完成全部项目");
			map.put("code", "400");
			return map;
		}
		for (MultipartFile multipartFile : file) {
			if (multipartFile.getSize() == 0) {
				map.put("message", "请填写完成全部项目");
				map.put("code", "400");
				return map;
			}
			String subfix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.')+1);
			if(!Const.allowSubfix.contains(subfix)) {
				map.put("message", "图片仅能上传\"gif\",\"jpeg\",\"jpg\",\"png\"格式的图片");
				map.put("code", "400");
				return map;
			}
		}
		ServletContext context = request.getSession().getServletContext();
		String realPath = context.getRealPath("/img");
		ArrayList<String> nameList = new ArrayList<String>();
		for (int i = 0; i < file.length; i++) {
			MultipartFile multipartFile = file[i];
			String fileName = "" + request.getSession().getId().substring(0, 5) + System.currentTimeMillis() + i
					+ multipartFile.getOriginalFilename()
							.substring(multipartFile.getOriginalFilename().lastIndexOf('.'));
			try {
				multipartFile.transferTo(new File(realPath + File.separator + fileName));
				nameList.add(fileName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		product.setImg1(nameList.get(0));
		product.setImg2(nameList.get(1));
		product.setImg3(nameList.get(2));
		productService.addMyProduct(product);
		map.put("code", "200");
		return map;
	}
}
