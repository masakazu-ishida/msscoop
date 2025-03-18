package jp.co.msscoop.app.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * Bean定義ファイルの代わり。
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
	 * application.propertiesのboot.modeの値によって
	 * 適切にインスタンス化して戻り値で返す
	 * boot.modeの値がproduction：SystemDateUtilFormDevelopをnew演算子でインスタス化してリターン
	 * boot.modeの値がdevelop：SystemDateUtilImplをnew演算子でインスタス化してリターン
	 * いずれでもない場合、SystemDateUtilImplをnew演算子でインスタス化してリターン
	 * 
	 * @return SystemDateUtilの実装クラスをリターン
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
