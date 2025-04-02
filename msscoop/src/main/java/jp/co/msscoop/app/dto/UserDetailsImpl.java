package jp.co.msscoop.app.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * [概要]<br>
 * SpringSecurityのユーザ情報に対応するDTO
 */
public class UserDetailsImpl implements UserDetails{

	
	/**
	 * シリアライズ時のバージョン番号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * UserInfo（ユーザテーブル）の列に対応するDTO
	 */
	private UserInfo userInfo;
	
	/**
	 * このユーザ持つロールのコレクション
	 */
	private Collection<GrantedAuthority> authorities;
	
	/**
	 * ユーザ情報のDTOで初期化するコンストラクタ
	 * 
	 * @param userInfo
	 */
	public UserDetailsImpl(UserInfo userInfo) {
		this.userInfo = userInfo;
		authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(userInfo.getRole()));
		
	}
	
	/**
	 * [概要]<br>
	 * ロールのコレクションを返す。SpringSecurityから呼ばれる。
	 * 
	 * @return
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return authorities;
	}

	/**
	 * [概要]<br> 
	 * パスワードを取得する。SpringSecurityから呼ばれる。
	 * 
	 * @return
	 */
	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return userInfo.getPassword();
	}

	/**
	 * [概要]<br>
	 * ユーザ名（ログインID）を取得する。SpringSecurityから呼ばれる。
	 * @return 
	 */
	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return userInfo.getUserId();
	}
	
	/**
	 * [概要]<br>
	 * ユーザの姓名を取得する。
	 * @return ユーザの姓名の文字列を返す
	 */
	public String getFullName() {
		return userInfo.getFullName();
	}
	
	
	/**
	 * [概要]<br>
	 * 
	 * @return
	 */
	public UserInfo getUserInfo(){
		return this.userInfo;
	}

}
