package jp.co.msscoop.app.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.msscoop.app.dao.UserInfoDAO;
import jp.co.msscoop.app.dto.UserInfo;


@Service
public class UserSharedServiceImpl implements UserSharedService {
	private final UserInfoDAO dao;
	private final PasswordEncoder passEncoder;
	
	public UserSharedServiceImpl(UserInfoDAO dao, PasswordEncoder passEncoder) {
		this.dao = dao; 
		this.passEncoder = passEncoder;
	}
	
	
	@Override
	public boolean register(UserInfo info) {
		// TODO 自動生成されたメソッド・スタブ
				
		//パスワードをエンコードする。
		String encodedPass = passEncoder.encode("12345");
		info.setPassword(encodedPass);
		return false;
	}
	
	
	@Override
	public UserInfo authorize(String id,String password) {
		// TODO 自動生成されたメソッド・スタブ
		
		UserInfo user = dao.findById(id);
		if(user != null) {
			if(user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

}
