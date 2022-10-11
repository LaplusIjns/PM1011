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
@Table(name = "items" ,schema ="PUBLIC")
public class items{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Integer item_id;

	@Column(name = "member_id")
	private Integer member_id ;
	
	@Column(name = "item_name")
	private String item_name;

	@Column(name = "item_description")
	private String item_description;
	
	@Column(name = "item_state")
	private String item_state ;
	
	@Column(name = "item_date")
	private String item_date;

	@Column(name = "item_price")
	private Integer item_price;
	
	@Column(name = "item_times_rent_out")
	private Integer item_times_rent_out;

	@Column(name = "category_id")
	private Integer category_id;

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public String getItem_state() {
		return item_state;
	}

	public void setItem_state(String item_state) {
		this.item_state = item_state;
	}

	public String getItem_date() {
		return item_date;
	}

	public void setItem_date(String item_date) {
		this.item_date = item_date;
	}

	public Integer getItem_price() {
		return item_price;
	}

	public void setItem_price(Integer item_price) {
		this.item_price = item_price;
	}

	public Integer getItem_times_rent_out() {
		return item_times_rent_out;
	}

	public void setItem_times_rent_out(Integer item_times_rent_out) {
		this.item_times_rent_out = item_times_rent_out;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	

}
