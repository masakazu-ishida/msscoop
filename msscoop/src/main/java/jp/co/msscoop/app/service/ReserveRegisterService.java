package jp.co.msscoop.app.service;

import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.form.ReserveForm;


/**
 * [概要]<br>
 * 予約登録機能に対するサービスインターフェース
 */
public interface ReserveRegisterService {
	ReserveForm input(ReserveForm registerForm);
	ReserveForm confirm(ReserveForm registerForm);
	String register(ReserveForm registerForm, UserInfo userInfo);
}
