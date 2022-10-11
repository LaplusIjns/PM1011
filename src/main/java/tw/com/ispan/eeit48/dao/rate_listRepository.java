package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.com.ispan.eeit48.model.members;
import tw.com.ispan.eeit48.model.rate_list;
@Repository
public interface rate_listRepository extends JpaRepository<rate_list, Integer>{
	@Query(value = "select * from rate_list where rated_member_id = ?1", nativeQuery = true)
	List<rate_list> queryByrated_member(Integer rated_member_id);
	
}