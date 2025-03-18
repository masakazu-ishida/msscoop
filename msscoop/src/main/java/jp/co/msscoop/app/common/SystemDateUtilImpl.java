package jp.co.msscoop.app.common;

import java.time.LocalDate;


//アノテーション

/**
 * @Comoponentはつけない！BeanConfiureでapplitaion.propertiesが『boot.mode=product』
 * の時にこのクラスを有効化する
 */
public class SystemDateUtilImpl implements SystemDateUtil {

	@Override
	public LocalDate today() {
		// TODO 自動生成されたメソッド・スタブ
		return LocalDate.now();
	}

}
