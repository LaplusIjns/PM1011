package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.com.ispan.eeit48.model.cart;



@Repository
public interface cartRepository extends JpaRepository<cart, Integer>{

	 @Query(value = "select * from cart where member_id = ?1", nativeQuery = true)
	    List<cart> findBymember_id(Integer memberid);
	    
		 @Query(value = "select * from cart where member_id = ?1 and items_id=?2", nativeQuery = true)
		    List<cart> findBymember_idAnditem_id(Integer memberid,Integer item_id);
}
