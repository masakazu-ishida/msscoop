package jp.co.msscoop.app.common;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AOPを実装。Controllerのメソッドが呼ばれる度に、ログにメソッド名・引数・引数の値を出力
 */
@Aspect
@Component
public class LogAspect {
	
	/**
	 * ログを初期化する。LogManager.getLogger(LogAspect.class);を呼び出し、
	 * メンバ変数を初期化する
	 */
	private Logger logger  = LogManager.getLogger(LogAspect.class);
	/**
	 * 
	 * execution(戻り値 パッケージ名.クラス名.メソッド名(引数))
	 * @param jp
	 */
	@Before("execution(* jp.co.msscoop.app.controller.*Controller.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("メソッド開始：" + jp.getSignature());
		String paramString = createParameterInfo(jp);
		logger.info("引数情報：" + paramString);
		
	}
	
	
	@AfterReturning(value="execution(* jp.co.msscoop.app.controller.*Controller.*(..))", returning = "resturnValue")
	public void endLog(JoinPoint jp, Object resturnValue) {
		logger.info("メソッド終了：" + jp.getSignature());
		logger.info("戻り値 " +  resturnValue);
	}


	/**
	 * ログ出力用の引数情報を生成
	 * @param jp
	 * @return
	 */
	private String createParameterInfo(JoinPoint jp) {
		String paramString = "";
		Object []args = jp.getArgs();
		for(int idx = 0; idx <  args.length; idx++) {
			var param = args[idx];
			paramString += String.format("第%d引数 ", idx + 1) + " 【型】:" + param.getClass().toString() + " " + "【値】:" + param.toString() + "\r";
		}
		return paramString;
	}
	
	
	

}
