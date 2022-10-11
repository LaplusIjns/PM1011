package tw.com.ispan.eeit48.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.ispan.eeit48.dao.itemsRepository;
import tw.com.ispan.eeit48.dao.membersRepository;
import tw.com.ispan.eeit48.dao.order_detailRepository;
import tw.com.ispan.eeit48.dao.order_listRepository;
import tw.com.ispan.eeit48.model.items;
import tw.com.ispan.eeit48.model.members;
import tw.com.ispan.eeit48.model.order_details;
import tw.com.ispan.eeit48.model.order_list;

@RestController
public class order_detailController {

	
	@Autowired
	order_detailRepository odr;
	
	@Autowired
	order_listRepository  olr;
	
	@Autowired
	membersRepository  mr;

	
	@Autowired
	itemsRepository ir;
	
	@Autowired
	membersController mc;
	
	
	//商品頁面取得商品預約日期
	@RequestMapping(path = {"/getOrderTime"})	
	public List<order_details> getOrderTime(String item_id) {
		Integer itemid=Integer.parseInt(item_id);
	return	odr.findByitem_id(itemid);
	}
	
	
	
	//購物車下訂單頁面
		@RequestMapping(path = {"/sendorderdetail/"})	
		public void sendOrderDetail(order_details od) {
			odr.save(od);
		}
	
	
	
	@RequestMapping(path = {"/pages/receiveorder"})	
	//已接受訂單頁面 回傳所有訂單OrderDetail OrderList
        public Orderlist frontCommodity(HttpSession session) {  
		
		Orderlist ol = new Orderlist();
        //var memberID=2;
		  var member=(members)session.getAttribute("member");
		    Integer memberID=member.getMember_id();
		
		//用會員ID 取得 item 
		List<items> s=	ir.findBymember_id(memberID);	
		
		LinkedList<order_details> listod =new LinkedList<order_details>();
		
		LinkedList<order_list> listol =new LinkedList<order_list>();
		
		LinkedList<members> lm =new LinkedList<members>();
		
		//用item取得itmeID取得 order_details
		s.forEach((e)->{
			listod.addAll(odr.findByitem_id(e.getItem_id())	);
		});
		System.out.println(s);
		//order_details取得order_id取得order_list	
		
		listod.forEach((e)->{
			listol.addAll(olr.findByorder_id(e.getOrder_id()))	;
		});		
		//從order_list的id找到members的account
		listol.forEach((e)->{
			lm.addAll(mr.findBymember_id(e.getMember_id()))	;
		});	
		
		ol.setLinkedListorder_details(listod);
		ol.setLinkedListorder_list(listol);
		ol.setListitems(s);
		ol.setLinkedListmembers(lm);
		return ol;
	}
	
	//接受訂單
	@RequestMapping(path = {"/agreedOrder"})
	public void agreedOrder(String order_id) {
		System.out.println(order_id);
		int i=Integer.parseInt(order_id);  
		odr.updateOrder_detail_state(i);
	}
	
//下定訂單
	@RequestMapping(path = {"/pages/myorder"})
	public Orderlist myOrder(HttpSession session) {
		
   Orderlist ol = new Orderlist();
   //取得會員SESSION
   var member=(members)session.getAttribute("member");
   Integer memberID=member.getMember_id();
   
	//用會員ID找到order_list
	List<order_list> listol =olr.findBymember_id(memberID);	
	
	LinkedList<order_details> listod =new LinkedList<order_details>();
	
	LinkedList<items> items=new LinkedList<items>();
	
	LinkedList<members> lm =new LinkedList<members>();
	
	//用order_list的order_id找到對應的order_detail
    listol.forEach((e)->{
    	   listod.addAll(odr.findByorder_id(e.getOrder_id()));   	
    });
    //用order_detail的item_id找到對應的items
    listod.forEach((e)->{
    items.addAll(ir.findByitem_id(e.getItem_id()));
    });
    //用items的member_id找到對應的members
    items.forEach((e)->{
    lm.addAll(mr.findBymember_id(e.getMember_id()));
    });    
    ol.setLinkedListorder_details(listod);
	ol.setListorder_list(listol);
	ol.setListitems(items);
	ol.setLinkedListmembers(lm);
    
		
		return ol;
	}	
		
		

		
		
    
	public class Orderlist{
		LinkedList<order_list>  LinkedListorder_list;
		LinkedList<order_details> LinkedListorder_details;
		List<items> Listitems;
		List<order_list>  Listorder_list;		
		public List<order_list> getListorder_list() {
			return Listorder_list;
		}
		public void setListorder_list(List<order_list> listorder_list) {
			Listorder_list = listorder_list;
		}
		LinkedList<members>  LinkedListmembers;
		
		
		
		public LinkedList<members> getLinkedListmembers() {
			return LinkedListmembers;
		}
		public void setLinkedListmembers(LinkedList<members> linkedListmembers) {
			LinkedListmembers = linkedListmembers;
		}
		public List<items> getListitems() {
			return Listitems;
		}
		public void setListitems(List<items> listitems) {
			Listitems = listitems;
		}
		public LinkedList<order_list> getLinkedListorder_list() {
			return LinkedListorder_list;
		}
		public void setLinkedListorder_list(LinkedList<order_list> linkedListorder_list) {
			LinkedListorder_list = linkedListorder_list;
		}
		public LinkedList<order_details> getLinkedListorder_details() {
			return LinkedListorder_details;
		}
		public void setLinkedListorder_details(LinkedList<order_details> linkedListorder_details) {
			LinkedListorder_details = linkedListorder_details;
		}
	
		
	}
}
