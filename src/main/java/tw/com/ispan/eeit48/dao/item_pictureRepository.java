package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.com.ispan.eeit48.model.item_picture;


@Repository
public interface item_pictureRepository extends JpaRepository<item_picture, Integer>{



	 @Query(value = "select * from  item_picture where item_id = ?1   ", nativeQuery = true)
	    item_picture queryByitem_id(Integer item_id);
	 
	 
	 @Query("select ip from item_picture ip where ip.item_id = :iID")
		List<item_picture> selectPictureByItemID(@Param("iID") Integer iID);
}