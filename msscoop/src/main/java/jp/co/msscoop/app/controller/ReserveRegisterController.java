package jp.co.msscoop.app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.msscoop.app.dto.UserDetailsImpl;
import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.exception.UseCaseException;
import jp.co.msscoop.app.form.ReserveForm;
import jp.co.msscoop.app.service.ReserveRegisterService;


/**
 * [概要]<br>
 * 予約登録機能のコントローラ
 * 
 */
@Controller
@SessionAttributes("registerForm")
@RequestMapping("/reserve/register")
public class ReserveRegisterController {
	
	
	/**
	 * 予約情報にアクセスするためReserveServiceをインジェクションする
	 */
	private final ReserveRegisterService reserveService;
	
	
	/**
	 * コンストラクタインジェクション
	 * @param reserveService
	 */
	public ReserveRegisterController(ReserveRegisterService reserveService) {
		
		this.reserveService = reserveService;
		
		
	}
	
	/**
	 * [概要]<br>
	　　　　ReserveRegisterFormをインスタンス化し、リターンする。
	 　　　セッションにキー名"registerForm"でFormオブジェクトを設定する働きとなり、
	 *  これにより、確認画面→入力画面に戻ったときに、最初に入力された値を保持して
	 *  予約入力情報を復元できる。
	 * 確認画面から入力画面に戻るときにエラーになる。
	 * 
	 * [処理内容]<br>
	 * 1.ReserveFormをインスタンス化して戻り値で返す。
	 
	 * 
	 * @return
	 */
	@ModelAttribute("registerForm")
	public ReserveForm setupForm() {
		return new ReserveForm();
	}

	/**
	 * [概要]<br>
	 * 空室予約入力画面をを表示。<br>
	 * 
	 * [処理内容]<br>
	 * 1.
	 * 2.
	 * 
	 * 
	 * 
	 * @param registerForm　セッションにキー名"registerForm"でFormオブジェクトを渡す。
	 * @param result　
	 * @param model
	 * @return
	 */
	@PostMapping(params = "input")
	public String input(@ModelAttribute("registerForm")ReserveForm registerForm , BindingResult result,  Model model) {
		
		//1. ReserveRegisterService.inputを呼び出す。引数にはReserveFormを指定する。
		//　
		reserveService.input(registerForm);
		
		//2.　"/reserve/register/input"を戻り値で返し、予約入力画面を表示する。
		return "/reserve/register/input";
	}
	
	/**
	 * 入力画面からのリクエストを受け取り、確認画面を表示する
	 * 
	 * @param registerForm
	 * @param model
	 * @return
	 */
	@PostMapping(params = "confirm")
	public String confirm(@ModelAttribute("registerForm") ReserveForm registerForm, Model model) {
		
		reserveService.confirm(registerForm);
		
		//
		//model.addAttribute("registerForm", registerForm);
		return "/reserve/register/confirm";
	}
	
	@PostMapping(params = "commit")
	public String commit(@ModelAttribute("registerForm") ReserveForm registerForm,
			@AuthenticationPrincipal UserDetailsImpl info,
			RedirectAttributes redirectAttr) {
		
		
		//reserveService.registerを呼び出し、予約を実行する。戻り値に予約IDを返す。
		String id  = reserveService.register(registerForm,info.getUserInfo());
		
		//FlushScopeで予約IDをリダイレクト先に伝える
		redirectAttr.addFlashAttribute("reserveId", id);
		
		//完了画面に遷移するcompleteメソッドにリダイレクトする。直接完了画面に遷移しないこと
		return "redirect:/reserve/register?complete";
	}
	
	/**
	 * 1.、
	 * 2.
	 * 3.
	 * 
	 * 
	 * @param model 完了画面に予約IDを出力する
	 * @param reserveId FlashScopeで渡された予約ID。
	 * @param status セッションから不要なオブジェクトを取り除く
	 * @return 完了画面"/reserve/register/complete"を返す
	 */
	@GetMapping(params = "complete")
	public String complete(Model model, @ModelAttribute("reserveId") String reserveId, SessionStatus status) {
		
		
		
		
		//model.addAttributeで出力先画面に予約IDを渡す
		model.addAttribute("reserveId",reserveId);
		
		//status.setCompleteを呼び出し、セッションから予約Formを取り除く
		status.setComplete();
		
		//完了画面に遷移する
		return "/reserve/register/complete";
	}
	
	//各リクエストハンドラメソッドは処理に失敗して前に戻る処理しか書かない。
	//Controllerの全リクエストハンドラメソッドにおける機能（ユースケース）の最初からやり直しは、ここに集約
	@ExceptionHandler(UseCaseException.class)
	public ModelAndView handleUseCaseException(UseCaseException e) {
		
		//例外ハンドラメソッドはModelを引数に取れないので、ModelViewを作ってView名と値をセットして返す
		ModelAndView mod = new ModelAndView();
		
		//model.addAttributeと同じ
		mod.addObject("errormsg", e.getMessage());
		
		//リクエストハンドラメソッドでView名を返すのと同じ
		mod.setViewName("/reserve/input");
		return mod;
		
	}

}
