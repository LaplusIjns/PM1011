package tw.com.ispan.eeit48.model;

import java.sql.Timestamp;
import java.time.LocalDate;

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

@Table(name = "order_details" ,schema ="PUBLIC")
public class order_details {

	public String getOrder_detail_state() {
		return order_detail_state;
	}

	public void setOrder_detail_state(String order_detail_state) {
		this.order_detail_state = order_detail_state;
	}

	public String getOrder_payment() {
		return order_payment;
	}

	public void setOrder_payment(String order_payment) {
		this.order_payment = order_payment;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private Integer order_detail_id;	
	
	@Column(name = "order_id")
	private Integer order_id;
	
	@Column(name = "item_id")
	private Integer item_id;
	
	@Column(name = "item_receive_date")
	private String item_receive_date;
	
	@Column(name = "deliver_way")
	private String deliver_way;
	
	
	@Column(name = "deliver_place")
	private String deliver_place;
	
	@Column(name = "item_return_date")
	private String item_return_date;
	
	
	@Column(name = "return_way")
	private String 	return_way;	
	
	@Column(name = "return_place")
	private String return_place;
	
	@Column(name = "item_price")
	private Integer item_price;
	
	@Column(name = "is_violate")
	private Boolean is_violate;
	
	@Column(name = "violate_type")
	private String violate_type;

	@Column(name = "order_detail_state")
	private String order_detail_state;
	
	
	@Column(name = "order_payment")
	private String order_payment;
	
	
	
	public Integer getOrder_detail_id() {
		return order_detail_id;
	}

	public void setOrder_detail_id(Integer order_detail_id) {
		this.order_detail_id = order_detail_id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String getItem_receive_date() {
		return item_receive_date;
	}

	public void setItem_receive_date(String item_receive_date) {
		this.item_receive_date = item_receive_date;
	}

	public String getDeliver_way() {
		return deliver_way;
	}

	public void setDeliver_way(String deliver_way) {
		this.deliver_way = deliver_way;
	}

	public String getDeliver_place() {
		return deliver_place;
	}

	public void setDeliver_place(String deliver_place) {
		this.deliver_place = deliver_place;
	}

	public String getItem_return_date() {
		return item_return_date;
	}

	public void setItem_return_date(String item_return_date) {
		this.item_return_date = item_return_date;
	}

	public String getReturn_way() {
		return return_way;
	}

	public void setReturn_way(String return_way) {
		this.return_way = return_way;
	}

	public String getReturn_place() {
		return return_place;
	}

	public void setReturn_place(String return_place) {
		this.return_place = return_place;
	}

	public Integer getItem_price() {
		return item_price;
	}

	public void setItem_price(Integer item_price) {
		this.item_price = item_price;
	}

	public Boolean getIs_violate() {
		return is_violate;
	}

	public void setIs_violate(Boolean is_violate) {
		this.is_violate = is_violate;
	}

	public String getViolate_type() {
		return violate_type;
	}

	public void setViolate_type(String violate_type) {
		this.violate_type = violate_type;
	}
	
	
}
