package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.com.ispan.eeit48.model.order_list;
@Repository
public interface order_listRepository extends JpaRepository<order_list, Integer>{
	 @Query(value = "select * from order_list where order_id = ?1", nativeQuery = true)
	    List<order_list> findByorder_id(Integer order_id);
	   
	    
	    
    @Query(value = "select * from order_list where member_id = ?1", nativeQuery = true)
		    List<order_list> findBymember_id(Integer member_id);   
		    
@Query("select o from order_list o where o.member_id = :mID")
List<order_list> selectOrderByMemberID(@Param("mID") Integer mID);	    
	    
}
