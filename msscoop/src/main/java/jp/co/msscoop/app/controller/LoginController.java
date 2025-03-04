package jp.co.msscoop.app.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.form.LoginForm;
import jp.co.msscoop.app.service.UserSharedService;
import jp.co.msscoop.app.session.UserSession;

/**
 * ■ログイン処理を行う
 * 
 * ■クラスレベルで宣言するアノテーション
 * @Controller
 * @RequestMapping("/login")
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	/**
	 * MessageSourceインターフェースのオブジェクトをインジェクションする
	 * messages.propertisからメッセージを取得するのに使用する
	 */
	private final MessageSource messageSource;
	
	/*
	 * 
	 * */
	private final UserSharedService service;
	
	/**
	 * SessionScopeBeanとなる
	 */
	/**
	 * 
	 */
	private final UserSession userSession;
	
	
	/**
	 * 画面初期化時にLoginForm()をModelにaddAttributeする
	 * ■クラスレベルで宣言するアノテーション
	 * @ModelAttribute
	 * @return　LoginFormをインスタンス化して返す。初期値は不要
	 */
	@ModelAttribute
	LoginForm setupForm() {
		return new LoginForm();
	}
	
	/**
	 * コンストラクタインジェクションを行うため、引数にインジェクションするインターフェースを指定し、メンバ変数にセットする
	 * 
	 * @param messageSource MessageSourceを渡す
	 * @param service UserSharedServiceを渡す
	 * @param sessionBean UserSessionを渡す
	 */
	public LoginController(MessageSource messageSource, UserSharedService service,UserSession sessionBean) {
		this.messageSource = messageSource;
		this.service = service;
		this.userSession = sessionBean;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("")
	public String index() {
		return "/common/login";
	}
	
	@PostMapping("")
	public String signIn(@Validated LoginForm form, BindingResult result, Model model) {
		
		//String型変数messageを宣言
		
		
		//入力エラーが発生した場合ログイン画面に戻る
		if(result.hasErrors()) {
			//ログイン画面に戻る
			return "/common/login"; 
		}
		//
		UserInfo user = service.authorize(form.getUsername(), form.getPassword());
		//
		if(user == null) {
			
			String errorMessage = messageSource.getMessage("bus.error.authorized", null, Locale.JAPAN);
			
			model.addAttribute("errormsg", errorMessage);
			
			//ログイン画面に戻る
			return "/common/login";
		}
		else {
			
			userSession.setLoginUser(user);
			//model.addAttribute("userSession", userSession);
			return "redirect:/reservable/search"; 
		}
	}
}
