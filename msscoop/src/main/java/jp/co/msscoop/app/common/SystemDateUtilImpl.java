package jp.co.msscoop.app.common;

import java.time.LocalDate;


//アノテーション

/**
 * 【概要】
 * 日付取得用クラス。システム日付から取得する。 『@Comoponent』はつけない！<br>
 *
 */
public class SystemDateUtilImpl implements SystemDateUtil {

	/**
	 * 
	 * 【概要】
	 * システム日付を取得し、戻り値で返す
	 * 
	 * @return システム日付を返す
	 */
	@Override
	public LocalDate today() {
		// TODO 自動生成されたメソッド・スタブ
		return LocalDate.now();
	}

}
