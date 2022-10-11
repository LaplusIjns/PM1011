package tw.com.ispan.eeit48.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.item_categoriesRepository;
import tw.com.ispan.eeit48.dao.item_pictureRepository;
import tw.com.ispan.eeit48.dao.item_tagsRepository;
import tw.com.ispan.eeit48.dao.itemsRepository;
import tw.com.ispan.eeit48.model.item_categories;
import tw.com.ispan.eeit48.model.item_picture;
import tw.com.ispan.eeit48.model.item_tags;
import tw.com.ispan.eeit48.model.items;
import tw.com.ispan.eeit48.model.members;

@Service
@Transactional
public class IndexSearchItemService {

	@Autowired
	private itemsRepository iRepository;

	@Autowired
	private item_categoriesRepository icRepository;

	@Autowired
	private item_tagsRepository itRepository;

	@Autowired
	private item_pictureRepository ipRepository;
	
	public List<items> selectById(items bean) {
		List<items> result = null;
		if (bean != null && bean.getItem_id() != null && !bean.getItem_id().equals(0)) {
			Optional<items> data = iRepository.findById(bean.getItem_id());
			if (data.isPresent()) {
				result = new ArrayList<items>();
				result.add(data.get());
			}
		} else {
			result = iRepository.findAll();
		}
		return result;
	}

//	以ID搜尋商品
	public List<items> selectItemByItemID(@Param("iID") Integer iID) {
		return iRepository.selectItemByItemID(iID);
	}

//	以類別搜尋商品
	public List<items> selectItemByCategoryID(@Param("icID") Integer icID) {
		return iRepository.selectItemByCategoryID(icID);
	}

//	以商品名稱搜尋商品
	public List<items> selectItemByItemName(@Param("iName") String iName) {
		return iRepository.selectItemByItemName(iName);
	}

//	以價格區間搜尋商品
	public List<items> selectItemByPrice(@Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice) {
		return iRepository.selectItemByPrice(minPrice, maxPrice);
	}

//	以名稱搜尋商品標籤
	public List<item_tags> selectItemTagsByName(@Param("itName") String itName) {
		return itRepository.selectItemTagsByName(itName);
	}

//	以名稱搜尋商品類別
	public List<item_categories> selectItemCategoriesByName(@Param("icName") String icName) {
		return icRepository.selectItemCategoriesByoneName(icName);
	}
	
	public List<item_categories> selectItemCategoriesBytwoNames(@Param("icName01") String icName01, 
			@Param("icName02") String icName02){
		return icRepository.selectItemCategoriesBytwoNames(icName01, icName02);
	}
	
//	以商品ID搜尋圖片
	public List<item_picture> selectPictureByItemID(@Param("iID") Integer iID){
		return ipRepository.selectPictureByItemID(iID);
	}
}
