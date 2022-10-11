package tw.com.ispan.eeit48.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import tw.com.ispan.eeit48.model.ChatroomBean;
import tw.com.ispan.eeit48.model.MessagesBean;
import tw.com.ispan.eeit48.service.ChatroomService;
import tw.com.ispan.eeit48.service.MessagesService;

@Controller
public class WebSocketChatController {
	@Autowired
	private MessagesService messagesService;
	@Autowired
	private ChatroomService chatroomService;
	
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public MessagesBean sendMessage(@Payload MessagesBean mbean) {
    	System.out.println("hello123");
    	//更新聊天室
    	ChatroomBean cbean = new ChatroomBean();
		cbean.setChatroom_id(mbean.getChatroom_id());
		cbean = chatroomService.select(cbean).get(0);
		cbean.setChatroom_create_time(mbean.getMessage_sent_time());
		chatroomService.select4(cbean);
		chatroomService.select2(cbean);
		System.out.println("hello");
		
//		Integer index= messagesService.total()+1;
//		mbean.setMessage_id(index);
		messagesService.insert(mbean);
//		
//		MessagesBean mbean1 = new MessagesBean();
//		mbean1.setMember_id(mbean.getMember_id());
//		mbean1.setChatroom_id(mbean.getChatroom_id());
//		mbean1.setMessage_content(mbean.getMessage_content());
//		mbean1.setMessage_sent_time(mbean.getMessage_sent_time());
//    	Integer index= messagesService.total()+1;
//    	mbean1.setMessage_id(index);
//    	messagesService.insert(mbean);
//		chatroomService.select2(cbean);
    	
    	
    	
		
//    	System.out.println(messagesBean.getMessage_content());
//    	System.out.println(messagesBean.getMessage_sent_time());
    	
        return mbean;
    }

//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(@Payload ChatMessage chatMessage,
//                               SimpMessageHeaderAccessor headerAccessor) {
//        // Add username in web socket session
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        return chatMessage;
//    }

}
