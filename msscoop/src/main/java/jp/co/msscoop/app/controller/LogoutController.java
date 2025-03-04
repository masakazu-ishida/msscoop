package jp.co.msscoop.app.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.msscoop.app.form.LoginForm;
import jp.co.msscoop.app.service.UserSharedService;
import jp.co.msscoop.app.session.UserSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	private UserSession userSession;
	
	public LogoutController(UserSession sessionBean) {
		
		this.userSession = sessionBean;
	}
	
	
	
	@GetMapping("")
	public String index() {
		//セッションBeanで格納されているデータを削除
		userSession.setLoginUser(null);
		return "redirect:/login";
	}

}
