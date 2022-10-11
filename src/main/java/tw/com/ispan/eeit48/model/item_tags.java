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
@Table(name = "item_tags" ,schema ="PUBLIC")
public class item_tags {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private Integer tag_id;
	
	@Column(name = "item_id")
	private Integer  item_id;
	
	@Column(name = "item_tag")
	private String  item_tag;

	public Integer getTag_id() {
		return tag_id;
	}

	public void setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String getItem_tag() {
		return item_tag;
	}

	public void setItem_tag(String item_tag) {
		this.item_tag = item_tag;
	}
	
	
	
}
