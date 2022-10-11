package tw.com.ispan.eeit48.model;

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
@Table(name = "rate_list" ,schema ="PUBLIC")
public class rate_list {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rate_id")
	private Integer rate_id;
	

	@Column(name = "order_id")
	private Integer order_id;

	@Column(name = "rated_member_id ")
	private Integer rated_member_id ;	

	@Column(name = "rated_item_id")
	private Integer rated_item_id;

	@Column(name = "rate_member_id")
	private Integer rate_member_id;


	@Column(name = "rate_description")
	private String rate_description;
	
	@Column(name = "rate_grade")
	private Integer rate_grade;

	public Integer getRate_id() {
		return rate_id;
	}

	public void setRate_id(Integer rate_id) {
		this.rate_id = rate_id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getRated_member_id() {
		return rated_member_id;
	}

	public void setRated_member_id(Integer rated_member_id) {
		this.rated_member_id = rated_member_id;
	}

	public Integer getRated_item_id() {
		return rated_item_id;
	}

	public void setRated_item_id(Integer rated_item_id) {
		this.rated_item_id = rated_item_id;
	}

	public Integer getRate_member_id() {
		return rate_member_id;
	}

	public void setRate_member_id(Integer rate_member_id) {
		this.rate_member_id = rate_member_id;
	}

	public String getRate_description() {
		return rate_description;
	}

	public void setRate_description(String rate_description) {
		this.rate_description = rate_description;
	}

	public Integer getRate_grade() {
		return rate_grade;
	}

	public void setRate_grade(Integer rate_grade) {
		this.rate_grade = rate_grade;
	}


	
}
