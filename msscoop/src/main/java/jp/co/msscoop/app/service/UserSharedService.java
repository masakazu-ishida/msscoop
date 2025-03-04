package jp.co.msscoop.app.service;

import jp.co.msscoop.app.dto.UserInfo;

public interface UserSharedService {
	
	UserInfo authorize(String id,String password);

}
