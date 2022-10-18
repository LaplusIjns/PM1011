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
@Table(name = "wishlists" ,schema ="PUBLIC")
public class wishlists {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wishlist_id")
	private Integer wishlist_id;	

	@Column(name = "member_id")
	private Integer member_id;
	
	@Column(name = "item_name")
	private String item_name;	
	
	@Column(name = "item_id")
	private Integer item_id;	

	@Column(name = "category_id")
	private Integer category_id;
	
	@Column(name = "item_description")
	private String item_description;
	
	
	@Column(name = "expect_price")
	private Integer expect_price;
	
	
	@Column(name = "item_photo_url")
	private String  item_photo_url;
	
	@Column(name = "item_likes")
	private Integer item_likes;
	
	@Column(name = "item_comment")
	private String 	item_comment;

	@Column(name = "addwish_date")
	private String 	addwish_date;
	
	public String getAddwish_date() {
		return addwish_date;
	}

	public void setAddwish_date(String addwish_date) {
		this.addwish_date = addwish_date;
	}

	public Integer getWishlist_id() {
		return wishlist_id;
	}

	public void setWishlist_id(Integer wishlist_id) {
		this.wishlist_id = wishlist_id;
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

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public Integer getExpect_price() {
		return expect_price;
	}

	public void setExpect_price(Integer expect_price) {
		this.expect_price = expect_price;
	}

	public String getItem_photo_url() {
		return item_photo_url;
	}

	public void setItem_photo_url(String item_photo_url) {
		this.item_photo_url = item_photo_url;
	}

	public Integer getItem_likes() {
		return item_likes;
	}

	public void setItem_likes(Integer item_likes) {
		this.item_likes = item_likes;
	}

	public String getItem_comment() {
		return item_comment;
	}

	public void setItem_comment(String item_comment) {
		this.item_comment = item_comment;
	}
	
	
	
}
