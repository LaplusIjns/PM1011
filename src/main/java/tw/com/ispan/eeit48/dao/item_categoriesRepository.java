package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.com.ispan.eeit48.model.item_categories;

@Repository
public interface item_categoriesRepository  extends JpaRepository<item_categories, Integer>{

	@Query("select ic from item_categories ic where ic.category_name like %:icName%")
	List<item_categories> selectItemCategoriesByoneName(@Param("icName") String icName);
	
	@Query("select ic from item_categories ic where ic.category_name like :icName02% and  ic.category_name like %:icName01")
	List<item_categories> selectItemCategoriesBytwoNames(@Param("icName01") String icName01, @Param("icName02") String icName02);
	
	@Query("select ic.category_name from item_categories ic where ic.category_id = :icID")
	String selectItemCategoriesByID(@Param("icID") Integer icID);
}