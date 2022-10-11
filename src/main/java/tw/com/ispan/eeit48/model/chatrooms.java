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
public class chatrooms {
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
	
	@Column(name = "chatroom_create_time")
	private String chatroom_create_time;

	
}
