package jp.co.msscoop.app.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;


import jp.co.msscoop.app.dao.ReservableRoomInfoDAO;
import jp.co.msscoop.app.dao.ReserveDAO;
import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.form.ReserveForm;

/**
 * [概要]<br>
 * 予約変更機能に対するサービスインターフェース実装クラス
 */
@Service
public class ReserveUpdateServiceImpl implements ReserveUpdateService {
	
	private final ReserveDAO reserveDAO;
	
	/**
	 * messageas.propertiesアクセス用インターフェース
	 */
	private final MessageSource messageSource;
	
	
	 
	/**
	 * [概要]
	 * 予約変更機能に必要なオブジェクトのインターフェースをコンストラクタインジェクションする
	 * 	 
	 * @param reserveDAO
	 * @param messageSource
	 */
	public ReserveUpdateServiceImpl(ReserveDAO reserveDAO, MessageSource messageSource) {
		this.reserveDAO = reserveDAO;
		this.messageSource = messageSource;
	
	}
	

	@Override
	public ReserveForm input() {
		// TODO 自動生成されたメソッド・スタブ
		
		
		return null;
	}

	@Override
	public ReserveForm confirm(ReserveForm registerForm) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String register(ReserveForm registerForm) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
