package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.com.ispan.eeit48.model.item_tags;

@Repository
public interface item_tagsRepository extends JpaRepository<item_tags, Integer>{
	@Query("select it from item_tags it where it.item_tag like %:itName%")
	List<item_tags> selectItemTagsByName(@Param("itName") String itName);
}