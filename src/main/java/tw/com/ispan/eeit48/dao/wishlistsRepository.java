package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.ispan.eeit48.model.wishlists;
@Repository
public interface wishlistsRepository extends JpaRepository<wishlists, Integer>{
	@Modifying
	@Query("update  wishlists  w  set w.item_likes = w.item_likes + 1 where w.wishlist_id = :id")
	void updateLike(@Param("id") Integer ID);
	
	@Query("select w from wishlists w order by w.wishlist_id desc")
	List<wishlists> orderByID();
	
	@Query("select w from wishlists w order by w.item_likes desc")
	List<wishlists> orderByLikes();
	
	@Query("select w from wishlists w where w.member_id = :mID")
	List<wishlists> selectByMemberId(@Param("mID") Integer mID);
}
