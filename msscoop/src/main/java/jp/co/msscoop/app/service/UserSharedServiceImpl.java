package jp.co.msscoop.app.service;

import org.springframework.stereotype.Service;

import jp.co.msscoop.app.dao.UserInfoDAO;
import jp.co.msscoop.app.dto.UserInfo;


@Service
public class UserSharedServiceImpl implements UserSharedService {
	private UserInfoDAO dao;
	
	public UserSharedServiceImpl(UserInfoDAO dao) {
		this.dao = dao; 
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
