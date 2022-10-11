package tw.com.ispan.eeit48.controller;

import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tw.com.ispan.eeit48.controller.membersController.getRate;
import tw.com.ispan.eeit48.dao.itemsRepository;
import tw.com.ispan.eeit48.dao.membersRepository;
import tw.com.ispan.eeit48.dao.rate_listRepository;
import tw.com.ispan.eeit48.model.ChatroomBean;
import tw.com.ispan.eeit48.model.MessagesBean;
import tw.com.ispan.eeit48.model.items;
import tw.com.ispan.eeit48.model.members;
import tw.com.ispan.eeit48.model.rate_list;

//跨域需求要加這個Annoation
@CrossOrigin
@RestController
@RequestMapping

public class membersController {
	@Autowired
	membersRepository membersRepository;
	@Autowired
	rate_listRepository rateRepository;
//	@Autowired
//	members member,member1,member2;
//	@Autowired
//	rate_list rateList;
//	@Autowired
//	HttpSession localSession;
	@Autowired
	JavaMailSender mailSender;
	String number = "";
	@Autowired
	itemsRepository itemsRepository;
	@Autowired
	private ServerProperties serverProperties;

//查看特定人id檔案
	@PostMapping(path = {"/findmember_id/{memberid}"})
    public ResponseEntity<?> findBymember_id(@PathVariable(name="memberid") Integer id) {
	    //獲得特定使用者資料
	    members bean = new members();
        bean.setMember_id(id);
        List<members> result = membersRepository.findBymember_id(bean.getMember_id());
        if(result!=null && !result.isEmpty()) {
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.notFound().build();
        }
    }	
	//查看特定人account檔案
    @PostMapping(path = {"/findmember_account/{accountname}"})
    public ResponseEntity<?> findBymember_account(@PathVariable(name="accountname") String id) {
        //獲得特定使用者資料
        members bean = new members();
        bean.setMember_account(id);
        List<members> result = membersRepository.findBymember_account(bean.getMember_account());
        if(result!=null && !result.isEmpty()) {
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.notFound().build();
        }
    }	
  //搜尋特定人評價列表
    @CrossOrigin
    @PostMapping(path = {"/getRate/{accountid}"})
    public getRate getRate2(@PathVariable(name="accountid") Integer id) {
        members member = new members();
        getRate gr=new getRate();
        member.setMember_id(id);
        member = membersRepository.getById(id);
        LinkedList<members> lm = new LinkedList<members>();
        LinkedList<items> li = new LinkedList<items>();
        if(member==null) {
            return null;
        }
            Integer rated_id = member.getMember_id();   
            List<rate_list> rate = rateRepository.queryByrated_member(rated_id);
            System.out.println(rate);
            rate.forEach((e)->{ 
                lm.add(membersRepository.queryByrate_id(e.getRate_member_id()));
                li.add(itemsRepository.queryByrated_item_id(e.getRated_item_id()));
                System.out.println(membersRepository.queryByrate_id(e.getRate_id()));
            });
            gr.setLinkedListmembers(lm);
            gr.setListrate_list(rate);
            gr.setLinkedListitems(li);
            System.out.println(gr);
             return gr;
    }
    
    
    
//members表新增銀行帳戶只能有一筆
	  @CrossOrigin
    @PostMapping(path = {"/addNewBankAccount"})
    public void addNewBankAccount(members members,String bank_account_num,HttpSession session) {
    	Integer bankAccountNum=Integer.parseInt(bank_account_num);
    	members member = new members();
    	member = (members)session.getAttribute("member");
    	Integer member_id=member.getMember_id();
    	membersRepository.updateBank_account(bankAccountNum,member_id);
    }
//刪除銀行帳戶 	
	@CrossOrigin
    @PostMapping(path = {"/deleteBankAccount"})
    public void deleteBankAccount(members members,String bank_account_num,HttpSession session) {
		members member = new members();
		member = (members) session.getAttribute("member");
    	Integer member_id=member.getMember_id();
    	
   //測試用 	Integer member_id =2
    	
    	membersRepository.deletebankaccount(member_id);
    }
//儲存便利商店資料
@CrossOrigin
@PostMapping(path = {"/addNewConvenientStore"})
	 public void addNewConvenientStore(String seven11_address,String ok_address,String family_address,HttpSession session) {
	members member = new members();
	member = (members) session.getAttribute("member");
	Integer memberID=member.getMember_id();
	
//	var memberID=2;
	System.out.println(seven11_address);
		membersRepository.updateConvenientStore(seven11_address,ok_address,family_address,memberID);
}		



BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder() ;
Date date = new Date();
SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//帳號建立
@CrossOrigin
@PostMapping(path = {"/doRegister"})
public String SaveAndCheckAccount(members createMember) {
//account password email要和前端AJAX傳來的KEY名稱一致    
//驗證帳號是否重複 重複會SYSOUT account exist    
	if(membersRepository.queryBymember_account(createMember.getMember_account())!=null) {
        return "此帳號已被使用";
}else if(createMember.getMember_account().equals("") || createMember.getMember_password().equals("") 
		|| createMember.getMember_email().equals("")){
		return "帳號、密碼、信箱都不能空白";
}else{      
			members member = new members();
			member.setMember_account(createMember.getMember_account());
	        member.setMember_email(createMember.getMember_email());
	        //將user輸入的密碼進行加密
	        member.setMember_password(bcpe.encode(createMember.getMember_password()));
//	        member.setMember_password(createMember.getMember_password());
	        //寫入創帳號日期
	        member.setAccount_create_time(dd.format(date));
	        member.setMember_rank("一般");
	        membersRepository.saveAndFlush(member);
	        System.out.println("insertOK");
	        return "帳號創立成功！請重新登入";
	    }
}
    
//登入	    
@CrossOrigin
@PostMapping(path = {"/loginRegister"})
public String LoginAndCheckAccount( members loginMember,HttpSession session) {
//在接受方法上宣告HttpSession session，並將這個session存到localSession裡面，後面都用localSession呼叫參數 	
//account password要和前端AJAX傳來的KEY名稱一致    
//驗證帳號密碼是否正確
	members member = new members();
	member = membersRepository.queryBymember_account(loginMember.getMember_account());
	if(loginMember.getMember_account().equals("")) {
        return "請輸入帳號與密碼";
}else if(member == null){
		return "帳號或密碼輸入錯誤";
}else{   
//	Boolean	isright =loginMember.getMember_password().equals(member.getMember_password()) ;
	Boolean	isright = bcpe.matches(loginMember.getMember_password(), member.getMember_password());
	if(isright) {
	System.out.println("Login OK");
	member.setLast_login_time(dd.format(date));
	membersRepository.saveAndFlush(member);
//	localSession = session;
//	localSession.setAttribute("member", member);
	session.setAttribute("member", member);
	if(member.getMember_nickname()!=null) {
	return "歡迎回來 " + member.getMember_nickname();}
	else {
	    return "歡迎回來 " + member.getMember_account();
	}
			}
			System.out.println("帳號或密碼輸入錯誤");
	        return "帳號或密碼輸入錯誤";
	    }
	}


//前端頁面跳轉時確認登入狀況
@CrossOrigin
@GetMapping(path = {"/getRegister"})
public members GetAccount(HttpSession session) {
	members member = new members();
	members member1 = new members();
//	member = (members) localSession.getAttribute("member");
	member = (members) session.getAttribute("member");
	if(member==null) {
	member1.setMember_id(0);
		return member1;
	}
return  member;
}
    
//登出
@CrossOrigin
@GetMapping(path = {"/logoutRegister"})
public String LogoutAccount(HttpSession session) {
//	localSession.removeAttribute("member");
	session.removeAttribute("member");
return  "帳號已登出";

}
    
//更新帳號資料
@CrossOrigin
@PostMapping(path = {"/changeRegister"})
public String changeAccount(@RequestParam("uploadImg") MultipartFile file,members changeMember,HttpSession session) {
	members member = new members();
//	member = (members) localSession.getAttribute("member");
	member = (members) session.getAttribute("member");
	if (member == null) {
        return "請先登入帳號";
    }
	 String fileOldName = file.getOriginalFilename();
	 System.out.println(fileOldName);
	 File p = new File("").getAbsoluteFile();
	 System.out.println(p.getAbsolutePath());
	 String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(date) + "_" + fileOldName;
//     String path = "C:/JavaFramework/eclipse-workspace/20220924/src/main/webapp/img/" + fileName;
	 String path =p+ "/src/main/webapp/img/" + fileName;
     File localSave = new File(path);
     if (!localSave.getParentFile().exists()) {
    	 localSave.getParentFile().mkdir();
     }
     try {
         //上傳檔案
         file.transferTo(localSave); //儲存檔案
//         url = "http://188.131.163.160:8087" + "/file/image/" + fileName;//外部訪問路徑，對應本地的c:/fileupload
    } catch (Exception e) {
      System.out.println(e.toString());
     }
     
     member.setMember_nickname(changeMember.getMember_nickname());
     member.setMember_region(changeMember.getMember_region());
     member.setMember_introduction(changeMember.getMember_introduction());
     if(fileOldName != "") { member.setMember_icon(fileName);}
     member.setMember_birthday(changeMember.getMember_birthday());
	membersRepository.saveAndFlush(member);
//	localSession.setAttribute("member", member);
	session.setAttribute("member", member);
return  "帳號資料已更新";

}
    
//忘記密碼
@CrossOrigin
@PostMapping(path = {"/forgetRegister"})
public String forgetAccount(members forgetMember) {
	members member = new members();
	member = membersRepository.queryBymember_account(forgetMember.getMember_account());
	if(member == null) {	
		return  "帳號輸入錯誤";
}else {
	int n = 6;
	int[] Lottery = new int[n];
	int temp;
	boolean isRepeat;
	for(int i=0;i<Lottery.length;i++) {
			do {
				temp = (int)((Math.random()*48+1));				
				// 檢查機制
				 isRepeat = false;
				 	for (int j = 0; j < i; j++) {
				 			if (temp == Lottery[j]) {
						// 發生重複了
				 				isRepeat = true;
				 				break;
				 				}
				 			}	
			}while(isRepeat);
		Lottery[i] = temp;			
		}
	
	int a = 0;
	for(int num :Lottery) {
		a++;
		if(a<6) {
			number	+= num;				
		}
	}
	SimpleMailMessage message = new SimpleMailMessage();  
	  
	  message.setTo(member.getMember_email(),"tel2855973@gmail.com");
	  //前面信箱為收件人，後面信箱為寄件人
	  message.setSubject("口袋漫遊網：密碼遺失驗證信");
	  int port = serverProperties.getPort();
	  message.setText("您的驗證碼為:" + number + "。\n" + "請點擊以下網站輸入驗證碼。\n" + "http://127.0.0.1:"+port+"/html/check_code.html" );
	  mailSender.send(message);
	  System.out.println(number);
	return  "請至信箱確認驗證碼，並點擊網址輸入驗證碼與新的密碼";
	}
}


//確認驗證碼
@CrossOrigin
@PostMapping(path = {"/checkCode"})
public String checkCode(String member_account,String checkCode,String newPassword) {
	members member = new members();
	if(number.equals(checkCode)) {
		member = membersRepository.queryBymember_account(member_account);
		member.setMember_password(bcpe.encode(newPassword));
		membersRepository.saveAndFlush(member);
		number = "";
		 return  "帳號資料已更新，請用新密碼重新登入";
	}else {
	 return  "驗證碼輸入錯誤";
	}
}


//搜尋評價列表
@CrossOrigin
@PostMapping(path = {"/getRate"})
public getRate getRate(HttpSession session) {
	members member = new members();
	getRate gr=new getRate();
//	member = (members) localSession.getAttribute("member");
	member = (members) session.getAttribute("member");
	LinkedList<members> lm = new LinkedList<members>();
	LinkedList<items> li = new LinkedList<items>();
	if(member==null) {
		return null;
	}
		Integer rated_id = member.getMember_id();	
		List<rate_list> rate = rateRepository.queryByrated_member(rated_id);
		System.out.println(rate);
		rate.forEach((e)->{	
			lm.add(membersRepository.queryByrate_id(e.getRate_member_id()));
			li.add(itemsRepository.queryByrated_item_id(e.getRated_item_id()));
			System.out.println(membersRepository.queryByrate_id(e.getRate_id()));
		});
		gr.setLinkedListmembers(lm);
		gr.setListrate_list(rate);
		gr.setLinkedListitems(li);
		System.out.println(gr);
		 return gr;
}


public class getRate{
	List<rate_list> Listrate_list;
	LinkedList<members> LinkedListmembers;
	LinkedList<items> LinkedListitems;
	public LinkedList<items> getLinkedListitems() {
		return LinkedListitems;
	}
	public void setLinkedListitems(LinkedList<items> linkedListitems) {
		LinkedListitems = linkedListitems;
	}
	public List<rate_list> getListrate_list() {
		return Listrate_list;
	}
	public void setListrate_list(List<rate_list> listrate_list) {
		Listrate_list = listrate_list;
	}
	public LinkedList<members> getLinkedListmembers() {
		return LinkedListmembers;
	}
	public void setLinkedListmembers(LinkedList<members> linkedListmembers) {
		LinkedListmembers = linkedListmembers;
	}
	
}
    
}