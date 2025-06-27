package jp.co.msscoop.app.common;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.msscoop.app.dao.UserInfoDAO;
import jp.co.msscoop.app.dto.UserDetailsImpl;
import jp.co.msscoop.app.dto.UserInfo;


/**
 * [概要]<br>
 * SpringSecuityの認証プロセスの一部で時に呼び出される。画面から入力されたユーザＩＤから
 * ユーザ情報(ユーザID・ユーザ名・ロール・パスワード)をSpringSecurityに返す。
 * 認証処理自体はSpringSecurityにて担当する。
 * 
 */
@Service
public class LocalUserDetailServiceIml implements UserDetailsService {

	/**
	 * ユーザ情報にアクセスするDAO
	 */
	private final UserInfoDAO userInfoDAO;
	
	
	
	/**
	 * 
	 * [概要]
	 * ユーザ情報予約登録に必要なオブジェクトのインターフェースをコンストラクタインジェクションする
	 * 
	 * コンストラクタインジェクションで初期化
	 * 
	 * @param userInfoDAO
	 */
	public LocalUserDetailServiceIml(UserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}
	
	/**
	 * 
	 */
	
	/**
	 * [概要]
	 * 予約登録に必要なオブジェクトのインターフェースをコンストラクタインジェクションする
	 * ユーザIDを元にDB問い合わせを行い、UserDetailsインターフェースの実装クラスを返す
	 * 
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
