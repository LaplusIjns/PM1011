package tw.com.ispan.eeit48.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.ChatroomRepository;
import tw.com.ispan.eeit48.model.ChatroomBean;

@Service
@Transactional
public class ChatroomService {
	@Autowired
	private ChatroomRepository chatroomRepository;
	
	public List<ChatroomBean> select(ChatroomBean bean){
		// 選特定聊天室 或   全部聊天室
		List<ChatroomBean> result = null;
		if(bean!=null && bean.getChatroom_id()!=null && !bean.getChatroom_id().equals(0)) {
			Optional<ChatroomBean> data = chatroomRepository.findById(bean.getChatroom_id());
			if(data.isPresent()) {
				result = new ArrayList<ChatroomBean>();
				result.add(data.get());
			}
		}else {
				result = chatroomRepository.findAll();
			}
		return result;
	}
	
	public ChatroomBean select2(ChatroomBean bean){
		// 選特定聊天室 或    創立聊天室
		ChatroomBean result = null;
		if(bean!=null && bean.getChatroom_member_id1()!=null && bean.getChatroom_member_id2()!=null) {
			ChatroomBean data = chatroomRepository.findBymemberid12(bean.getChatroom_member_id1(),bean.getChatroom_member_id2());
			if(data!=null) {
				result = data;
			}else {
				String x=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
				bean.setChatroom_create_time(x);
				return chatroomRepository.save(bean);
				
			}
		}
		return result;
	}
	public List<ChatroomBean> select3(ChatroomBean bean){
		// 選特定聊天室 或全部聊天室
		List<ChatroomBean> result = null;
		if(bean!=null ) {
			List<ChatroomBean> data = chatroomRepository.findRoomBymemberid(bean.getChatroom_member_id1(),bean.getChatroom_member_id2());
			result=data;

		}else {
			return null;
		}
		
		return result;
	}

	public ChatroomBean select4(ChatroomBean bean){
		// 刷新時間
		ChatroomBean result = null;
		if(bean!=null && bean.getChatroom_id()!=null && bean.getChatroom_create_time()!=null) {
			ChatroomBean data = chatroomRepository.saveAndFlush(bean);
			if(data!=null) {
				result = data;
			}
		}
		return result;
	}
	

	
	
}
