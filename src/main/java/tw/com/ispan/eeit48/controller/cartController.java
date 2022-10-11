package tw.com.ispan.eeit48.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.ispan.eeit48.dao.cartRepository;
import tw.com.ispan.eeit48.dao.item_pictureRepository;
import tw.com.ispan.eeit48.dao.itemsRepository;
import tw.com.ispan.eeit48.dao.member_payment_detailRepository;
import tw.com.ispan.eeit48.dao.membersRepository;
import tw.com.ispan.eeit48.model.cart;
import tw.com.ispan.eeit48.model.item_picture;
import tw.com.ispan.eeit48.model.items;
import tw.com.ispan.eeit48.model.member_payment_detail;
import tw.com.ispan.eeit48.model.members;

@RestController
public class cartController {
@Autowired
cartRepository cr;
@Autowired
item_pictureRepository ipr;
@Autowired
membersRepository mr;
@Autowired
itemsRepository ir;
@Autowired
member_payment_detailRepository  member_payment_detailRepository;
@Autowired
membersController mc;


//加入購物車addInCart
     @RequestMapping("/addInCart")
         public void addInCart(String items_id,cart cart,HttpSession session) {
    
    	 Integer itemsi=Integer.parseInt(items_id);
    	 
    	 items item=ir.queryByitem_id(itemsi);
    	 //商品ID取得會員
    	 
    	
    	 //取得帳戶名稱
        String owner_account=mr.findmember_accountBymember_id(item.getMember_id());
        cart.setOwner_account(owner_account);
        System.out.println("999");
        //字串轉Integer
        Integer itemsid=Integer.parseInt(items_id);
	    cart.setItems_id(itemsid);	
	    //設置member_id
	    var member=(members)session.getAttribute("member");
	  if(member==null) {return;}
	    Integer memberID=member.getMember_id();
	    System.out.println(memberID);
	  if( cr.findBymember_idAnditem_id(memberID,itemsid).isEmpty()==true) {
		  cart.setMember_id(member.getMember_id());
		    cr.save(cart);		  
	  }
	  else {
		
	  }

	
	    
}
     
   //刪除購物車/deleteCart
     @RequestMapping("/deleteCart")
         public void deleteCart(cart cart) {
    	cr.delete(cart);
}
     
     
     

	//cart 取得購物車資料
	@RequestMapping("/getcart/")
	public cartBack getCart(HttpSession session) {
		cartBack cb=new cartBack();	
	// member_id來取得cart 數字後來要改成 登入者ID
		  var member=(members)session.getAttribute("member");
		  if(member==null) {
			  return null;
		  }
		    Integer memberID=member.getMember_id();
	System.out.println(memberID);
//	var memberID=2測試用
	
    List<cart> lc= cr.findBymember_id(memberID);		
	LinkedList<item_picture> llip=new LinkedList<item_picture>();	
	LinkedList<members> lmo=new LinkedList<members>();
	LinkedList<items> lir=new LinkedList<items>();
	//cart取得item_id取得item_picture圖片URL
	lc.forEach((e)->{
		llip.add(ipr.queryByitem_id(e.getItems_id()));
	});	
	//cart取得item_id取得items
	lc.forEach((e)->{
		lir.addAll(ir.findByitem_id(e.getItems_id()));
	});		
	//用cart的owner_account取得owner member
	lc.forEach((e)->{
		lmo.addAll(mr.findBymember_account(e.getOwner_account()));
	});	
	cb.setOwnermember(lmo);
	//用member_id取得member_payment_detail	
	cb.setListmember_payment_detail(member_payment_detailRepository.findBymember_id(memberID));
    //member_id取得member		
	cb.setBuymember(mr.findBymember_id(memberID));
	cb.setLcclass(lc);
	cb.setLlipclass(llip);
	cb.setItems(lir);
	return cb;
	}	
	
	
	
	//自訂類別
	public class cartBack{
     List<cart> lcclass;
	 LinkedList<item_picture> llipclass;
	 List<members> Buymember;
	 List<members> ownermember;
	 LinkedList<items> items;
	 List<member_payment_detail> listmember_payment_detail;
	 
	 
	public List<member_payment_detail> getListmember_payment_detail() {
		return listmember_payment_detail;
	}
	public void setListmember_payment_detail(List<member_payment_detail> listmember_payment_detail) {
		this.listmember_payment_detail = listmember_payment_detail;
	}
	public LinkedList<items> getItems() {
		return items;
	}
	public void setItems(LinkedList<items> items) {
		this.items = items;
	}
	public List<members> getOwnermember() {
		return ownermember;
	}
	public void setOwnermember(List<members> ownermember) {
		this.ownermember = ownermember;
	}
	
	public List<members> getBuymember() {
		return Buymember;
	}
	public void setBuymember(List<members> buymember) {
		Buymember = buymember;
	}
	public List<cart> getLcclass() {
		return lcclass;
	}
	public void setLcclass(List<cart> lcclass) {
		this.lcclass = lcclass;
	}
	public LinkedList<item_picture> getLlipclass() {
		return llipclass;
	}
	public void setLlipclass(LinkedList<item_picture> llipclass) {
		this.llipclass = llipclass;
	}		
	}

}
