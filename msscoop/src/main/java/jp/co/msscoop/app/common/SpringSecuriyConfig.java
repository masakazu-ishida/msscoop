package jp.co.msscoop.app.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
	    builder.userDetailsService(userDetailsService);
	    AuthenticationManager manager = builder.build();
	
	    http.authenticationManager(manager)
	        .authorizeHttpRequests((auth) -> auth
	            // 認証対象外のURL指定（静的ファイル、エラーページ遷移のURL）
	        .requestMatchers("/images/**", "/css/**", "/js/**").permitAll()
	        // ログイン画面のURLも認証対象外とする
	        .requestMatchers("/login").permitAll()
	        // 上記以外は認証が必要
	        .anyRequest().authenticated());
	
		// フォーム認証の設定
		http.formLogin((formLogin) -> formLogin
		    // フォーム認証のログイン画面のURL
		    .loginPage("/login")
		    // 認証成功時に遷移するURL
		    .defaultSuccessUrl("/reservable/search"));
		    // Form認証成功時にユーザオブジェクトを構築するハンドラーを追加する
		    
		
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