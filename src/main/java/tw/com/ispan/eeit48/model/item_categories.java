package tw.com.ispan.eeit48.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Entity

@Table(name = "item_categories" ,schema ="PUBLIC")
public class item_categories {
	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "category_id")
    private Integer category_id;
	
	@Column(name = "category_name")
	private String category_name;


	 
}
