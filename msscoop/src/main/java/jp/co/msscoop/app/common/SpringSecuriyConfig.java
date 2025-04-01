package jp.co.msscoop.app.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SecurityContex.xmlの代わり。XMLで指定する代わりに設定情報をプログラムで記述する
 * 
 * 
 */
@Configuration
@EnableWebSecurity
public class SpringSecuriyConfig  {
 
	
	/**
	 * UserDetailsServiceをインジェクション。SpringSecurity初期化の過程で必要
	 */
	private final UserDetailsService userDetailsService;
	
	public SpringSecuriyConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	

	
	
	/**
	 * [処理概要]<br>
	 * SpringSecurity初期化を記述<br>
	 * 
	 * [処理内容]<br>
	 * 1.HttpSecurity.authorizeHttpRequestsで認可情報を設定する。引数Customizerをラムダ式を使って初期化する。呼び出しはストリーム形式で呼び出す。<br>
	 * 　1.1 ラムダ式：requestMatchersの引数に『"/images/**", "/css/**", "/js/**"』を指定し、permitAll()を呼び出す。このパスは認証なしで呼び出し可能であることを指示する。<br>
	 * 　1.2 ラムダ式：requestMatchersの引数に『"/login"』を指定し、permitAll()を呼び出す。このパスは認証なしで呼び出し可能であることを指示する。<br>
	 * 　1.3 ラムダ式：requestMatchersの引数に『"/loginProcess"』を指定し、permitAll()を呼び出しす。このパスは認証なしで呼び出し可能であることを指示する。<br>
	 * 　1.4 ラムダ式：requestMatchersの引数に『"/admin"』を指定し、hasRole("ADMIN")を呼び出し、ADMINロール以外のアクセスを制限する<br>
	 * 　1.5 ラムダ式：anyRequest().authenticated()で、上記設定以外が全て認証が必要な事を指示する。<br>
	 * <br>
	 * 2.HttpSecurity.formLoginで認証情報を設定する。引数Customizerをラムダ式を使って初期化する。呼び出しはストリーム形式で呼び出す。<br>
	 * 　2.1 ラムダ式：usernameParameter("userId")でユーザIDのリクエストパラメータ名を指示する<br>
	 * 　2.2 ラムダ式：passwordParameter("password")でパスワードのリクエストパラメータ名を指示する<br>　　
	 * 　2.3 ラムダ式：loginPage("/login") でログイン画面表示パスを指示する<br>  　　
	 * 　2.4 ラムダ式：loginProcessingUrl("/loginProcess") でログイン認証処理パスを指示する<br>
	 * 　2.5 ラムダ式：defaultSuccessUrl("/reservable/search",true)で認証処理成功時のリダイレクトパスを指定<br>  
	 * 　2.6 ラムダ式：failureUrl("/login?error")で認証失敗時のリダイレクトパスを指定<br>
	 * <br>
	 * 3.HttpSecurity.sessionManagementでログインセッションの扱いを引数Customizerをラムダ式を使って初期化する。呼び出しはストリーム形式で呼び出す。<br>
	 *  3.1 ラムダ式：enableSessionUrlRewriting(false)でURLライティング（セッションIDをURLに設定する事）を禁止する<br>
	 *  3.2 ラムダ式：maximumSessions(1)で同一ユーザ情報での最大セッション数を指定。既に同一ユーザでログインを実行した場合後勝ち（古いセッションは破棄）となる。<br>
	 * <br>
	 * 
	 * 
	 * 4.HttpSecurity.logoutでログアウト情報をCustomizerをラムダ式を使って初期化する。呼び出しはストリーム形式で呼び出す。<br>
	 * 　4.1 ラムダ式：logoutRequestMatcher(new AntPathRequestMatcher("/logout"))でログアウトのURLを指定する。<br>
	 *  4.2 ラムダ式：logoutSuccessUrl("/login")でログアウト成功時のリダイレクトパスを指定 <br>
	 *  4.3 ラムダ式：deleteCookies("JSESSIONID")でログアウト成功時のクッキー削除を市営<br>
	 *  4.4 ラムダ式：invalidateHttpSession(true).permitAll()でログアウト成功時のセッションクリアを指示<br>
	 *  
	 * 5.HttpSecurity.userDetailsServiceで認証情報取得サービスuserDetailsServiceを引数に指定して呼び出す。
	 *  
	 * @param http　このクラスのメソッドで初期化を実行。認証・FORM設定・セッション設定・ログアウト設定を行うメソッドを呼び出し、引数Customizerをラムダ式を使って初期化する
	 * @return　メソッドの最後にHttpSecurity.build()を呼び出し、　SecurityFilterChainを返す。
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.userDetailsService(userDetailsService);
		//認可の設定
		http.authorizeHttpRequests( (customizer)-> customizer
				
								
				// 認証対象外のURL指定（静的ファイルURL）
				.requestMatchers("/images/**", "/css/**", "/js/**").permitAll()
				
				//認証対象外のURL指定（静的ファイル、loginページ遷移のURL）
				.requestMatchers("/login").permitAll()
				.requestMatchers("/loginProcess").permitAll()
				
				// admiはADMINロール以外アクセス不可能
		        .requestMatchers("/admin").hasRole("ADMIN")		
		        
		        
		        // 上記以外は認証が必要
		        .anyRequest().authenticated()		
		);
		
		
	
		// フォーム認証の設定
		http.formLogin((formLogin) -> formLogin
			//リクエストパラメータのユーザIDの名前
			.usernameParameter("userId")
			//リクエストパラメータのパスワードを名前
			.passwordParameter("password")
		    // フォーム認証のログイン画面のURL
		    .loginPage("/login")  
		    //ログイン処理のURL
		    .loginProcessingUrl("/loginProcess")
            // 認証成功時に遷移するURL
		    .defaultSuccessUrl("/reservable/search",true)
		 	//失敗した時のURL
	    	.failureUrl("/login?error")
	    	//認証サービスの設定
			
				
			);
		    
		
		
		http.sessionManagement((cusstomizer)->cusstomizer
				
				.enableSessionUrlRewriting(false)
				.maximumSessions(1)
				);
		
		
		
		
		
		// ログアウト処理の設定
		http.logout((logout) -> logout
		    // ログアウトのURL
		    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		    // ログアウト成功時のURL（ログイン画面に遷移）
		    .logoutSuccessUrl("/login")
		   
		    // Cookieの値を削除する
		    .deleteCookies("JSESSIONID")
		    // セッションを無効化する
		    .invalidateHttpSession(true).permitAll());
		
		    return http.build();
	}
	
	/**
	 * パスワードのエンコーダーをDIコンテナに登録する。
	 * @return エンコーダーBCryptPasswordEncoderをインスタンス化して返す
	 */
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

