package tw.com.ispan.eeit48.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.model.ChatroomBean;
import tw.com.ispan.eeit48.model.MessagesBean;
import tw.com.ispan.eeit48.service.ChatroomService;
import tw.com.ispan.eeit48.service.MessagesService;

@RestController
@RequestMapping(path= {"/api/chat"})
public class chatroomsController {
	@Autowired
	private ChatroomService chatroomService;
	@Autowired
	private MessagesService messagesService;
	
	@GetMapping(path= {"/ALLchatrooms"})
	public ResponseEntity<List<ChatroomBean>> findALLChatroom(){
		//獲得全部聊天室
		List<ChatroomBean> result = chatroomService.select(null);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(path= {"/ALLmessages/{chatroomid}"})
	public ResponseEntity<?> findMessagesByPK(@PathVariable(name="chatroomid") Integer id){
		//獲得 (聊天室id) 聊天室所有訊息
		ChatroomBean bean = new ChatroomBean();
		MessagesBean mbean = new MessagesBean();
		bean.setChatroom_id(id);
		List<ChatroomBean> result = chatroomService.select(bean);
		if(result!=null && !result.isEmpty()) {
			mbean.setChatroom_id(result.get(0).getChatroom_id());
			List<MessagesBean> mresult = messagesService.select2(mbean);
			return ResponseEntity.ok(mresult);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(path= {"/ALLchatrooms/{memberid}"})
	public ResponseEntity<?> findChatroomByMemberid(@PathVariable(name="memberid") Integer id){
		//獲得 (使用者id) 所有聊天室
		ChatroomBean cbean = new ChatroomBean();
		cbean.setChatroom_member_id1(id);
		cbean.setChatroom_member_id2(id);
		List<ChatroomBean> result = chatroomService.select3(cbean);
		if(result!=null && !result.isEmpty()) {
			return ResponseEntity.ok(result);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(path= {"/ALLmessages"})
	public ResponseEntity<List<MessagesBean>> findALLMessages(){
		//獲得所有訊息
		List<MessagesBean> result = messagesService.select(null);
		return ResponseEntity.ok(result);
	}
	@PutMapping(path= {"/creatchatroom/{memberid1}/{memberid2}"})
	public ResponseEntity<ChatroomBean> createchatroom(@PathVariable(name="memberid1") Integer id1,@PathVariable(name="memberid2") Integer id2){
		//創造聊天室 id1 一定要小於 id2
		Integer number1 = id1;
		Integer number2 = id2;
		if(id1.intValue()>id2.intValue()) {
			number1=id2;
			number2=id1;
		}
		ChatroomBean cbean = new ChatroomBean();
		cbean.setChatroom_member_id1(number1);
		cbean.setChatroom_member_id2(number2);
		ChatroomBean result = chatroomService.select2(cbean);
		
		
		return ResponseEntity.ok(result);
	}
	@PostMapping
	public ResponseEntity<?> sendMessage(@RequestBody MessagesBean mbean) {
		// 更新聊天室時間
		ChatroomBean cbean = new ChatroomBean();
		cbean.setChatroom_id(mbean.getChatroom_id());
		cbean = chatroomService.select(cbean).get(0);
		cbean.setChatroom_create_time(mbean.getMessage_sent_time());
		chatroomService.select4(cbean);
		//接收訊息
//		Integer index= messagesService.total()+1;
//		mbean.setMessage_id(index);
		MessagesBean result = messagesService.insert(mbean);
		chatroomService.select2(cbean);
		if(result!=null) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	
	

}
