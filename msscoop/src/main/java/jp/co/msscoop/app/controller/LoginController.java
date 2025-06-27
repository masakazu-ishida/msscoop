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


import jp.co.msscoop.app.form.LoginForm;


/**
 * [概要]<br>
 * SpringSecurityがログイン処理を行うにあたり、ログイン画面を表示と、ログインフォームオブジェクトの初期化を行う
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	
	/**
	 * 
	 * [概要]<br>
	 * ログインフォームを初期化する<br><br>
	 * [処理内容]<br>
	 * 1.LoginFormをインスタンス化して返す。初期値は不要
	 * @return　LoginFormをインスタンス化して返す。
	 */
	@ModelAttribute
	LoginForm setupForm() {
		return new LoginForm();
	}
	

	
	/**
	 * [概要]<br>
	 * ログイン画面を表示する
	 * 
	 * [処理内容]<br>
	 * 1. "/common/login"を返す
	 * 
	 * @return ログイン画面を表すパス"/common/login"を返す
	 */
	@GetMapping("")
	public String index() {
		return "/common/login";
	}
	

}
