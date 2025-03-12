package jp.co.msscoop.app.common;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.msscoop.app.dao.UserInfoDAO;
import jp.co.msscoop.app.dto.UserDetailsImpl;
import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.service.UserSharedService;


@Service
public class LocalUserDetailServiceIml implements UserDetailsService {

	private final UserInfoDAO userInfoDAO;
	
	
	public LocalUserDetailServiceIml(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		
		
		UserInfo userInfo = userInfoDAO.findById(username);
		if(userInfo == null) {
			throw new UsernameNotFoundException(username);
		}
		
		UserDetailsImpl userDetails  = new UserDetailsImpl(userInfo);
		
		
		return userDetails;
	}

}
