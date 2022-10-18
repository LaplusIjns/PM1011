package tw.com.ispan.eeit48.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import tw.com.ispan.eeit48.dao.item_pictureRepository;
import tw.com.ispan.eeit48.dao.itemsRepository;
import tw.com.ispan.eeit48.dao.membersRepository;
import tw.com.ispan.eeit48.model.item_picture;
import tw.com.ispan.eeit48.model.items;
import tw.com.ispan.eeit48.model.members;

@RestController
public class itemController{

@Autowired
itemsRepository ir;

@Autowired
item_pictureRepository ipr;

@Autowired
membersController mc;

@Autowired 
membersRepository membersRepository;

@Autowired
item_pictureRepository item_pictureRepository;

//更新商品資料 Myproduct頁
@RequestMapping(value = "/updateItem")
@ResponseBody // 引數：(圖片名稱，圖片（不進行特殊處理）)前端統一放到file中，傳給後端
public void updateItem(@RequestParam("uploadImg") MultipartFile file,
	String item_name,String item_date,String item_state,
		String item_price,String item_id,item_picture ip) {
   System.out.println(item_price);
   System.out.println(item_id);
   System.out.println(item_state);
   System.out.println(item_date);
   System.out.println(item_name);
    Integer  itemprice=Integer.parseInt(item_price);
    
    Integer itemid=Integer.parseInt(item_id);
//把商品更新進資料庫
	ir.updateitems(item_name, item_state, item_date,itemprice,itemid);

 if(file!=null && !file.isEmpty()) {
	  //判斷檔案是否為空
	    if (file.isEmpty()) {
	     
	    }
	    // 獲取檔名
	    String fileName = file.getOriginalFilename();
	    fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
	    //加個時間戳，儘量避免檔名稱重複
	    String path = "E:/HTML-master//PocketRoamingHTML/img/" + fileName;
	   
	    
	    //創建圖片前端對應的URL
//	    String rpath= "../img/"+ fileName;
	    String rpath= fileName;
	    //把itme表的對應ID塞進itemPpicture的ID

	    ip.setItem_id(itemid)  ;
	    ip.setPicture_url(rpath);        
	    ipr.save(ip);
	    
	    //建立檔案路徑
	    File dest = new File(path);
	    //判斷檔案是否已經存在q
	    if (dest.exists()) {
	     
	    }
	    //判斷檔案父目錄是否存在
	    if (!dest.getParentFile().exists()) {
	        dest.getParentFile().mkdir();
	    }
	    try {
	        //上傳檔案
	        file.transferTo(dest); //儲存檔案
//	        url = "http://188.131.163.160:8087" + "/file/image/" + fileName;//外部訪問路徑，對應本地的c:/fileupload
	   } catch (IOException e) {
	     
	    }
	   
 }
  
}


//我的商品頁面 刪除商品 (狀態改變)
@RequestMapping("/mp/deleteItems")

public void delitems(String item_id) {
	System.out.println("123");
	System.out.println(item_id);
	Integer itemid=Integer.parseInt(item_id);
	ir.deleteitem(itemid);
}



//進入商品頁面 用商品ID取出對應資料
@RequestMapping(path = {"product{item_id}"})
public product product(@PathVariable(name="item_id")String item_id) {
	Integer itemid=Integer.parseInt(item_id);
	product product=new product();
	List<items> listitemid=ir.findByitem_id(itemid);
	product.setLitems(listitemid);
	//用item_ID找出對應的item_picture
	product.setItem_picture(item_pictureRepository.queryByitem_id(itemid));
	//用item的memberID找出對應member 
	listitemid.forEach((e)->{	
		product.setMember(membersRepository.queryBymember_id(e.getMember_id()));		
	});	
	return product;
}


//
//
//上架商品資料
	@RequestMapping(path = {"/pages/saveMyItemData"})
	public String methodUploadCommodity(items item ,HttpSession session) {
		
		  var member=(members)session.getAttribute("member");
		    Integer memberID=member.getMember_id();
	
//	var memberID=2 測試用
	
	 item.setMember_id(memberID);
	 item.setItem_state("已上架");	 
	 ir.save(item);	
		System.out.println("ok");
	System.out.println(item.getItem_id());
		return "/index";
	}
	

    
//我的商品頁面進入頁面自動抓取資料  回傳個人商品不包括已刪除的'
   	@RequestMapping(path = {"/pages/getMyItemData"})
    public itemsAndPic methodGetCommodityById(HttpSession session) {  
//   	  var member=(members)mc.localSession.getAttribute("member");
   		members member = new members();
   		member =(members) session.getAttribute("member");
   		if(member==null) {return null;}
	    Integer memberID=member.getMember_id();
//   	var memberID=2;
     LinkedList<item_picture> lip= new LinkedList<item_picture>();
    	List<items>  a=ir.findByOwner_id(memberID);
       a.forEach((e)->{           	   
    	   lip.add(ipr.queryByitem_id(e.getItem_id()));
       });
       itemsAndPic iap=new itemsAndPic();
       iap.setLitems(a);
       iap.setItem_picture(lip);
    	return iap;
    }
   	
   	//首頁回傳所有商品(除了已下架跟已刪除)
	@RequestMapping(path = {"/pages/frontCommodity"})
    public itemsAndPic frontCommodity() {  
     LinkedList<item_picture> lip= new LinkedList<item_picture>();
    	List<items>  a=ir.findAllButDelete();
       a.forEach((e)->{           	   
    	   lip.add(ipr.queryByitem_id(e.getItem_id()));
       });
       itemsAndPic iap=new itemsAndPic();
       iap.setLitems(a);
       iap.setItem_picture(lip);
    	return iap;
    }
    
    
 //上架商品資料  商品圖片
    @RequestMapping(value = "/uploadFile")
    @ResponseBody // 引數：(圖片名稱，圖片（不進行特殊處理）)前端統一放到file中，傳給後端
    public void uploadFile(@RequestParam("uploadImg") MultipartFile file, HttpServletRequest request, 
    		HttpServletResponse response,items item,item_picture ip,HttpSession session,
    		String category_type,
    		String category_region,String boardgame_size,String console_type,
    		String comic_type,String novel_type,String boardgame_type) {
    	System.out.println(category_type);
    	String categoryID="";
    	
    	if(category_type.equals("漫畫")) {
    		categoryID+="1";
    		categoryID+=category_region;
    		categoryID+=comic_type;    		
    	
    	}else if(category_type.equals("小說")) {
    		categoryID+="2";
    		categoryID+=category_region;
    		categoryID+=novel_type;    	
    	}else if(category_type.equals("桌遊")) {
    		categoryID+="3";
    		categoryID+=boardgame_size;
    		categoryID+=boardgame_type; 
    	}else if(category_type.equals("主機")) {
    		categoryID+="4";
    		categoryID+=console_type;
    	}
    	System.out.println(categoryID);
    	Integer category_id=Integer.parseInt(categoryID);
    	item.setCategory_id(category_id);
    	
    	
    	
    	
    	
    	
    	var member=(members)session.getAttribute("member");
  	    Integer memberID=member.getMember_id();
//    	var memberID=2;測試用
    	item.setMember_id(memberID);
    	item.setItem_state("準備上架");
    //把商品存進資料庫
    	ir.save(item);
    //取得商品ID
    	item.getItem_id();
    	System.out.println(item.getItem_id());
        String url = null;
        int update = 0;
      
        //判斷檔案是否為空
        if (file.isEmpty()) {
         
        }
        // 獲取檔名
        String fileName = file.getOriginalFilename();
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //加個時間戳，儘量避免檔名稱重複
//        String path = "E:/HTML-master//PocketRoamingHTML/img/" + fileName;
//        String path = "C:/JavaFramework/" + fileName;
        		
      String path = "C:/JavaFramework/eclipse-workspace/CHNAG0112-master/HTML-master1006/PocketRoamingHTML/img/" + fileName;
//      
        //創建圖片前端對應的URL
//        String rpath= "../img/"+ fileName;
        String rpath= fileName;
        //把itme表的對應ID塞進itemPpicture的ID
        ip.setItem_id(item.getItem_id())  ;
        ip.setPicture_url(rpath);        
        ipr.save(ip);
        
        //建立檔案路徑
        File dest = new File(path);
        //判斷檔案是否已經存在q
        if (dest.exists()) {
         
        }
        //判斷檔案父目錄是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //上傳檔案
            file.transferTo(dest); //儲存檔案
//            url = "http://188.131.163.160:8087" + "/file/image/" + fileName;//外部訪問路徑，對應本地的c:/fileupload
       } catch (IOException e) {
         
        }
       
    }
    //自訂類別 
    public class itemsAndPic {
    	public  List<items> litems;
    	public  List<item_picture> item_picture;
		public List<items> getLitems() {
			return litems;
		}
		public void setLitems(List<items> litems) {
			this.litems = litems;
		}
		public List<item_picture> getItem_picture() {
			return item_picture;
		}
		public void setItem_picture(List<item_picture> item_picture) {
			this.item_picture = item_picture;
		}
    	
    }
    
    
   //自訂類別 回傳商品頁面用 
    public class product{
    	public  List<items> litems;
    	public item_picture item_picture;
    	public members member;
		public List<items> getLitems() {
			return litems;
		}
		public void setLitems(List<items> litems) {
			this.litems = litems;
		}
		public item_picture getItem_picture() {
			return item_picture;
		}
		public void setItem_picture(item_picture item_picture) {
			this.item_picture = item_picture;
		}
		public members getMember() {
			return member;
		}
		public void setMember(members member) {
			this.member = member;
		}
    	
    }
    
    
    
    
    
    
    
    
    
	
}
