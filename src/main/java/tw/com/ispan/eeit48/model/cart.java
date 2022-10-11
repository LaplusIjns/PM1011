package tw.com.ispan.eeit48.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;



@Component
@Entity
@Table(name = "cart" ,schema ="PUBLIC")
public class cart {
	public Integer getCart_id() {
		return cart_id;
	}

	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Integer getItems_id() {
		return items_id;
	}

	public void setItems_id(Integer items_id) {
		this.items_id = items_id;
	}

	public String getOwner_account() {
		return owner_account;
	}

	public void setOwner_account(String owner_account) {
		this.owner_account = owner_account;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "cart_id")
	private Integer cart_id;	

	@Column(name = "member_id")
	private Integer member_id ;
	
	@Column(name = "items_id")
	private Integer items_id;
	
	@Column(name = "owner_account")
	private String owner_account;	
	
}
