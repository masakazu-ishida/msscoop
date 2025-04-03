package jp.co.msscoop.app.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;


/**
 * 【概要】
 * 日付取得用クラス。テストに使用するため固定日付を取得する。 『@Comoponent』はつけない！<br>
 *
 */
public class SystemDateUtilFormDevelop implements SystemDateUtil{
	
	/**
	 * 【概要】
	 * 日付は常に2025/01/05を返す
	 * 
	 * @return
	 */
	@Override
	public LocalDate today() {
		// TODO 自動生成されたメソッド・スタブ
		String strDate = "2025/01/05";
		return LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));	
	}

}
