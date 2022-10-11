package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.model.order_details;

@Repository
public interface order_detailRepository extends JpaRepository<order_details, Integer> {
	
	@Query(value = "select * from order_details where item_id = ?1", nativeQuery = true)
	List<order_details> findByitem_id(Integer item_id);

	@Query(value = "select * from order_details where order_id = ?1", nativeQuery = true)
	List<order_details> findByorder_id(Integer order_id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE order_details SET order_detail_state ='已接受/出貨中' WHERE order_id=?1", nativeQuery = true)
	int updateOrder_detail_state(Integer order_id);

}
