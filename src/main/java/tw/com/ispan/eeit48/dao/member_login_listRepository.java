package tw.com.ispan.eeit48.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.ispan.eeit48.model.member_login_list;
@Repository
public interface member_login_listRepository  extends JpaRepository<member_login_list, Integer>{

}
