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

@Configuration
@EnableWebSecurity
public class SpringSecuriyConfig  {
 
	
	private final UserDetailsService userDetailsService;
	
	public SpringSecuriyConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	

	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		
		
		http.userDetailsService(userDetailsService);
		
		
		//認可の設定
		http.authorizeHttpRequests( (customizer)-> customizer
				
								
				// 認証対象外のURL指定（静的ファイルURL）
				.requestMatchers("/images/**", "/css/**", "/js/**").permitAll()
				
				//認証対象外のURL指定（静的ファイル、loginページ遷移のURL）
				.requestMatchers("/login").permitAll()
				.requestMatchers("loginProcess").permitAll()
				
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
     * パスワードのエンコーダーをDIコンテナに登録する。<br>
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

