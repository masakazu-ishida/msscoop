package jp.co.msscoop.app.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;


/**
 * 『@Comoponent』はつけない！<br>
 * 日付取得用クラス
 * 
 */
public class SystemDateUtilFormDevelop implements SystemDateUtil{
	
	/**
	 BeanConfiureでapplitaion.propertiesが『boot.mode=develop』<br>
	 * の時にこのクラスを有効化する。単体テスト用で日付は常に2025/01/05を返す
	 */
	@Override
	public LocalDate today() {
		// TODO 自動生成されたメソッド・スタブ
		String strDate = "2025/01/05";
		return LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));	
	}

}
