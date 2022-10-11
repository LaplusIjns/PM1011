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
@Data
@Table(name = "order_list" ,schema ="PUBLIC")
public class order_list {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer order_id;
	
	@Column(name = "member_id")
	private Integer member_id;
	
	@Column(name = "order_date")
	private String order_date;
	
	@Column(name = "order_item_number")
	private Integer order_item_number;	
	
	@Column(name = "order_delivery_fee")
	private Integer order_delivery_fee;
	
	@Column(name = "order_total_price")
	private Integer order_total_price;

	@Column(name = "order_payment")
	private String order_payment;
	
	@Column(name = "order_state")
	private String order_state;

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public Integer getOrder_item_number() {
		return order_item_number;
	}

	public void setOrder_item_number(Integer order_item_number) {
		this.order_item_number = order_item_number;
	}

	public Integer getOrder_delivery_fee() {
		return order_delivery_fee;
	}

	public void setOrder_delivery_fee(Integer order_delivery_fee) {
		this.order_delivery_fee = order_delivery_fee;
	}

	public Integer getOrder_total_price() {
		return order_total_price;
	}

	public void setOrder_total_price(Integer order_total_price) {
		this.order_total_price = order_total_price;
	}

	public String getOrder_payment() {
		return order_payment;
	}

	public void setOrder_payment(String order_payment) {
		this.order_payment = order_payment;
	}

	public String getOrder_state() {
		return order_state;
	}

	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	
	


}
