package tw.com.ispan.eeit48.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.eeit48.model.items;
import tw.com.ispan.eeit48.model.members;
import tw.com.ispan.eeit48.model.order_list;
import tw.com.ispan.eeit48.model.wishlists;
import tw.com.ispan.eeit48.service.WishpoolService;

@RestController
@RequestMapping(path = { "/api/wishpool" })
public class WishpoolApiController {

	@Autowired
	private WishpoolService wishpoolService;

	@GetMapping
	public ResponseEntity<List<wishlists>> selectAll() {
		List<wishlists> result = wishpoolService.selectById(null);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/recent")
	public ResponseEntity<List<wishlists>> orderByID(){
		List<wishlists> result = wishpoolService.orderByID();
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/popular")
	public ResponseEntity<List<wishlists>> orderByLikes(){
		List<wishlists> result = wishpoolService.orderByLikes();
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> selectById(@PathVariable("id") Integer ID) {
		wishlists bean = new wishlists();
		bean.setWishlist_id(ID);
		List<wishlists> result = wishpoolService.selectById(bean);
		if (result != null && !result.isEmpty()) {
			return ResponseEntity.ok(result.get(0));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/like/{id}")
	public void updateLike(@PathVariable("id") Integer ID) {
		 wishpoolService.updateLike(ID);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody wishlists bean) {
		wishlists result = wishpoolService.insert(bean);
		System.out.println(result);
		if (result != null) {
			URI uri = URI.create("/api/wishpool/" + result.getMember_id());
			return ResponseEntity.created(uri).body(result);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

//	public class memberDetail {
//		private members mb;
//		private List<members> mbs;
//		private List<items> it;
//		private List<List<items>>  its;
//		private List<order_list> od;
//		private List<List<order_list>>  ods;
//		
//		public members getMb() {
//			return mb;
//		}
//		public void setMb(members mb) {
//			this.mb = mb;
//		}
//		
//		public List<members> getMbs() {
//			return mbs;
//		}
//		public void setMbs(List<members> mbs) {
//			this.mbs = mbs;
//		}
//		public List<items> getIt() {
//			return it;
//		}
//		public void setIt(List<items> it) {
//			this.it = it;
//		}
//		public List<order_list> getOd() {
//			return od;
//		}
//		public void setOd(List<order_list> od) {
//			this.od = od;
//		}
//		public List<List<items>> getIts() {
//			return its;
//		}
//		public void setIts(List<List<items>> its) {
//			this.its = its;
//		}
//		public List<List<order_list>> getOds() {
//			return ods;
//		}
//		public void setOds(List<List<order_list>> ods) {
//			this.ods = ods;
//		}
//		
//	}
}
