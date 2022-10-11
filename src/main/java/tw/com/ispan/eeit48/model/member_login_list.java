package tw.com.ispan.eeit48.model;


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
@Table(name = "member_login_list" ,schema ="PUBLIC")
public class member_login_list {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "login_list_id")
	private Integer login_list_id;
	
	@Column(name = "member_id")
	private Integer member_id;
	
	@Column(name = "member_login_time")
	private String member_login_time;

	public Integer getLogin_list_id() {
		return login_list_id;
	}

	public void setLogin_list_id(Integer login_list_id) {
		this.login_list_id = login_list_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public String getMember_login_date() {
		return member_login_time;
	}

	public void setMember_login_date(String member_login_time) {
		this.member_login_time = member_login_time;
	}
	
	
	
	
	
	
	
	
	
}
