package tw.com.ispan.eeit48.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.dao.itemsRepository;
import tw.com.ispan.eeit48.dao.membersRepository;
import tw.com.ispan.eeit48.dao.order_listRepository;
import tw.com.ispan.eeit48.dao.wishlistsRepository;
import tw.com.ispan.eeit48.model.items;
import tw.com.ispan.eeit48.model.members;
import tw.com.ispan.eeit48.model.order_list;
import tw.com.ispan.eeit48.model.wishlists;

@Service
@Transactional
public class WishpoolService {
	@Autowired
	private membersRepository mRepository;
	@Autowired
	private itemsRepository iRepository;
	@Autowired
	private wishlistsRepository wRepository;

	public List<wishlists> selectById(wishlists bean) {
		List<wishlists> result = null;
		if (bean != null && bean.getWishlist_id() != null && !bean.getWishlist_id().equals(0)) {
			Optional<wishlists> data = wRepository.findById(bean.getWishlist_id());
			if (data.isPresent()) {
				result = new ArrayList<wishlists>();
				result.add(data.get());
			}
		} else {
			result = wRepository.findAll();
		}
		return result;
	}

	public List<wishlists> orderByID(){
		return wRepository.orderByID();
	}

	public List<wishlists> orderByLikes(){
		return wRepository.orderByLikes();
	}
	
	public void updateLike(@Param("id") Integer ID) {
		wRepository.updateLike(ID);
	}
	
	public wishlists insert(wishlists bean) {
		wishlists result = null;
		if (bean != null && bean.getWishlist_id() != null) {
			if (!wRepository.existsById(bean.getWishlist_id())) {
				result = wRepository.save(bean);
			}
		}
		return result;
	}
	

	
	
		

		
}
