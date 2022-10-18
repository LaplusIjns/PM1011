package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.model.items;

@Repository
public interface itemsRepository extends JpaRepository<items, Integer> {
	
	@Query(value = "select * from items where item_id = ?1 and item_state !='已刪除' AND item_state !='已下架' AND item_state !='準備上架'", nativeQuery = true)
	items queryByitem_id(Integer item_id);
	
	// 擁有者權限可以看到還未上架的
	@Query(value = "select * from items where member_id = ?1 and item_state !='已刪除'  ", nativeQuery = true)
	List<items> findByOwner_id(Integer memberid);

	@Query(value = "select * from items where member_id = ?1 and item_state !='已刪除' AND item_state !='已下架' AND item_state !='準備上架' ", nativeQuery = true)
	List<items> findBymember_id(Integer memberid);

	@Query(value = "select * from items where item_id = ?1 and item_state !='已刪除' AND item_state !='已下架' AND item_state !='準備上架' ", nativeQuery = true)
	List<items> findByitem_id(Integer item_id);

	@Query(value = "select * from items where item_state !='已刪除' AND item_state !='已下架' AND item_state !='準備上架'", nativeQuery = true)
	List<items> findAllButDelete();

	@Transactional
	@Modifying
	@Query(value = "UPDATE items SET item_state='已刪除' WHERE item_id=?2")
	int deleteitem(Integer item_id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE items SET item_name=?1 ,  item_state=?2,	item_date=?3,item_price=?4     WHERE item_id=?5", nativeQuery = true)
	int updateitems(String item_name, String item_state, String item_date, Integer item_price, Integer item_id);
	
	@Query("select i from items i where i.member_id = :mID and item_state !='已刪除' AND item_state !='已下架' AND item_state !='準備上架'")
	List<items> selectItemByMemberID(@Param("mID") Integer mID);
	
	@Query("select i from items i where i.item_id = :iID and item_state !='已刪除' AND item_state !='已下架' AND item_state !='準備上架'")
	List<items> selectItemByItemID(@Param("iID") Integer iID);
	
	@Query("select i from items i where i.category_id = :icID and item_state !='已刪除' AND item_state !='已下架' AND item_state !='準備上架'")
	List<items> selectItemByCategoryID(@Param("icID") Integer icID);
	
	@Query("select i from items i where i.item_price  between :minPrice and :maxPrice")
	List<items> selectItemByPrice(@Param("minPrice") Integer minPrice,@Param("maxPrice") Integer maxPrice);
	
	@Query("select i from items i where i.	item_name like %:iName% and item_state !='已刪除' AND item_state !='已下架' AND item_state !='準備上架'")
	List<items> selectItemByItemName(@Param("iName") String iName);
	
	@Modifying
	@Query("update items i set i.item_state = :st, i.item_date = :dt where i.item_id = :iId")
	void updateItemState(@Param("st") String state ,@Param("dt") String date,@Param("iId") Integer ID);
	
	
	@Query("select i from items i where i.item_name like %:iName% ")
	List<items> selectItemByItemNameManager(@Param("iName") String iName);
	
	@Query(value = "select * from items where item_id = ?1",nativeQuery = true)
	items queryByrated_item_id(Integer id);
	
//	圖表分析用
	@Query("select i from items i where i.category_id like '1%'")
	List<items> selectMangaItem();
	
	@Query("select i from items i where i.category_id like '2%'")
	List<items> selectNovelItem();
	
	@Query("select i from items i where i.category_id like '3%'")
	List<items> selectBoardItem();
	
	@Query("select i from items i where i.category_id like '4%'")
	List<items> selectConsoleItem();
	
	@Query(value = "SELECT i.category_id from items i GROUP BY i.category_id ORDER BY SUM(i.item_times_rent_out) desc limit 8",nativeQuery = true)
	List<Integer> selectPopularCategories();
	
	@Query(value = "SELECT SUM(i.item_times_rent_out) s from items i GROUP BY i.category_id ORDER BY s desc limit 8",nativeQuery = true)
	List<Integer> selectPopularCategoriesRentOutTimes();
}