package jp.co.msscoop.app.common;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.msscoop.app.session.UserSession;

/**
 * リクエストの度に呼び出されるフィルター
 * セッションが有効かどうか確認する
 */
@Component
public class RequestFilter  implements Filter{
	
	/**
	 * SessionScopeBeanをインジェクションする
	 */
	@Autowired
	UserSession session;
	
	/**
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//HttpServletRequestで変数を宣言し、doFilterの引数ServletRequestをHttpServletRequestにキャストする
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		
		//String型のrequestURIを変数宣言し、初期値をのHttpServletRequest.getRequestURIメソッドの戻り値でセット
		String requestURI = httpServletRequest.getRequestURI();
		
		//もしgetRequestURIの戻り値が"/login"・"/logout"の場合、FilterChain.doFilterを呼び出す
		if(requestURI.indexOf("/login") != -1 || requestURI.indexOf("/logout") != -1) {
			chain.doFilter(request, response);
		}
		//さもなくば
		else {
			//もしメンバ変数session.getLoginUserを呼び出し、戻り値がnullならば"/login"にリダイレクト
			//doFilterの引数responseをHttpServletResponseにキャストして、sendReidirectを呼び出す
			if(session.getLoginUser() == null) {
				((HttpServletResponse)response).sendRedirect("/login");
			}
			//さもなくばFilterChain.doFilterを呼び出す
			else {
				chain.doFilter(request, response);
			}
			
		}
	}
}
