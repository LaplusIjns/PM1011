package tw.com.ispan.eeit48.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.controller.ManagementApiController.PopularCategories;
import tw.com.ispan.eeit48.dao.item_categoriesRepository;
import tw.com.ispan.eeit48.dao.itemsRepository;
import tw.com.ispan.eeit48.model.items;
import tw.com.ispan.eeit48.model.members;
import tw.com.ispan.eeit48.model.order_list;
import tw.com.ispan.eeit48.service.ManagemantService;

@CrossOrigin
@RestController
@RequestMapping(path = { "/api/manage" })
public class ManagementApiController {

	@Autowired
	private itemsRepository ir;

	@Autowired
	private item_categoriesRepository icr;

	@Autowired
	private ManagemantService managemantService;

	@GetMapping
	public ResponseEntity<List<members>> selectAll() {
		List<members> result = managemantService.selectById(null);
		return ResponseEntity.ok(result);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody members bean) {
		members result = managemantService.insert(bean);
		System.out.println(result);
		if (result != null) {
			URI uri = URI.create("/api/manage/" + result.getMember_id());
			return ResponseEntity.created(uri).body(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> selectById(@PathVariable("id") Integer ID) {
		members bean = new members();
		bean.setMember_id(ID);
		List<members> result = managemantService.selectById(bean);
		if (result != null && !result.isEmpty()) {
			return ResponseEntity.ok(result.get(0));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//以帳號搜尋會員
	@GetMapping("/account/{account}")
	public ResponseEntity<?> selectByAccount(@PathVariable("account") String account) {

		MemberDetail memberDetail = new MemberDetail();
		members member = new members();
		List<items> item = null;
		List<order_list> order = null;

		member = managemantService.selectByAccount(account);
		item = managemantService.selectItemByMemberID(member.getMember_id());
		order = managemantService.selectOrderByMemberID(member.getMember_id());

		memberDetail.setMb(member);
		memberDetail.setIt(item);
		memberDetail.setOd(order);

		if (member != null) {
			return ResponseEntity.ok(memberDetail);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	以暱稱搜尋會員
	@GetMapping("/nickname/{nickname}")
	public ResponseEntity<?> selectByNickname(@PathVariable("nickname") String nickname) {
		MemberDetail memberDetail = new MemberDetail();
		List<members> member = null;
		List<List<items>> item = new ArrayList<List<items>>();
		List<List<order_list>> order = new ArrayList<List<order_list>>();

		member = managemantService.selectByNickname(nickname);
		for (int i = 0; i < member.size(); i++) {
			item.add(managemantService.selectItemByMemberID(member.get(i).getMember_id()));
			order.add(managemantService.selectOrderByMemberID(member.get(i).getMember_id()));
		}

		memberDetail.setMbs(member);
		memberDetail.setIts(item);
		memberDetail.setOds(order);

		if (member != null) {
			return ResponseEntity.ok(memberDetail);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Integer ID) {
		members bean = new members();
		bean.setMember_id(ID);
		boolean result = managemantService.delete(bean);
		if (result) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/all/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer ID, @RequestBody members bean) {
		members result = managemantService.update(bean);
		if (result != null) {
			return ResponseEntity.ok(result);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	以商品名稱搜尋商品
	@PostMapping("/itemNameManager")
	ResponseEntity<?> selectByItemName(items bean) {
		System.out.println("123");
		List<items> is = ir.selectItemByItemNameManager(bean.getItem_name());
		if (is != null && !is.isEmpty()) {
			return ResponseEntity.ok(is);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	更改會員權限
	@PutMapping("/memberRank")
	public void updateMemberRank(String rank, String bDate, Integer ID) {
		managemantService.updateMemberRank(rank, bDate, ID);
		System.out.println(rank + bDate + ID);
	}

//	更改商品狀態
	@PutMapping("/itemState")
	public void updateItemState(@Param("st") String state, @Param("dt") String date, @Param("iId") Integer ID) {
		System.out.println(state + date + ID);
		managemantService.updateItemState(state, date, ID);
	}

//	搜尋現有商品類別
	@GetMapping("/itemAnalyze")
	public List<List<items>> selectItemByBigCategories() {
		System.out.println("123456");
		List<List<items>> total = new ArrayList<List<items>>();
		total.add(ir.selectMangaItem());
		total.add(ir.selectNovelItem());
		total.add(ir.selectBoardItem());
		total.add(ir.selectConsoleItem());
		return total;
	}

//	搜尋熱門商品類別及出租次數
	@GetMapping("/popularItemCatagories")
	public PopularCategories selectItemByPopularCategories() {
		PopularCategories PC = new PopularCategories();
		List<Integer> icid = ir.selectPopularCategories();
		List<String> ics = new ArrayList<String>();
		for (Integer id : icid) {
			ics.add(icr.selectItemCategoriesByID(id));
		}
		PC.setCategories(ics);
		PC.setRentTimes(ir.selectPopularCategoriesRentOutTimes());
		return PC;
	}

	public class PopularCategories {
		private List<String> cgs;
		private List<Integer> rts;

		public List<String> getCategories() {
			return cgs;
		}

		public void setCategories(List<String> cgs) {
			this.cgs = cgs;
		}

		public List<Integer> getRentTimes() {
			return rts;
		}

		public void setRentTimes(List<Integer> rts) {
			this.rts = rts;
		}

	}

	public class MemberDetail {
		private members mb;
		private List<members> mbs;
		private List<items> it;
		private List<List<items>> its;
		private List<order_list> od;
		private List<List<order_list>> ods;

		public members getMb() {
			return mb;
		}

		public void setMb(members mb) {
			this.mb = mb;
		}

		public List<members> getMbs() {
			return mbs;
		}

		public void setMbs(List<members> mbs) {
			this.mbs = mbs;
		}

		public List<items> getIt() {
			return it;
		}

		public void setIt(List<items> it) {
			this.it = it;
		}

		public List<order_list> getOd() {
			return od;
		}

		public void setOd(List<order_list> od) {
			this.od = od;
		}

		public List<List<items>> getIts() {
			return its;
		}

		public void setIts(List<List<items>> its) {
			this.its = its;
		}

		public List<List<order_list>> getOds() {
			return ods;
		}

		public void setOds(List<List<order_list>> ods) {
			this.ods = ods;
		}

	}
}
