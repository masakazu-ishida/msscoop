package jp.co.msscoop.app.service;

import jp.co.msscoop.app.dto.UserInfo;

/**
 * [概要]<br>
 * 
 */
public interface UserSharedService {
	
	UserInfo authorize(String id,String password);
	boolean register(UserInfo info);

}
