package tw.com.ispan.eeit48.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.MessagesRepository;
import tw.com.ispan.eeit48.model.ChatroomBean;
import tw.com.ispan.eeit48.model.MessagesBean;

@Service
@Transactional
public class MessagesService {
	@Autowired
	private MessagesRepository messagesRepository;
	
	public List<MessagesBean> select(MessagesBean bean){
		//獲得全部 或者 特定訊息
		List<MessagesBean> result = null;
		if(bean!=null && bean.getChatroom_id()!=null && !bean.getChatroom_id().equals(0)) {
			Optional<MessagesBean> data = messagesRepository.findById(bean.getMessage_id());
			if(data.isPresent()) {
				result = new ArrayList<MessagesBean>();
				result.add(data.get());
			}
		}else {
				result = messagesRepository.findAll();
			}
		
		return result;
	}
	
	public List<MessagesBean> select2(MessagesBean bean){
		//獲得特定聊天室所有訊息
		List<MessagesBean> result = null;
		if(bean!=null && bean.getChatroom_id()!=null && !bean.getChatroom_id().equals(0)) {
			List<MessagesBean> data = messagesRepository.findByChatroomid(bean.getChatroom_id());
			result = data;
		}else {
				result = messagesRepository.findAll();
			}
		return result;
	}
	
	public MessagesBean insert(MessagesBean mbean) {
		MessagesBean result = null;
		if(mbean!=null ) {
//			if(!messagesRepository.existsById(mbean.getMessage_id())) {
				return messagesRepository.save(mbean);
//			}
		}
		return result;
	}
	public Integer total() {

		return (int) messagesRepository.count();
	}


}
