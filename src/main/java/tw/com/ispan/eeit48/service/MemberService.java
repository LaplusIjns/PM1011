package tw.com.ispan.eeit48.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.ispan.eeit48.dao.membersRepository;
import tw.com.ispan.eeit48.model.members;

//@Service
//@Transactional
public class MemberService {
	
//	@Autowired
//	membersRepository membersRepository;
//	
//	public members login(String username, String password) {
//		members bean = membersRepository.findAllById(username);
//		if(bean!=null) {
//			if(password!=null && password.length()!=0) {
//				byte[] pass = bean.getPassword();
//				byte[] temp = password.getBytes();
//				if(Arrays.equals(pass, temp)) {
//					return bean;
//				}
//			}
//		}
//		return null;
//	}
	
}