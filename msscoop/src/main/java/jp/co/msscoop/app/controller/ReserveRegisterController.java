package jp.co.msscoop.app.controller;

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

import jp.co.msscoop.app.dto.Room;
import jp.co.msscoop.app.exception.UseCaseException;
import jp.co.msscoop.app.form.ReserveRegisterForm;
import jp.co.msscoop.app.service.ReserveService;
import jp.co.msscoop.app.service.RoomService;
import jp.co.msscoop.app.session.UserSession;

@Controller
@SessionAttributes("registerForm")
@RequestMapping("/reserve/register")
public class ReserveRegisterController {
	
	/**
	 * 部屋情報にアクセスするためRoomServiceをインジェクションする
	 */
	private final RoomService roomService;
	
	/**
	 * 予約情報にアクセスするためReserveServiceをインジェクションする
	 */
	private final ReserveService reserveService;
	
	public ReserveRegisterController(RoomService roomService,ReserveService reserveService,UserSession userSession) {
		
		this.roomService = roomService; 
		this.reserveService = reserveService;
		
	}
	
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
	public ReserveRegisterForm setupForm() {
		return new ReserveRegisterForm();
	}

	/**
	 * 
	 * @param registerForm　セッションにキー名"registerForm"でFormオブジェクトを渡す。
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(params = "input")
	public String input(@ModelAttribute("registerForm")ReserveRegisterForm registerForm , BindingResult result,  Model model) {
		registerForm.setMeal(true);
		registerForm.setStayNumberOfPeople(1);
		model.addAttribute("registerForm", registerForm);
		return "/reserve/register/input";
	}
	
	@PostMapping(params = "confirm")
	public String confirm(@ModelAttribute("registerForm") ReserveRegisterForm registerForm, Model model) {
		Room room = roomService.findById(registerForm.getRoomId());
		//連泊はサポートしない。Room.priceがReserveのamount
		registerForm.setAmount(room.getPrice() * registerForm.getStayNumberOfPeople());
		model.addAttribute("registerForm", registerForm);
		return "/reserve/register/confirm";
	}
	
	@PostMapping(params = "commit")
	public String commit(@ModelAttribute("registerForm") ReserveRegisterForm registerForm,RedirectAttributes redirectAttr) {
		
		String id  = reserveService.register(registerForm);
		
		//予約情報をリダイレクト先に伝える
		redirectAttr.addFlashAttribute("reserveId", id);
		
		return "redirect:/reserve/register?complete";
	}
	
	@GetMapping(params = "complete")
	public String complete(Model model, @ModelAttribute("reserveId") String reserveId, SessionStatus status) {
		status.setComplete();
		model.addAttribute("reserveId",reserveId);
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
