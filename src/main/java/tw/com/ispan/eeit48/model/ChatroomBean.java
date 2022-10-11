package tw.com.ispan.eeit48.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;
import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Entity
@Data
@Table(name = "chatrooms" ,schema ="PUBLIC")
public class ChatroomBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "chatroom_id")
	private Integer chatroom_id;	
	
//	FK設定到 members 表的ID
//    @ManyToOne   
//    @JoinColumn(name="chatroom_member_id",referencedColumnName = "member_id")
//	private members member;

	@Column(name = "chatroom_member_id1")
	private Integer chatroom_member_id1 ;
	
	@Column(name = "chatroom_member_id2")
	private Integer chatroom_member_id2 ;
	
	public Integer getChatroom_id() {
		return chatroom_id;
	}

	public void setChatroom_id(Integer chatroom_id) {
		this.chatroom_id = chatroom_id;
	}

	public Integer getChatroom_member_id1() {
		return chatroom_member_id1;
	}

	public void setChatroom_member_id1(Integer chatroom_member_id1) {
		this.chatroom_member_id1 = chatroom_member_id1;
	}

	public Integer getChatroom_member_id2() {
		return chatroom_member_id2;
	}

	public void setChatroom_member_id2(Integer chatroom_member_id2) {
		this.chatroom_member_id2 = chatroom_member_id2;
	}

	public String getChatroom_create_time() {
		return chatroom_create_time;
	}

	public void setChatroom_create_time(String chatroom_create_time) {
		this.chatroom_create_time = chatroom_create_time;
	}

	@Column(name = "chatroom_create_time")
	private String chatroom_create_time;

	
}
