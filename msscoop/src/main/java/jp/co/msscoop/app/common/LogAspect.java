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
 *　Controllerのメソッドが呼ばれる度に、ログにメソッド名・引数・引数の値を出力するAOP
 */
//@Aspect
//@Component
public class LogAspect {
	
	/**
	 * ログを初期化する。LogManager.getLogger(LogAspect.class);を呼び出し、
	 * メンバ変数を初期化する
	 */
	private Logger logger  = LogManager.getLogger(LogAspect.class);
	
	/**
	 * 
	 * 
	 * [概要]<br>
	 * Controllerのリクエストハンドラが呼ばれる前に前処理として実行されるメソッド情報をログ出力する。<br><br>
	 * 
	 * [処理内容]<br>
	 * 1.Logger.infoでログメッセージ『"メソッド開始：』と、JoinPoint.getSignature()でメソッドのシグニチャを連結して出力<br>
	 * 2.createParameterInfoで引数情報を取得し、String型変数paramStringにセット<br>
	 * 3.Logger.infoでログメッセージ『"引数情報：』と、paramStringで引数情報を出力<br>
	 * 4.出力例：メソッド開始：LoinController.index(Model)<br>
	 * 
	 * 
	 * @param jp　メソッド・引数情報を表す
	 */
	@Before("execution(* jp.co.msscoop.app.controller.*Controller.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("メソッド開始：" + jp.getSignature());
		String paramString = createParameterInfo(jp);
		logger.info("引数情報：" + paramString);
		
	}
	
	
	/**
	 [概要]<br>
	 * Controllerのリクエストハンドラが呼ばれた後に後処理として実行されるメソッド情報をログ出力する。<br><br>
	 * 
	 * [処理内容]<br>
	 * 1.Logger.infoでログメッセージ『"メソッド終了：』と、JoinPoint.getSignature()でメソッドのシグニチャを連結して出力<br>
	 * 1.Logger.infoでログメッセージ『"戻り値：』と引数resturnValueを連結して出力<br>
	 * 
	 * @param jp メソッド・引数情報を表す
	 * @param resturnValue 戻り値情報を表す
	 */
	@AfterReturning(value="execution(* jp.co.msscoop.app.controller.*Controller.*(..))", returning = "resturnValue")
	public void endLog(JoinPoint jp, Object resturnValue) {
		logger.info("メソッド終了：" + jp.getSignature());
		logger.info("戻り値 " +  resturnValue);
	}


	/**
	 * [概要]<br>
	 * ログ出力用の引数情報(呼ばれたメソッドの引数情報)を生成し、文字列で返す<br>
	 * 出力形式の例
	 * 　　第１引数　型：String型　値：Hello
	 * 　　第２引数　型：Integer型　値：123
	 * 
	 * <br>
	 * [処理内容]<br>
	 * 1.String型変数paramStringを宣言<br>
	 * 2.Object型の配列型変数argsを宣言<br>
	 * 3.JoinPoint.getArgsで引数のオブジェクトをjava.lang.Object型配列で取得し、argsに設定<br>
	 * 4.argsの要素数分、ループを実行する<br>
	 *  4.1 argsの添え字にループカウンタを指定し、引数情報をjava.lang.Object型で取得し、変数paramに設定<br>
	 *  4.2 param.getClass().toString()で引数の型名を取得し、param.toString()で引数の値を取り出し、これを文字列連結する。<br>
	 *  4.3 出力形式のように出力するにはString.formatを使用する。例：String.format("第%d引数 ", paramNumber)
	 *  4.3 4.2で文字列連結した結果を、変数paramStringに+=で追加する<br>
	 * 5.paramStringを戻り値で返す<br>
	 * @param jp メソッド・引数情報を表す
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
