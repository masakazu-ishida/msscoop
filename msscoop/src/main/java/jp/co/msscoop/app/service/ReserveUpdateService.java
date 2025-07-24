package jp.co.msscoop.app.service;

import jp.co.msscoop.app.form.ReserveForm;

/**
 * [概要]<br>
 * 予約変更機能に対するサービスインターフェース
 */
public interface ReserveUpdateService {

	public ReserveForm input(String reserveId,ReserveForm reservForm);
	public ReserveForm confirm(ReserveForm updateForm);
	public boolean update(ReserveForm updateForm);
	
}
