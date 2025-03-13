package jp.co.msscoop.app.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserInfo userInfo;
	
	private Collection<GrantedAuthority> authorities;
	
	public UserDetailsImpl(UserInfo userInfo) {
		this.userInfo = userInfo;
		authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(userInfo.getRole()));
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return userInfo.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return userInfo.getUserId();
	}
	
	public String getFullName() {
		return userInfo.getFullName();
	}

}
