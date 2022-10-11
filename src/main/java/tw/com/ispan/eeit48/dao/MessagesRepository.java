package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.com.ispan.eeit48.model.MessagesBean;
@Repository
public interface MessagesRepository  extends JpaRepository<MessagesBean, Integer>{

	@Query(value = "select * from messages m where m.chatroom_id=?1", nativeQuery = true)
	List<MessagesBean> findByChatroomid(Integer chatroom_id);
}

