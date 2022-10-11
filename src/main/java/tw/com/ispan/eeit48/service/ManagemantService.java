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
import tw.com.ispan.eeit48.model.items;
import tw.com.ispan.eeit48.model.members;
import tw.com.ispan.eeit48.model.order_list;

@Service
@Transactional
public class ManagemantService {
	@Autowired
	private membersRepository mRepository;
	@Autowired
	private itemsRepository iRepository;
	@Autowired
	private order_listRepository oRepository;

	public List<members> selectById(members bean) {
		List<members> result = null;
		if (bean != null && bean.getMember_id() != null && !bean.getMember_id().equals(0)) {
			Optional<members> data = mRepository.findById(bean.getMember_id());
			if (data.isPresent()) {
				result = new ArrayList<members>();
				result.add(data.get());
			}
		} else {
			result = mRepository.findAll();
		}
		return result;
	}

//	以帳號搜尋會員
	public members selectByAccount(@Param("acc") String account) {
		return mRepository.selectByAccount(account);
	}
	
//	以暱稱搜尋會員
	public List<members> selectByNickname(@Param("nick") String nickname) {
		return mRepository.selectByNickname(nickname);
	}
	
	public members insert(members bean) {
		members result = null;
		if (bean != null && bean.getMember_id() != null) {
			if (!mRepository.existsById(bean.getMember_id())) {
				result = mRepository.save(bean);
			}
		}
		return result;
	}

	public members update(members bean) {
		members result = null;
		if (bean != null && bean.getMember_id() != null) {
			if (mRepository.existsById(bean.getMember_id())) {
				result = mRepository.save(bean);
			}
		}
		return result;
	}

//	更改會員權限及停權日期
	public void updateMemberRank(@Param("rk") String rank ,@Param("bd") String bDate,@Param("mId") Integer ID) {
		mRepository.updateMemberRank(rank,bDate,ID);
	}
	
//	更改商品狀態
	public void updateItemState(@Param("st") String state,@Param("dt") String date ,@Param("iId") Integer ID) {
		iRepository.updateItemState(state,date, ID);
	}
	
	public boolean delete(members bean) {
		boolean result = false;
		if (bean != null && bean.getMember_id() != null) {
			if (mRepository.existsById(bean.getMember_id())) {
				mRepository.deleteById(bean.getMember_id());
				result = true;
			}
		}
		return result;
	}
	
//	以商品名稱搜尋商品
	public List<items> selectItemByItemName(@Param("iName") String iName) {
		return iRepository.selectItemByItemName(iName);
	}
	
	//以會員ID搜尋商品
	public List<items> selectItemByMemberID(@Param("mID") Integer mID){
		return iRepository.selectItemByMemberID(mID);
	}
	
	//以會員ID搜尋訂單
		public List<order_list> selectOrderByMemberID(@Param("mID") Integer mID){
			return oRepository.selectOrderByMemberID(mID);
		}
		

		
}
