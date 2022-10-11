package tw.com.ispan.eeit48.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecpay.payment.integration.AllInOne;
//import ecpay.payment.integration.ExampleAllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@RestController
@RequestMapping(path = { "/pay" })
public class payController {
	public  AllInOne all;
	
	@PostMapping
	public String ECpay(String orderID,String orderDate,Integer amount,String Item){
		System.out.println(orderID + orderDate + amount + Item);
		all = new AllInOne("");
		AioCheckOutALL obj = new AioCheckOutALL();
		obj.setMerchantTradeNo(orderID);
		obj.setMerchantTradeDate(orderDate);
		obj.setTotalAmount(amount.toString());
		obj.setTradeDesc("test Description");
		obj.setItemName(Item);
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);
		return form;
	}
}
