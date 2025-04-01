package jp.co.msscoop.app.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
* 
* 今日の日付を固定・システム日付を返す。<br>
*/
public interface SystemDateUtil {
	
	/**
	 * アプリケーションの稼働日を取得する
	 * @return　アプリケーションの稼働日をyyy-MM-ddのLocalDateで返す
	 */
	public LocalDate today();
}
