package jp.co.msscoop.app.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.form.ReservableSearchForm;

/**
 * 空室検索フォームを維持する、セッションスコープBean
 * 
 */
@Component
@SessionScope
public class ReservableSearchFormSession {
	private static final long serialVersionUID = 1L;
	
	private ReservableSearchForm searchForm;

	public ReservableSearchForm getSearchForm() {
		return searchForm;
	}

	public void addSearchForm(ReservableSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	
	public void removeSearchForm() {
		this.searchForm = null;
	}
}
