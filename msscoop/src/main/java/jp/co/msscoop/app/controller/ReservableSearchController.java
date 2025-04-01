package jp.co.msscoop.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import jp.co.msscoop.app.common.SystemDateUtil;
import jp.co.msscoop.app.dto.ReservableRoomInfo;
import jp.co.msscoop.app.exception.BusinessException;
import jp.co.msscoop.app.exception.UseCaseException;
import jp.co.msscoop.app.form.ReservableSearchForm;
import jp.co.msscoop.app.service.ReservableSearchService;
import jp.co.msscoop.app.session.ReservableSearchFormSession;


/**
 * 空室検索機能用コントローラ
 * 
 */
@Controller
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

	
	private final SystemDateUtil systemDateUtil; 
	/**
	 * 
	 * @param service
	 * @param messageSource
	 */
	public ReservableSearchController(MessageSource messageSource,ReservableSearchService service,ReservableSearchFormSession searchFormSession,SystemDateUtil systemDateUtil) {
		this.service = service;
		this.messageSource = messageSource;
		this.searchFormSession = searchFormSession;
		this.systemDateUtil =systemDateUtil;
	}

	
	/**
	 * 
	 * [概要]<br>
	 * 空室検索画面を表示。<br>
	 * セッションに検索フォームが設定されていない場合、インスタンス化して検索画面表示字の初期値を設定する<br><br>
	 * 
	 * [処理内容]<br>
	 * 1.ReservableSearchFormg型変数searchFormを宣言し、searchFormSession.getSearchForm()で初期化する。<br>
	 * 2.getSearchFormの戻り値がNULLかどうか（セッションに検索フォームが格納されているかどうか）条件判断する。<br>
	 * 　　2.1 条件が真の場合(戻り値がNULLなら真)ReservableSearchFormgをnewでインスタンス化し、searchFormに設定する。<br>
	 *  　2.2 searchForm.setInDoorBathを呼び出し、『内風呂あり』を初期値に設定する。<br>
	 *   2.3 searchForm.setSmokingを呼び出し、『喫煙不可能』を初期値に設定する。<br>
	 *   2.4　searchForm.setCheckinに今日の日付＋１の日付を指定する。今日の日付はsystemDateUtil.todayで取得する。<br>
	 *   2.5　searchForm.setCheckoutに今日の日付＋２の日付を指定する。今日の日付はsystemDateUtil.todayで取得する。<br>
	 *  
	 * 3.model.addAttributeを呼び出す。第一引数にはキー名"reservableSearchForm"を、第二引数にsearchFormを指定する。<br> 
	 * 4.戻り値に"/reservable/reservableSearch"を指定して返す。<br>
	 * 
	 * 
	 * @param model Modelオブジェクト
	 * @return "/reservable/reservableSearch"を返す
	 */
	@GetMapping("")
	public String index(Model model) {
		
		//ReservableSearchFormの変数searchFormを宣言
		//searchFormSession.getSearchFormを呼び出しし、戻り値をsearchFormに設定する。
		ReservableSearchForm searchForm = searchFormSession.getSearchForm();
		//searchFormがNULLかどうか（一度も検索を実行していない場合、getSearchFormはNULLを返す）条件判定する
		if(searchForm == null) {
			//searchFormがNULLの場合
			//ReservableSearchFormをインスタンス化し、変数searchFormにセット
			searchForm = new ReservableSearchForm();
			
			//searchForm.setInDoorBathに引数trueをセット（初期値は内ｗ風呂あり）
			searchForm.setInDoorBath(true);
			//searchForm.setSmokingに引数falseをセット（初期値は禁煙）
			searchForm.setSmoking(false);
			
			//デフォルトではチェックイン日付は明日、チェックアウト日付は明後日でカレンダーを初期化する
			searchForm.setCheckin( systemDateUtil.today().plusDays(1) );
			searchForm.setCheckout(systemDateUtil.today().plusDays(2));
		}
		
		//model.addAttributeを呼び出し、キー名は『reservableSearchForm』、第二引数にsearchFormをセット
		model.addAttribute("reservableSearchForm", searchForm);
		
		//"/reservable/reservableSearch"をリターンする
		return "/reservable/reservableSearch";

	}
	

	@PostMapping("")
	public String index2(Model model) {
		
		return index(model);

	}
	
	
	
	

	/**
	 * [概要]<br>
	 * 空室検索を実行<br>
	 * 
	 * [処理内容]<br>
	 * 1.空室検索用フォームに
	 * 2.
	 * 3.
	 *
	 * @param form 
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(params = "execute")
	public String search(@Valid ReservableSearchForm form, BindingResult result, Model model) {
		try {
			
			//1.空室検索フォームの入力チェックを実行する
			if(result.hasErrors()) {
				//1.1もしエラーがあれば、"/reservable/reservableSearch"を返し、空室検索画面に戻る
				return "/reservable/reservableSearch";
			}
			
			//2.ReservableSearchServiceのsearchメソッドを呼び出し、空室検索を実行する。<br>
			//引数に空室検索フォームのチェックイン日付・チェックアウト日付、内風呂・禁煙を指定する
			List<ReservableRoomInfo> reservableRoomList = service.search(form.getCheckin(), form.getCheckout(),
					form.isInDoorBath(), form.isSmoking());

			
			//3.ReservableSearchFormSession.addSearchFormを呼び出し、次回検索に備えて検索条件フォームをセッションに格納。
			searchFormSession.addSearchForm(form);
			
			//4.Model.addAttributeを呼び出す。第一引数にキー"reservableRoomList"を指定し、第二引数に空室検索結果を指定
			model.addAttribute("reservableRoomList", reservableRoomList);
			
			//5."/reservable/reservableSearchResult"を返す
			return "/reservable/reservableSearchResult";
		} catch (BusinessException e) {
			//6.catchブロックを作成。引数にはBusinessExceptionを指定
			
			//7.例外ハンドラを実行。Model.addAttributeを呼び出す。第一引数にキー"errormsg"を指定し、第二引数にBusinessException.getMessageを呼び出す
			model.addAttribute("errormsg", e.getMessage());

			//8. リクエストハンドラメソッドでView名を返すのと同じ
			return "/reservable/reservableSearch";
		}
	}


	// 各リクエストハンドラメソッドは処理に失敗して前に戻る処理しか書かない。
	// Controllerの全リクエストハンドラメソッドにおける機能（ユースケース）の最初からやり直しは、ここに集約
	/**
	 * 
	 * @param e 
	 * @return
	 */
	
	
	
	/**
	 * 
	 * [概要]<br>
	 * 各リクエストハンドラメソッドで補足しきれない処理を記述する。
	 * 
	 * 
	 * [処理内容]<br>
	 * 1.
	 * 2.
	 * 3.
	 *
	 * 
	 * @param e リクエストハンドラメソッドで補足しきれない例外。例外クラスが引き渡されてSpringによって呼び出される。引数を例外のスーパークラス
	 *          Throwableにすることで、Exception・RunTimeException両方の例外を処理する。
	 * 
	 *          Exception・・・・・・・・・・・致命的エラー RunTimeException・・・ユースケースエラー
	 * @return 遷移先と遷移先に引き渡すエラーメッセージを指定
	 */
	@ExceptionHandler
	public ModelAndView handleException(Throwable e) {

		// 1.例外ハンドラメソッドはModelを引数に取れないので、ModelViewを作ってView名と値をセットして返す
		ModelAndView mod = new ModelAndView();

		// 2.例外の種別を判断。UseCaseExceptionまたはBusinessExceptionかどうかを条件判断する。
		if (e instanceof UseCaseException || e instanceof BusinessException) {		
			// 2.1 trueの時の処理（UseCaseExceptionまたはBusinessExceptionの時）
			// 2.1.1 ModelAndView.addObjectを呼び出す。意味は【model.addAttribute】と同じ。第一引数にキー"errormsg"を指定し、第二引数にThrowable.getMessageを呼び出す
			mod.addObject("errormsg", e.getMessage());

			// 2.1.2 ModelAndView.setViewNameを呼び出す。引数には"/reservable/reservableSearch"を指定。リクエストハンドラメソッドでView名を返すのと同じ
			mod.setViewName("/reservable/reservableSearch");
			
			//2.1.3 で作成したModelAndViewインスタンスをリターン
			return mod;
		} else {
			
			// 2.2 falseの時の処理（UseCaseExceptionまたはBusinessExceptionでない場合、致命的エラーの場合)

			// 2.2.1 ModelAndView.addObjectを呼び出す。意味は【model.addAttribute】と同じ。第一引数にキー"errormsg"を指定し、第二引数にThrowable.getMessageを呼び出す
			mod.addObject("errormsg", e.getMessage());
			
			//2.2.2 ModelAndView.setViewNameを呼び出す。引数には"/commonn/error"を指定。リクエストハンドラメソッドでView名を返すのと同じ
			mod.setViewName("/commonn/error");
			
			//2.2.3 で作成したModelAndViewインスタンスをリターン
			return mod;
		}

	}
}
