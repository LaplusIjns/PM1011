package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.com.ispan.eeit48.model.items;
import tw.com.ispan.eeit48.model.member_payment_detail;

@Repository
public interface member_payment_detailRepository extends JpaRepository<member_payment_detail,Integer>{
	 @Query(value = "select * from member_payment_detail where member_id = ?1", nativeQuery = true)
	    List<member_payment_detail> findBymember_id(Integer memberid);
}
