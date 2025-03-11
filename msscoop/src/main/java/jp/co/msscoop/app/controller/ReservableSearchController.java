package jp.co.msscoop.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import jp.co.msscoop.app.dto.ReservableRoomInfo;
import jp.co.msscoop.app.exception.BusinessException;
import jp.co.msscoop.app.exception.UseCaseException;
import jp.co.msscoop.app.form.ReservableSearchForm;
import jp.co.msscoop.app.service.ReservableSearchService;
import jp.co.msscoop.app.session.ReservableSearchFormSession;

@Controller
//@SessionAttributes("reservableSearchForm")
@RequestMapping("/reservable/search")
public class ReservableSearchController {
	/**
	 * 
	 */
	private final ReservableSearchService service;
	
	
	
	private final ReservableSearchFormSession searchFormSession;

	/**
	 * 
	 */
	private final MessageSource messageSource;

	/**
	 * 
	 * @param service
	 * @param messageSource
	 */
	public ReservableSearchController(MessageSource messageSource,ReservableSearchService service,ReservableSearchFormSession searchFormSession) {
		this.service = service;
		this.messageSource = messageSource;
		this.searchFormSession = searchFormSession;
	}

	
	/**
	 * 空室検索画面を表示
	 * 
	 * @return
	 */
	@GetMapping("")
	public String index(Model model) {
		
		
		ReservableSearchForm searchForm = searchFormSession.getSearchForm();
		//一度も検索を実行していない場合、検索条件が空なのでインスタンス化
		if(searchForm == null) {
			searchForm = new ReservableSearchForm();
			searchForm.setInDoorBath(false);
			searchForm.setSmoking(false);
			searchForm.setCheckin(LocalDate.now());
			searchForm.setCheckout(LocalDate.now().plusDays(1));
		}
		model.addAttribute("reservableSearchForm", searchForm);
		return "/reservable/reservableSearch";

	}

	/**
	 * 検索条件を元に空室検索を実行し、結果を一覧に表示する
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping(params = "execute")
	public String search(ReservableSearchForm form, Model model) {
		try {
			List<ReservableRoomInfo> reservableRoomList = service.search(form.getCheckin(), form.getCheckout(),
					form.isInDoorBath(), form.isSmoking());

			if (reservableRoomList.isEmpty()) {
				String errorMesage = messageSource.getMessage("bus.error.searchresultzero", null, Locale.JAPAN);
				model.addAttribute("errormsg", errorMesage);
				return "/reservable/reservableSearch";
			}
			//次回検索に備えて検索条件をセッションに格納
			searchFormSession.addSearchForm(form);
			
			
			model.addAttribute("reservableRoomList", reservableRoomList);
			return "/reservable/reservableSearchResult";
		} catch (BusinessException e) {
			// model.addAttributeと同じ
			model.addAttribute("errormsg", e.getMessage());

			// リクエストハンドラメソッドでView名を返すのと同じ
			return "/reservable/reservableSearch";
		}
	}


	// 各リクエストハンドラメソッドは処理に失敗して前に戻る処理しか書かない。
	// Controllerの全リクエストハンドラメソッドにおける機能（ユースケース）の最初からやり直しは、ここに集約
	/**
	 * 
	 * @param e 例外クラスが引き渡されてSpringによって呼び出される。引数を例外のスーパークラス
	 *          Throwableにすることで、Exception・RunTimeException両方の例外を処理する。
	 * 
	 *          Exception・・・・・・・・・・・致命的エラー RunTimeException・・・ユースケースエラー
	 * @return
	 */
	@ExceptionHandler
	public ModelAndView handleException(Throwable e) {

		// 例外ハンドラメソッドはModelを引数に取れないので、ModelViewを作ってView名と値をセットして返す
		ModelAndView mod = new ModelAndView();

		// 例外の種別を判断してユースケース例外であれば、ユースケースの先頭に戻ってやり直し
		if (e instanceof UseCaseException || e instanceof BusinessException) {
			// model.addAttributeと同じ
			mod.addObject("errormsg", e.getMessage());

			// リクエストハンドラメソッドでView名を返すのと同じ
			mod.setViewName("/reservable/reservableSearch");
			return mod;
		} else {
			// 致命的エラー発生でエラー専用画面に戻る
			mod.addObject("errormsg", e.getMessage());
			mod.setViewName("/commonn/error");
			return mod;
		}

	}
}
