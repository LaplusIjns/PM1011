package tw.com.ispan.eeit48.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.com.ispan.eeit48.model.ChatroomBean;
import tw.com.ispan.eeit48.model.MessagesBean;


@Repository
public interface ChatroomRepository extends JpaRepository<ChatroomBean, Integer>{
	
	@Query(value = "select * from chatrooms c where c.chatroom_member_id1=?1 and c.chatroom_member_id2=?2", nativeQuery = true)
	ChatroomBean findBymemberid12(Integer chatroom_member_id1,Integer chatroom_member_id2);
	
	@Query(value = "select * from chatrooms c where c.chatroom_member_id1=?1 or c.chatroom_member_id2=?2 ORDER BY STR_TO_DATE( c.chatroom_create_time,\"%Y/%m/%d %T\") DESC", nativeQuery = true)
	List<ChatroomBean> findRoomBymemberid(Integer chatroom_member_id1,Integer chatroom_member_id2);

}
