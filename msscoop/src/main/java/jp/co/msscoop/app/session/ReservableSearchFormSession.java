package jp.co.msscoop.app.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.form.ReservableSearchForm;

/**
 * [概要]<br>
 * 空室検索フォームを維持するセッションスコープBean
 * 
 */
@Component
@SessionScope
public class ReservableSearchFormSession {
	/**
	 * シリアライズ時のバージョン番号
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 空室検索フォーム。セッションで管理される
	 */
	private ReservableSearchForm searchForm;

	/**
	 * [概要]<br>
	 * セッションから空室検索フォームを取得。
	 * @return
	 */
	public ReservableSearchForm getSearchForm() {
		return searchForm;
	}

	/**
	 * [概要]<br>
	 * セッションに空室検索フォームを格納
	 * @param searchForm
	 */
	public void addSearchForm(ReservableSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	
	/**
	 * [概要]<br>
	 * セッション内の空室検索フォームを空にする
	 * 
	 */
	public void removeSearchForm() {
		this.searchForm = null;
	}
}
