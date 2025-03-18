package jp.co.msscoop.app.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;


/**
 * @Comoponentはつけない！BeanConfiureでapplitaion.propertiesが『boot.mode=develop』
 * の時にこのクラスを有効化する
 */
public class SystemDateUtilFormDevelop implements SystemDateUtil{
	@Override
	public LocalDate today() {
		// TODO 自動生成されたメソッド・スタブ
		String strDate = "2025/01/05";
		return LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));	
	}

}
