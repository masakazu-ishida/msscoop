package jp.co.msscoop.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import jp.co.msscoop.app.session.ReservableSearchFormSession;



//@Controller
//@RequestMapping("/logout")
public class LogoutController {
	
	
	private final ReservableSearchFormSession searchFormSession;
	
	public LogoutController(ReservableSearchFormSession searchFormSession) {
		
		this.searchFormSession = searchFormSession;
	}
	
	
	
	@GetMapping("")
	public String index() {
		//セッションBeanで格納されているデータを削除
		searchFormSession.removeSearchForm();
		
		return "redirect:/login";
	}

}