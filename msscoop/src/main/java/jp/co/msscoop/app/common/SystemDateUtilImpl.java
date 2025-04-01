package jp.co.msscoop.app.common;

import java.time.LocalDate;


//アノテーション

/**
 * 『@Comoponent』はつけない！<br>
 * 日付取得用クラス
 * 
 */
public class SystemDateUtilImpl implements SystemDateUtil {

	/**
	 BeanConfiureでapplitaion.propertiesが『boot.mode=product』<br>
	 * の時にこのクラスを有効化する。本番用で日付は常にOSのシステム日付を返す
	 */
	@Override
	public LocalDate today() {
		// TODO 自動生成されたメソッド・スタブ
		return LocalDate.now();
	}

}
