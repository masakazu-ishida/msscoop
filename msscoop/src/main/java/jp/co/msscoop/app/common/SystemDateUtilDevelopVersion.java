package jp.co.msscoop.app.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;


@Component
public class SystemDateUtilDevelopVersion implements SystemDateUtil{
	@Override
	public LocalDate today() {
		// TODO 自動生成されたメソッド・スタブ
		String strDate = "2025/01/10";
		return LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));	
	}

}
