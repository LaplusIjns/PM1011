package tw.com.ispan.eeit48.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import tw.com.ispan.eeit48.dao.member_payment_detailRepository;
import tw.com.ispan.eeit48.dao.membersRepository;
import tw.com.ispan.eeit48.model.member_payment_detail;
import tw.com.ispan.eeit48.model.members;

@CrossOrigin
@RestController
@RequestMapping
public class member_payment_detailController {
	
@Autowired
member_payment_detailRepository member_payment_detailRepository;
@Autowired 
member_payment_detail member_payment_detail;
@Autowired
membersRepository membersRepository ;
@Autowired
membersController mc;



//memeber_payment 增加信用卡
	@RequestMapping(path = {"/addPaymentInformation"})	
	public void addPaymentInformation(member_payment_detail member_payment_detail,HttpSession session) {
		  var member=(members)session.getAttribute("member");
		    Integer memberID=member.getMember_id();	 
//		 var memberID=2; 測試用
		member_payment_detail.setMember_id(memberID);
		member_payment_detailRepository.save(member_payment_detail);
	}
	
	
	
//取得信用卡 帳戶資訊
	@RequestMapping(path = {"/getPaymentInformation"})	
	public payInformation getPaymentInformation(members members,HttpSession session) {	
		payInformation payInformation = new payInformation();
//取得member
		  var member=(members)session.getAttribute("member");
		  if(member==null) {return null;}
		    Integer memberID=member.getMember_id();		 
//		 var memberID=2; 測試用
		payInformation.setListmembers(membersRepository.findBymember_id(memberID));
		
		payInformation.setListmember_payment_detail(member_payment_detailRepository.findBymember_id(memberID));	
	
		return 	payInformation;
	}

	
	//刪除信用卡資訊	
	@RequestMapping(path = {"/deleteCreditcard"})	
	public void deleteCreditcard(member_payment_detail member_payment_detail) {	
		member_payment_detailRepository.delete(member_payment_detail);		
	}
	
	
	
	
public class payInformation{
	
	List<member_payment_detail> listmember_payment_detail;
	List<members> listmembers;
	
	public List<members> getListmembers() {
		return listmembers;
	}
	public void setListmembers(List<members> listmembers) {
		this.listmembers = listmembers;
	}
	public List<member_payment_detail> getListmember_payment_detail() {
		return listmember_payment_detail;
	}
	public void setListmember_payment_detail(List<member_payment_detail> listmember_payment_detail) {
		this.listmember_payment_detail = listmember_payment_detail;
	}	
}
	
}
