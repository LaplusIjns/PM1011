package tw.com.ispan.eeit48.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.dao.order_listRepository;
import tw.com.ispan.eeit48.model.members;
import tw.com.ispan.eeit48.model.order_list;

@RestController
public class order_listController {

@Autowired
order_listRepository olr;

@Autowired
membersController mc;

//建立orderlist資料	
	@RequestMapping("/sendorderlist/")
	public order_list sendorderlist(order_list orderlist,HttpSession session) {
		LocalDate todaysDate = LocalDate.now();
        System.out.println(todaysDate);
        orderlist.setOrder_date(todaysDate.toString());
        
        var member=(members)session.getAttribute("member");
	    Integer memberID=member.getMember_id();
	    
	    orderlist.setMember_id(memberID);
	    
		 olr.save(orderlist);
		 
		 
		 return olr.getById(orderlist.getOrder_id());
	}
}