package jp.co.msscoop.app.common;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.msscoop.app.dao.UserInfoDAO;
import jp.co.msscoop.app.dto.UserDetailsImpl;
import jp.co.msscoop.app.dto.UserInfo;


/**
 * 
 * 
 * 
 * SpringSecuityの認証時に呼び出され、画面から入力されたユーザＩＤから
 * ユーザ情報を返却する
 * 
 */
@Service
public class LocalUserDetailServiceIml implements UserDetailsService {

	/**
	 * ユーザ情報にアクセスするDAO
	 */
	private final UserInfoDAO userInfoDAO;
	
	/**
	 * コンストラクタインジェクションで初期化
	 * 
	 * @param userInfoDAO
	 */
	public LocalUserDetailServiceIml(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
	
	/**
	 * ユーザIDを元にDB問い合わせを行い、UserDetailsインターフェースの実装クラスを返す
	 */
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
