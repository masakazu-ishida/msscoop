package jp.co.msscoop.app.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import jp.co.msscoop.app.common.SystemDateUtil;
import jp.co.msscoop.app.dao.ReservableRoomInfoDAO;
import jp.co.msscoop.app.dto.ReservableRoomInfo;
import jp.co.msscoop.app.exception.BusinessException;

/**
 * [概要]<br>
 * 予約可能機能に対するサービスインターフェース実装クラス
 */
@Service
public class ReservableSearchServiceImpl implements ReservableSearchService {

	/**
	 * messages.propertiesからメッセージを取得するインターフェース
	 */
	private final MessageSource messageSource;
	
	/**
	 * 日付を取得するインターフェース
	 */
	private final SystemDateUtil systemDateUtil;
	
	/**
	 * 
	 *　空室検索テーブルにアクセスするDAOインターフェース
	 */
	private final ReservableRoomInfoDAO reservableRoomInfoDAO;
	
	
	/**
	 * * [概要]
	 * 空室検索に必要なオブジェクトのインターフェースをコンストラクタインジェクションする
	 * 
	 * @param reservableRoomInfoDAO
	 * @param messageSource
	 * @param systemDateUtil
	 */
	public ReservableSearchServiceImpl(ReservableRoomInfoDAO reservableRoomInfoDAO,MessageSource messageSource,SystemDateUtil systemDateUtil){
		this.messageSource = messageSource;
		this.reservableRoomInfoDAO = reservableRoomInfoDAO;
		this.systemDateUtil = systemDateUtil;
		
	}
	
	/**
	 * [概要]<br>
	 * 空室検索を実行する<br>
	 * [処理内容]<br>
	 * 1. long型変数diffDaysをチェックイン日付・チェックアウト日付の差分で初期化。<br>
	 * 2. 差分の取得はChronoUnit.DAYS.betweenメソッドを呼び出す。<br>
	 * 		第一引数：チェックイン日付<br>
	 * 		第二引数：チェックアウト日付<br>
	 * 3. 差分が0と等しいかを条件判定(チェックインインとチェックアウトが同一日付かどうか条件判定)<br>
	 *   3.1 trueの時の処理<br>
	 *	 3.1.1 String型変数messageを宣言。<br>
	 *	 3.1.2 messageSource.getMessageを呼び出し、プロパティファイルmessages.propertiesからキー"bus.error.sameday"のメッセージを取得し、変数messageを初期化<br>
	 * 	 3.1.3 引数messageで初期値を与えてBusinessExceptionをインスタンス化し、throwする。（Controllerでキャッチして空室検索画面に戻る）<br>
	 * 4. 差分が0未満かどうかを条件判定(チェックアウトがチェックインインより過去日付の場合前の画面に戻る)<br>
	 * 	 4.1 trueの時の処理<br>
	 *	 4.1.1 String型変数messageを宣言。<br>
	 *	 4.1.2 messageSource.getMessageを呼び出し、プロパティファイルmessages.propertiesからキー"bus.error.pastday"のメッセージを取得し、変数messageを初期化<br>
	 *	 4.1.3 引数messageで初期値を与えてBusinessExceptionをインスタンス化し、throwする。（Controllerでキャッチして空室検索画面に戻る）<br>
     *
	 *	5. long型変数diffDaysをチェックイン日付と本日の日付の差分で初期化。<br>
	 *	6. 差分の取得はChronoUnit.DAYS.betweenメソッドを呼び出す。<br>
	 *  7. 差分が0以下かどうかを条件判定（本日のチェックインは認めない。チェックイン日付が本日の場合前の画面に戻る。)<br>
	 *   7.1　trueの時の処理<br>
	 *	 7.1.1 String型変数messageを宣言。<br>
	 *   7.1.2 messageSource.getMessageを呼び出し、プロパティファイルmessages.propertiesからキー"bus.error.tomorrow"のメッセージを取得し、変数messageを初期化<br>
	 *  　7.1.3 引数messageで初期値を与えてBusinessExceptionをインスタンス化し、throwする。（Controllerでキャッチして空室検索画面に戻る）<br>
	 *  8. ReservableRoomInfoはあくまで予約可能日（つまりチェックイン可能日）として登録されている。よってチェックアウト日の一日前を検索範囲とするため、チェックアウト日から１を引いた値を引数に渡す<br>
	 *  
	 *  9. List<ReservableRoomInfo>型変数listを宣言。<br>
	 *	10. インターフェースReservableRoomInfoDAOのsearchメソッドを呼び出し、変数listに格納。<br>
 	 *	   第一引数 本メソッドの引数のcheckinを引き渡す。<br>
	 *	   第二引数　本メソッドの引数のcheckoutから１を減じた日付を引き渡す。※ReservableRoomInfoテーブルは予約可能日を扱う、つまりチェックイン可能日として登録されている。<br>
	 *	   第三引数　本メソッドの引数のindoorBathRoomを引き渡す。<br>
	 *	   第四引数 本メソッドの引数のsmokingを引き渡す。<br>
	 *  11. 変数listが空かどうかを条件判定（searchメソッドで検索結果が該当なしかどうか）<br>
	 *   11.1 trueの時の処理<br>
	 *	 11.1.1 String型変数messageを宣言。<br>
     *	 11.1.2 messageSource.getMessageを呼び出し、プロパティファイルmessages.propertiesからキー"bus.error.searchresult_zero"のメッセージを取得し、変数messageを初期化<br>
	 *	  11.1.3 引数messageで初期値を与えてBusinessExceptionをインスタンス化し、throwする。（Controllerでキャッチして空室検索画面に戻る）<br>
	 *  12. 変数listを戻り値で返す<br>
	 *  
	 * @param checkin チェックイン日付
	 * @param　checkout チェックアウト日付
	 * @param　indoorBathRoom 内風呂
	 * @param　smoking 禁煙
	 * @return　空室情報のListを返す。
	 * 
	 */
	@Override
	public List<ReservableRoomInfo> search(LocalDate checkin, LocalDate checkout,boolean indoorBathRoom,
			boolean smoking) {
		// long型変数diffDaysをチェックイン日付・チェックアウト日付の差分で初期化。
		//　差分の取得はChronoUnit.DAYS.betweenメソッドを呼び出す。
		long diffDays = ChronoUnit.DAYS.between(checkin, checkout);
		
		//差分が0と等しいかを条件判定(チェックインインとチェックアウトが同一日付かどうか条件判定)
		if(diffDays == 0) {
			//trueの時の処理
			//String型変数messageを宣言。
			//プロパティファイルmessages.propertiesからキー"bus.error.sameday"のメッセージを取得し、変数messageを初期化
			String message = messageSource.getMessage("bus.error.sameday", null, Locale.JAPAN);
			
			//引数messageで初期値を与えてBusinessExceptionをインスタンス化し、throwする。（Controllerでキャッチして空室検索画面に戻る）
			throw new BusinessException(message);
		}
		
		//差分が0未満かどうかを条件判定(チェックアウトがチェックインインより過去日付の場合前の画面に戻る)
		if(diffDays < 0) {
			//trueの時の処理
			//String型変数messageを宣言。
			//プロパティファイルmessages.propertiesからキー"bus.error.pastday"のメッセージを取得し、変数messageを初期化
			String message = messageSource.getMessage("bus.error.pastday", null, Locale.JAPAN);
			//引数messageで初期値を与えてBusinessExceptionをインスタンス化し、throwする。（Controllerでキャッチして空室検索画面に戻る）
			throw new BusinessException(message);
		}
		
		
		// long型変数diffDaysをチェックイン日付と本日の日付の差分で初期化。
		//　差分の取得はChronoUnit.DAYS.betweenメソッドを呼び出す。
		diffDays = ChronoUnit.DAYS.between(systemDateUtil.today() ,checkin);
		//差分が0以下かどうかを条件判定（本日のチェックインは認めない。チェックイン日付が本日の場合前の画面に戻る。)
		if(diffDays <= 0){
			//trueの時の処理
			//String型変数messageを宣言。
			//プロパティファイルmessages.propertiesからキー"bus.error.tomorrow"のメッセージを取得し、変数messageを初期化
			String message = messageSource.getMessage("bus.error.tomorrow", null, Locale.JAPAN);
			//引数messageで初期値を与えてBusinessExceptionをインスタンス化し、throwする。（Controllerでキャッチして空室検索画面に戻る）
			throw new BusinessException(message);
		}
		
		
		
		//ReservableRoomInfoはあくまで予約可能日（つまりチェックイン可能日）として登録されている。
		//よってチェックアウト日の一日前を検索範囲とするため、チェックアウト日から１を引いた値を引数に渡す
		LocalDate finalCheckIn = checkout.minusDays(1);
		
		//List<ReservableRoomInfo>型変数listを宣言。
		//インターフェースReservableRoomInfoDAOのsearchメソッドを呼び出し、変数listに格納。
		//第一引数 本メソッドの引数のcheckinを引き渡す。
		//第二引数　本メソッドの引数のcheckoutから１を減じた日付を引き渡す。※ReservableRoomInfoテーブルは予約可能日を扱う、つまりチェックイン可能日として登録されている。
		//第三引数　本メソッドの引数のindoorBathRoomを引き渡す。
		//第四引数 本メソッドの引数のsmokingを引き渡す。
		List<ReservableRoomInfo> list = reservableRoomInfoDAO.search(checkin, finalCheckIn, indoorBathRoom, smoking);
		
		//変数listが空かどうかを条件判定（searchメソッドで検索結果が該当なしかどうか）
		if(list.isEmpty()) {
			//trueの時の処理
			//String型変数messageを宣言。
			//プロパティファイルmessages.propertiesからキー"bus.error.searchresult_zero"のメッセージを取得し、変数messageを初期化
			String message = messageSource.getMessage("bus.error.searchresult_zero", null, Locale.JAPAN);
			//引数messageで初期値を与えてBusinessExceptionをインスタンス化し、throwする。（Controllerでキャッチして空室検索画面に戻る）
			throw new BusinessException(message);
		}
		
		/*
		//変数listの要素の数だけループを実行
		for(ReservableRoomInfo roomInfo : list) {
			
			
			//画像のブラウザキャッシュを防ぐため、画像パスの末尾に【?version=ランダム値】を埋める
			Double randamValue = new Double(Math.random() * 100);
			
			
			String path = roomInfo.getRoom().getRoomImage() + "?version=" +  LocalDate.now().toString() +  randamValue.toString();
			roomInfo.getRoom().setRoomImage(path);
		}*/
		
		//変数listを戻り値で返す
		return list;
	}

}
