package jp.co.msscoop.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


import jp.co.msscoop.app.form.ReserveForm;
import jp.co.msscoop.app.service.ReserveRegisterService;

/**
 * [概要]<br>
 * 予約変更機能のコントローラ
 */
@Controller
@SessionAttributes("registerForm")
@RequestMapping("/reserve/update")
public class ReserveUpdateController {
	/**
	 * 予約情報にアクセスするためReserveServiceをインジェクションする
	 */
	private final ReserveRegisterService reserveService;
	
	
	
	/**
	 * ■概要
	 * ReserveRegisterFormをインスタンス化し、リターンする。
	 * 
	 * ■動きの説明
	 * セッションにキー名"registerForm"でFormオブジェクトを設定する働きとなり、
	 * これにより、確認画面→入力画面に戻ったときに、最初に入力された値を保持して
	 * 予約入力を復元できる。
	 * 
	 * "@ModelAttribute("registerForm")"でキー名を指定しないと、
	 * 確認画面から入力画面に戻るときにエラーになる。
	 * @return
	 */
	@ModelAttribute("registerForm")
	public ReserveForm setupForm() {
		return new ReserveForm();
	}
	
	public ReserveUpdateController(ReserveRegisterService reserveService) {
		this.reserveService = reserveService;
		
	}
	
	@PostMapping(params = "input")
	public String input(@ModelAttribute("registerForm")ReserveForm registerForm , BindingResult result,  Model model) {
		reserveService.input(registerForm);
		return "/reserve/register/input";
	}


}
