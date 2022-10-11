package tw.com.ispan.eeit48.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;


@Component
@Entity

@Table(name = "members" ,schema ="PUBLIC")
public class members{
	public String getSeven11_address() {
		return seven11_address;
	}


	public void setSeven11_address(String seven11_address) {
		this.seven11_address = seven11_address;
	}


	public String getOk_address() {
		return ok_address;
	}


	public void setOk_address(String ok_address) {
		this.ok_address = ok_address;
	}


	public String getFamily_address() {
		return family_address;
	}


	public void setFamily_address(String family_address) {
		this.family_address = family_address;
	}


	public String getBank_account() {
		return bank_account;
	}


	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
    private Integer member_id;

	@Column(name = "member_account")
	private String  member_account;
	
	@Column(name = "member_password")
	private String member_password;

	@Column(name = "member_email")
	private String member_email;
	
	@Column(name = "member_phonenumber")
	private String member_phonenumber;
	
	@Column(name = "member_birthday")
	private String member_birthday;
	
	@Column(name = "member_region")
	private String  member_region;
	
//	型別可能出錯	
	@Column(name = "member_rate")
	private Double member_rate;
	
	@Column(name = "member_icon")
	private String  member_icon;
	
	@Column(name = "member_nickname")
	private String  member_nickname;
	
	@Column(name = "member_introduction")
	private String  member_introduction;
	
	@Column(name = "member_rank")
	private String member_rank;
	
	@Column(name = "member_ban_date")
	private String member_ban_date;
	
	@Column(name = "member_token")
	private String member_token;		
	
	@Column(name = "member_balance")
	private Integer member_balance;

	
	@Column(name = "account_create_time")
	private String account_create_time;
	
	
	@Column(name = "last_login_time")
	private String last_login_time;
	
	@Column(name = "seven11_address")
	private String seven11_address;

	
	@Column(name = "ok_address")
	private String 	ok_address;

	
	@Column(name = "family_address")
	private String family_address;

	
	@Column(name = "bank_account")
	private String 	bank_account;



	public Integer getMember_id() {
		return member_id;
	}


	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}


	public String getMember_account() {
		return member_account;
	}


	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}


	public String getMember_password() {
		return member_password;
	}


	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}


	public String getMember_email() {
		return member_email;
	}


	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}


	public String getMember_phonenumber() {
		return member_phonenumber;
	}


	public void setMember_phonenumber(String member_phonenumber) {
		this.member_phonenumber = member_phonenumber;
	}


	public String getMember_birthday() {
		return member_birthday;
	}


	public void setMember_birthday(String member_birthday) {
		this.member_birthday = member_birthday;
	}


	public String getMember_region() {
		return member_region;
	}


	public void setMember_region(String member_region) {
		this.member_region = member_region;
	}


	public Double getMember_rate() {
		return member_rate;
	}


	public void setMember_rate(Double member_rate) {
		this.member_rate = member_rate;
	}


	public String getMember_icon() {
		return member_icon;
	}


	public void setMember_icon(String member_icon) {
		this.member_icon = member_icon;
	}


	public String getMember_nickname() {
		return member_nickname;
	}


	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}


	public String getMember_introduction() {
		return member_introduction;
	}


	public void setMember_introduction(String member_introduction) {
		this.member_introduction = member_introduction;
	}


	public String getMember_rank() {
		return member_rank;
	}


	public void setMember_rank(String member_rank) {
		this.member_rank = member_rank;
	}


	public String getMember_ban_date() {
		return member_ban_date;
	}


	public void setMember_ban_date(String member_ban_date) {
		this.member_ban_date = member_ban_date;
	}


	public String getMember_token() {
		return member_token;
	}


	public void setMember_token(String member_token) {
		this.member_token = member_token;
	}


	public Integer getMember_balance() {
		return member_balance;
	}


	public void setMember_balance(Integer member_balance) {
		this.member_balance = member_balance;
	}


	public String getAccount_create_time() {
		return account_create_time;
	}


	public void setAccount_create_time(String account_create_time) {
		this.account_create_time = account_create_time;
	}


	public String getLast_login_time() {
		return last_login_time;
	}


	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}

	
	
	
}
