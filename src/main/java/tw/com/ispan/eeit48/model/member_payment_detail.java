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
@Table(name = "member_payment_detail" ,schema ="PUBLIC")
public class member_payment_detail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_detail_id")
	private Integer payment_detail_id;

	@Column(name = "member_id")
	private Integer member_id;

	@Column(name = "member_firstname")
	private String member_firstname;

	@Column(name = "member_lastname")
	private String member_lastname;
	
	@Column(name = "member_address")
	private String member_address;

	@Column(name = "creditcard_number")
	private String creditcard_number;

	@Column(name = "creditcard_date")
	private String creditcard_date;
	
	@Column(name = "creditcard_security_code")
	private Integer creditcard_security_code;

	public Integer getPayment_detail_id() {
		return payment_detail_id;
	}

	public void setPayment_detail_id(Integer payment_detail_id) {
		this.payment_detail_id = payment_detail_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public String getMember_firstname() {
		return member_firstname;
	}

	public void setMember_firstname(String member_firstname) {
		this.member_firstname = member_firstname;
	}

	public String getMember_lastname() {
		return member_lastname;
	}

	public void setMember_lastname(String member_lastname) {
		this.member_lastname = member_lastname;
	}

	public String getMember_address() {
		return member_address;
	}

	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}

	public String getCreditcard_number() {
		return creditcard_number;
	}

	public void setCreditcard_number(String creditcard_number) {
		this.creditcard_number = creditcard_number;
	}

	public String getCreditcard_date() {
		return creditcard_date;
	}

	public void setCreditcard_date(String creditcard_date) {
		this.creditcard_date = creditcard_date;
	}

	public Integer getCreditcard_security_code() {
		return creditcard_security_code;
	}

	public void setCreditcard_security_code(Integer creditcard_security_code) {
		this.creditcard_security_code = creditcard_security_code;
	}
	
	
	

	



}
