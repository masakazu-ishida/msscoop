package jp.co.msscoop.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import jp.co.msscoop.app.form.ReserveForm;
import jp.co.msscoop.app.service.ReserveRegisterService;
import jp.co.msscoop.app.service.ReserveUpdateService;

/**
 * [概要]<br>
 * 予約変更機能のコントローラ
 */
@Controller
@SessionAttributes("updateForm")
@RequestMapping("/reserve/update")
public class ReserveUpdateController {
	/**
	 * 予約情報にアクセスするためReserveServiceをインジェクションする
	 */
	private final ReserveUpdateService reserveUpdateService;
	
	
	
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
	@ModelAttribute("updateForm")
	public ReserveForm setupForm() {
		return new ReserveForm();
	}
	
	public ReserveUpdateController(ReserveUpdateService reserveUpdateService) {
		this.reserveUpdateService = reserveUpdateService;
		
	}
	
	@PostMapping(params = "input")
	public ModelAndView input(@RequestParam("reserveId") String reserveId) {
		ReserveForm reserveForm = reserveUpdateService.input(reserveId);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/reserve/update/input");
		mv.addObject("updateForm", reserveForm);
		return mv;
	}
	
	
	@PostMapping(params = "confirm")
	public ModelAndView confirm(@ModelAttribute("updateForm")ReserveForm reserveForm ) {
		ModelAndView mv = new ModelAndView();
		reserveForm = reserveUpdateService.confirm(reserveForm);
		mv.setViewName("/reserve/update/confirm");
		mv.addObject("updateForm", reserveForm);
		return mv;
	}
	
	@PostMapping(params = "commit")
	public ModelAndView commmit(@ModelAttribute("updateForm")ReserveForm reserveForm ) {
		ModelAndView mv = new ModelAndView();
		int result = reserveUpdateService.commit(reserveForm);
		mv.setViewName("redirect:/reserve/update?complete");
		
		return mv;
	}
	
	@GetMapping(params = "complete")
	public ModelAndView complete(@ModelAttribute("updateForm")ReserveForm reserveForm, SessionStatus status ) {
		ModelAndView mv = new ModelAndView();
		reserveForm = reserveUpdateService.confirm(reserveForm);
		mv.setViewName("/reserve/update/complete");
		mv.addObject("updateForm", reserveForm);
		status.setComplete();
		return mv;
	}


}
