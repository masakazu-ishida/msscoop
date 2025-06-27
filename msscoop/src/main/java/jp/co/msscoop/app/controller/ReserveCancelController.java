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
import jp.co.msscoop.app.service.ReserveCancelService;
import jp.co.msscoop.app.service.ReserveRegisterService;
import jp.co.msscoop.app.service.ReserveUpdateService;

/**
 * [概要]<br>
 * 予約変更機能のコントローラ
 */
@Controller
@SessionAttributes("cancelForm")
@RequestMapping("/reserve/cancel")
public class ReserveCancelController {
	/**
	 * 予約情報にアクセスするためReserveServiceをインジェクションする
	 */
	private final ReserveCancelService reserveCancelService;
	
	
	
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
	@ModelAttribute("cancelForm")
	public ReserveForm setupForm() {
		return new ReserveForm();
	}
	
	public ReserveCancelController(ReserveCancelService reserveCancelService) {
		this.reserveCancelService = reserveCancelService;
		
	}
	/*
	@PostMapping(params = "input")
	public ModelAndView input(@RequestParam("reserveId") String reserveId) {
		ReserveForm reserveForm = reserveCancelService.input(reserveId);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/reserve/cancel/input");
		mv.addObject("cancelForm", reserveForm);
		return mv;
	}
	*/
	
	@PostMapping(params = "confirm")
	public ModelAndView confirm(@ModelAttribute("cancelForm")ReserveForm reserveForm ) {
		ModelAndView mv = new ModelAndView();
		reserveForm = reserveCancelService.confirm(reserveForm.getReserveId());
		mv.setViewName("/reserve/cancel/confirm");
		mv.addObject("cancelForm", reserveForm);
		return mv;
	}
	
	@PostMapping(params = "commit")
	public ModelAndView commmit(@ModelAttribute("cancelForm")ReserveForm reserveForm ) {
		ModelAndView mv = new ModelAndView();
		int result = reserveCancelService.commit(reserveForm);
		mv.setViewName("redirect:/reserve/cancel?complete");
		
		return mv;
	}
	
	@GetMapping(params = "complete")
	public ModelAndView complete(@ModelAttribute("cancelForm")ReserveForm reserveForm, SessionStatus status ) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/reserve/cancel/complete");
		mv.addObject("cancelForm", reserveForm);
		status.setComplete();
		return mv;
	}


}
