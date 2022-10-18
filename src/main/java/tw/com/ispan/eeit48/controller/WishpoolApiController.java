package tw.com.ispan.eeit48.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tw.com.ispan.eeit48.dao.wishlistsRepository;
import tw.com.ispan.eeit48.model.item_picture;
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

	@Autowired
	private wishlistsRepository wr;

	@GetMapping
	public ResponseEntity<List<wishlists>> selectAll() {
		List<wishlists> result = wishpoolService.selectById(null);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/recent")
	public ResponseEntity<List<wishlists>> orderByID() {
		List<wishlists> result = wishpoolService.orderByID();
		return ResponseEntity.ok(result);
	}

	@GetMapping("/popular")
	public ResponseEntity<List<wishlists>> orderByLikes() {
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

	// 新增物品到許願池
	@RequestMapping(value = "/addWishlist")
	@ResponseBody
	public void addWishlist(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			wishlists wishlist,	String category_type, String category_region, String boardgame_size, String console_type, 
			String comic_type,String novel_type, String boardgame_type) {
		System.out.println(category_type);
		String categoryID = "";

		if (category_type.equals("漫畫")) {
			categoryID += "1";
			categoryID += category_region;
			categoryID += comic_type;

		} else if (category_type.equals("小說")) {
			categoryID += "2";
			categoryID += category_region;
			categoryID += novel_type;
		} else if (category_type.equals("桌遊")) {
			categoryID += "3";
			categoryID += boardgame_size;
			categoryID += boardgame_type;
		} else if (category_type.equals("主機")) {
			categoryID += "4";
			categoryID += console_type;
		}
		System.out.println(categoryID);
		Integer category_id = Integer.parseInt(categoryID);
		wishlist.setCategory_id(category_id);

//		var member=(members)session.getAttribute("member");
//		System.out.println(member);
//  	    Integer memberID=member.getMember_id();
		wishlist.setMember_id(2);
		wishlist.setItem_likes(0);

		wr.save(wishlist);

		System.out.println(wishlist.getWishlist_id());
	}
	
//	找會員的願望
	@GetMapping(("/member"))
	public List<wishlists> selectByMemberId(HttpSession session) {
		var member=(members)session.getAttribute("member");
		return wr.selectByMemberId(member.getMember_id());
	}
}