package com.eshop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value="/state",method=RequestMethod.GET)
	public void template(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String filename = "state.pdf";
		response.setContentType("application/octet-stream");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(filename, "UTF-8"));
		InputStream in = request.getServletContext().getResourceAsStream("/pdf/"+filename);
		ServletOutputStream out = response.getOutputStream();
		byte[] bs=new byte[1024];
		int len=-1;
		while((len=in.read(bs))!=-1){
			out.write(bs,0,len);
		}
		out.close();
		in.close();
	}
}
