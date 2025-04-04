package jp.co.msscoop.app.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * [概要]<br>
 * Bean定義ファイルの代わり。AP起動時に読み込まれる。
 * 
 */
@Configuration
public class BeanConfiure {
	
	/**
	 * application.propertiesのboot.modeの
	 * 設定値を格納。値は『production』『develop』を設定
	 * */
	@Value("${boot.mode}")
    String mode;
	
	/**
	 * [概要]<br>
	 * メンバ変数modeの値によって、今日の日付を固定・システム日付を返す。<br><br>
	 * [処理内容]<br>
	 * 1.メンバ変数modeの値が"developかどうか条件判断"する<br>
	 *   1.1 条件式が真の場合：SystemDateUtilFormDevelopをnew演算子でインスタス化してリターン<br>
	 *   1.2 条件式が偽でかつproductの場合：SystemDateUtilImplをnew演算子でインスタス化してリターン<br>
	 *   1.3 条件式が偽で上記いずれでもない場合、SystemDateUtilImplをnew演算子でインスタス化してリターン<br>
	 * 
	 * @return SystemDateUtilの実装クラスをインスタンス化してリターン
	 */
	@Bean
	SystemDateUtil getSystemDateUtil() {
	
		if(mode.equals("develop")) {
			//今日が何日かを単体テスト用に固定日付(2025/1/05)とする
			return new SystemDateUtilFormDevelop();
		}
		else if(mode.equals("product")) {
			//今日が何日かを本番用にシステム日付とする
			return new SystemDateUtilImpl();
		}
		else {
			return new SystemDateUtilImpl();
		}
	}
}
