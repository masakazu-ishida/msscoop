package jp.co.msscoop.app.service;

import jp.co.msscoop.app.form.ReserveForm;

/**
 * [概要]<br>
 * 予約変更機能に対するサービスインターフェース
 */
public interface ReserveCancelService {

	public ReserveForm confirm(String reserveId);
	public int commit(ReserveForm updateForm);
	
}
