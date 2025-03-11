package jp.co.msscoop.app.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jp.co.msscoop.app.dto.UserInfo;
import lombok.Data;




@Component
@SessionScope
public class UserSession implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserInfo loginUser;
	
	private String userId;

	public UserInfo getLoginUser() {
		return loginUser;
	}

	public void addLoginUser(UserInfo loginUser) {
		this.loginUser = loginUser;
	}
	
	public void removeLoginUser() {
		this.loginUser = null;
	}

	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
