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

@Table(name = "item_picture" ,schema ="PUBLIC")
public class item_picture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "picture_id")
	private Integer picture_id;
	
	@Column(name = "item_id")
	private Integer item_id;
	
	@Column(name = "picture_url")
	private String  picture_url;

	public Integer getPicture_id() {
		return picture_id;
	}

	public void setPicture_id(Integer picture_id) {
		this.picture_id = picture_id;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	
	
	
}
