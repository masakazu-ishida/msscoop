package jp.co.msscoop.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import jp.co.msscoop.app.session.ReservableSearchFormSession;
import jp.co.msscoop.app.session.UserSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	private final UserSession userSession;
	private final ReservableSearchFormSession searchFormSession;
	
	public LogoutController(UserSession sessionBean,ReservableSearchFormSession searchFormSession) {
		
		this.userSession = sessionBean;
		this.searchFormSession = searchFormSession;
	}
	
	
	
	@GetMapping("")
	public String index() {
		//セッションBeanで格納されているデータを削除
		userSession.addLoginUser(null);
		searchFormSession.removeSearchForm();
		
		return "redirect:/login";
	}

}
