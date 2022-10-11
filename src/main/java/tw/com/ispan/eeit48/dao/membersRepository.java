package tw.com.ispan.eeit48.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.eeit48.model.members;

@Repository
public interface membersRepository extends JpaRepository<members, Integer> {
	
	   
	@Query(value = "select * from members where member_account = ?1", nativeQuery = true)
	List<members> findBymember_account(String member_account);

	@Query(value = "select * from members where member_id = ?1", nativeQuery = true)
	List<members> findBymember_id(Integer memberid);
	
	@Query(value = "select * from members where member_id = ?1", nativeQuery = true)
	members queryBymember_id(Integer memberid);

	@Query(value = "select * from members where member_account = ?1", nativeQuery = true)
	members queryBymember_account(String member_account);

	@Query(value = "select * from members where member_account = ?1 and member_password = ?2", nativeQuery = true)
	members queryBymember_password(String member_account, String member_password);
	
	@Query(value = "select member_account from members where member_id = ?1", nativeQuery = true)
	String findmember_accountBymember_id(Integer member_id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE members SET bank_account =?1 WHERE member_id=?2", nativeQuery = true)
	int updateBank_account(Integer bank_account, Integer member_id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE members SET seven11_address=?1,ok_address=?2,family_address=?3 WHERE member_id=?4", nativeQuery = true)
	int updateConvenientStore(String seven11_address,String ok_address,String family_address, Integer member_id);

	
	@Query("select m from members  m where m.member_account = :acc")
	members selectByAccount(@Param("acc") String account);
	
	@Query("select m from members  m where m.member_nickname like  %:nick%")
	List<members> selectByNickname(@Param("nick") String nickname);
	
	@Modifying
	@Query("update  members  m  set m.member_rank = :rk, m.member_ban_date = :bd where m.member_id = :mId")
	void updateMemberRank(@Param("rk") String rank ,@Param("bd") String banDate,@Param("mId") Integer ID);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE members SET bank_account='' WHERE member_id=?1", nativeQuery = true)
	int deletebankaccount(Integer member_id);
	

	@Query(value = "select * from members where member_id = ?1 ", nativeQuery = true)
    members queryByrate_id(Integer rate_id);
	
	
}
