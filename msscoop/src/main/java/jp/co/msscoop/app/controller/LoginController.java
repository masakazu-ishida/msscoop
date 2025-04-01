package jp.co.msscoop.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

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


/**
 * ■ログイン処理を行う
 * 
 * ■クラスレベルで宣言するアノテーション
 * @Controller
 * @RequestMapping("/login")
 * 
 */
@Controller
@RequestMapping("")
public class LoginController {

	/**
	 * MessageSourceインターフェースのオブジェクトをインジェクションする
	 */
	private final MessageSource messageSource;
	
	/*
	 * UserSharedServiceインターフェースのオブジェクトをインジェクションする
	 * */
	private final UserSharedService service;
	
	/**
	 * SessionScopeBeanとなる
	 */
	
	
	
	/**
	 * 
	 * [概要]<br>
	 * ログ出力用の引数情報を生成し、文字列で返す<br><br>
	 * [処理内容]<br>
	 * 1.
	 * 2.
	 * 3.
	 * 4.
	 * 
	 * 
	 * 画面初期化時にLoginForm()をModelにaddAttributeする
	 * ■メソッドレベルで宣言するアノテーション
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
	public LoginController(MessageSource messageSource, UserSharedService service) {
		this.messageSource = messageSource;
		this.service = service;
		
	}
	
	
	/**
	 * ■概要
	 * ログイン画面を表示する
	 * 
	 * ■メソッドレベルで宣言するアノテーション
	 * @GetMapping("")
	 * 
	 * ■ロジック詳細
	 * ・"/common/login"を返す
	 * 
	 * @return ログイン画面を表すパス"/common/login"を返す
	 */
	@GetMapping("/login")
	public String index() {
		return "/common/login";
	}
	
	/**
	 * <p>
	 * ■概要
	 * ログインを実行する</br>
	 * 
	 * ■ロジック詳細</br>
	 * ・result.hasErrorsで入力エラーが発生した場合</br>
	 * 　ログイン画面に戻る</br>
	 * ・インジェクションしたUserSharedService.authorizeで認証を行い、戻り値を取得</br>
	 * ・authorize戻り値が非NULL（認証成功）</br>
	 * 　SessionScopeBean.setLoginUserを呼び出し、UserInfoをセット。</br>
	 * 　"redirect:/reservable/search"を返す</br>
	 * 
	 * ・authorize戻り値がNULL（認証失敗時）</br>
	 * 　キー"bus.error.authorized"をmessage.propertiesから取得し、キー"errormsg"でModelにaddAttributeする</br>
	 * 　View名"/common/login"を返す</br>
	 * </p>
	 * @param　form LoginFormを@Validatedをつけて指定
	 * @param result BindingResultを指定
	 * @param model Modelを指定
	 * @return ログイン成功："redirect:/reservable/search"　ログイン失敗："/common/login"
	 * 
	 */
	/*
	@PostMapping("/loginProcess")
	public String signIn(@Validated LoginForm form, BindingResult result, Model model) {
		
		//String型変数messageを宣言
		
		
		//入力エラーが発生した場合ログイン画面に戻る
		if(result.hasErrors()) {
			//ログイン画面に戻る
			return "/common/login"; 
		}
		//
		UserInfo user = service.authorize(form.getUserId(), form.getPassword());
		//
		if(user == null) {
			
			String errorMessage = messageSource.getMessage("bus.error.authorized", null, Locale.JAPAN);
			
			model.addAttribute("errormsg", errorMessage);
			
			//ログイン画面に戻る
			return "/common/login";
		}
		else {
			
			userSession.addLoginUser(user);
			//model.addAttribute("userSession", userSession);
			return "redirect:/reservable/search"; 
		}
	}
	*/
}
