package tw.com.ispan.eeit48.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.model.item_categories;
import tw.com.ispan.eeit48.model.item_picture;
import tw.com.ispan.eeit48.model.item_tags;
import tw.com.ispan.eeit48.model.items;
import tw.com.ispan.eeit48.model.members;
import tw.com.ispan.eeit48.service.IndexSearchItemService;

@CrossOrigin
@RestController
@RequestMapping(path = { "/api/searchItem" })
public class IndexSearchItemApiController {

	@Autowired
	private IndexSearchItemService indexSearchItemService;

	@GetMapping
	public ResponseEntity<List<items>> selectAll() {
		List<items> result = indexSearchItemService.selectById(null);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> selectById(@PathVariable("id") Integer ID) {
		items bean = new items();
		bean.setItem_id(ID);
		List<items> result = indexSearchItemService.selectById(bean);
		if (result != null && !result.isEmpty()) {
			return ResponseEntity.ok(result.get(0));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	以價格區間搜尋商品
	@GetMapping("/price/{minPrice}/{maxPrice}")
	public ResponseEntity<?> selectByPrice(@PathVariable("minPrice") Integer minPrice,
			@PathVariable("maxPrice") Integer maxPrice) {
		ItemDetail itemDetail = new ItemDetail();
		List<items> is = indexSearchItemService.selectItemByPrice(minPrice, maxPrice);
		List<List<item_picture>> ips = new ArrayList<List<item_picture>>();
		for(items i:is) {
			ips.add(indexSearchItemService.selectPictureByItemID(i.getItem_id()));
		}
		itemDetail.setIts(is);
		itemDetail.setItps(ips);
		if (is != null && !is.isEmpty()) {
			return ResponseEntity.ok(itemDetail);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	以商品名稱搜尋商品
	@PostMapping("/itemName")
	public ResponseEntity<?> selectByItemName(items bean) {
		System.out.println("223");
		ItemDetail itemDetail = new ItemDetail();
		List<items> is = indexSearchItemService.selectItemByItemName(bean.getItem_name());
		List<List<item_picture>> ips = new ArrayList<List<item_picture>>();
		
		for(items i:is) {
			ips.add(indexSearchItemService.selectPictureByItemID(i.getItem_id()));
		}
		itemDetail.setIts(is);
		itemDetail.setItps(ips);
		
		if (is != null && !is.isEmpty()) {
			return ResponseEntity.ok(itemDetail);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	以類別名稱搜尋商品
	@PostMapping("/itemCategory")
	public ResponseEntity<?> selectByCategoryName(item_categories bean) {
		System.out.println("GG");
		ItemDetail itemDetail = new ItemDetail();
		List<item_categories> ics = indexSearchItemService.selectItemCategoriesByName(bean.getCategory_name());
		List<items> is = new ArrayList<items>();
		List<List<item_picture>> ips = new ArrayList<List<item_picture>>();
		
		for (item_categories ic : ics) {
			for (items i : indexSearchItemService.selectItemByCategoryID(ic.getCategory_id())) {
				is.add(i);
			}
		}
		
		for(items i:is) {
			ips.add(indexSearchItemService.selectPictureByItemID(i.getItem_id()));
		}
		itemDetail.setIts(is);
		itemDetail.setItps(ips);
		
		if (is != null && !is.isEmpty()) {
			return ResponseEntity.ok(itemDetail);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/itemPartCategory")
	public ResponseEntity<?> selectByPartCategoryName(String name01, String name02) {
		ItemDetail itemDetail = new ItemDetail();
		List<item_categories> ics = indexSearchItemService.selectItemCategoriesBytwoNames(name01, name02);
		List<items> is = new ArrayList<items>();
		List<List<item_picture>> ips = new ArrayList<List<item_picture>>();
		
		for (item_categories ic : ics) {
			for (items i : indexSearchItemService.selectItemByCategoryID(ic.getCategory_id())) {
				is.add(i);
			}
		}
		
		for(items i:is) {
			ips.add(indexSearchItemService.selectPictureByItemID(i.getItem_id()));
		}
		itemDetail.setIts(is);
		itemDetail.setItps(ips);
		
		if (is != null && !is.isEmpty()) {
			return ResponseEntity.ok(itemDetail);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	以標籤名稱搜尋商品
	@PostMapping("/itemTag")
	public ResponseEntity<?> selectByTagName(item_tags bean) {
		ItemDetail itemDetail = new ItemDetail();
		List<item_tags> its = indexSearchItemService.selectItemTagsByName(bean.getItem_tag());
		List<items> is = new ArrayList<items>();
		List<List<item_picture>> ips = new ArrayList<List<item_picture>>();
		for (item_tags it : its) {
			for (items i : indexSearchItemService.selectItemByItemID(it.getItem_id())) {
				boolean isRepeat = false;
				for (int k = 0; k < is.size(); k++) {
					if (i == is.get(k)) {
						isRepeat = true;
					}
				}
				if (isRepeat == false) {
					is.add(i);
				}
			}
		}
		for(items i:is) {
			ips.add(indexSearchItemService.selectPictureByItemID(i.getItem_id()));
		}
		itemDetail.setIts(is);
		itemDetail.setItps(ips);
		if (is != null && !is.isEmpty()) {
			return ResponseEntity.ok(itemDetail);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public class ItemDetail {
		private List<items> its;
		private List<List<item_picture>> itps;

		public List<items> getIts() {
			return its;
		}

		public void setIts(List<items> its) {
			this.its = its;
		}

		public List<List<item_picture>> getItps() {
			return itps;
		}

		public void setItps(List<List<item_picture>> itps) {
			this.itps = itps;
		}

	}
}
