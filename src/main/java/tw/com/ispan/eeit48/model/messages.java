package tw.com.ispan.eeit48.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Entity
@Data
@Table(name = "messages" ,schema ="PUBLIC")
public class messages{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private Integer message_id;

	@Column(name = "chatroom_id")
	private Integer chatroom_id;

	@Column(name = "member_id ")
	private Integer member_id ;

	@Column(name = "message_content")
	private String message_content;
	
	@Column(name = "message_sent_time")
	private String message_sent_time;

	public Integer getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public Integer getChatroom_id() {
		return chatroom_id;
	}

	public void setChatroom_id(Integer chatroom_id) {
		this.chatroom_id = chatroom_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public String getMessage_sent_time() {
		return message_sent_time;
	}

	public void setMessage_sent_time(String message_sent_time) {
		this.message_sent_time = message_sent_time;
	}

	
	
	

	



}
