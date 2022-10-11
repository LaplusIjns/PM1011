package tw.com.ispan.eeit48.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//import tw.com.ispan.eeit48.dao.chatroomRepository;
import tw.com.ispan.eeit48.dao.item_categoriesRepository;
import tw.com.ispan.eeit48.model.chatrooms;
import tw.com.ispan.eeit48.model.item_categories;
@CrossOrigin
@Controller
public class RoutingController {
//	@Autowired chatroomRepository chatroomRepository;
//	@Autowired chatrooms chatrooms;
	
	@Autowired item_categoriesRepository item_categoriesRepository;
	@Autowired item_categories  item_categories;
	
//	@RequestMapping(path = {"/aaa"})
//	public String method77() {
//		
//	System.out.println(chatroomRepository.count());	;
//		return "/index";
//	}
	
	@RequestMapping(path = {"/"})
	public String method1() {
//		return "/index";
		return "/index.html";
	}
//	   @RequestMapping(path = {"/html/product.html/{accountname}"})
//	    public String method5(@PathVariable String accountname) {
//	        return "/index.html";
//	    }
	@RequestMapping(path = {"/secure/login.page"})
	public String method2() {
		return "/secure/login";
	}
	@RequestMapping(path = {"/pages/product.page"})
	public String method3() {
		return "/WEB-INF/views/pages/product.jsp";
	}
	@RequestMapping(path = {"/pages/display.page"})
	public String method4() {
		return "/pages/display";
	}
}
