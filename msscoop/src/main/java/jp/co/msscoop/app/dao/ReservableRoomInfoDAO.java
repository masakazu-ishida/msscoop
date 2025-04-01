package jp.co.msscoop.app.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.msscoop.app.dto.ReservableRoomInfo;

/**
 * 空室検索業務用のDAO
 */
@Mapper
public interface ReservableRoomInfoDAO {
	
	/**
	 * 
	 * 【概要】<br>
	 * 予約可能な日を取得<br>
	 * 【処理内容】<br>
	 * SQLの組み立てかた<br>
		1.予約可能テーブルと予約テーブルを左外部結合（左：予約可能テーブル）する。<br>
		  これにより予約と紐づいたもの(NOT NULL)とないもの(NULL)が一覧で表示されるので、
		  抽出条件(WHERE)で予約と紐づかない（つまりNULL）で絞りこんだものを予約可能情報としてサブクエリとしてする。<br>
		2.1.のサブクエリ結果とお部屋テーブルを内部結合<br>
		3.画面から入力された値を抽出条件(WHERE)とする<br>
		 ・ReservableRoomInfo.businessDayに対し	チェックインを開始日付とし、チェックアウト日付から１を引いたものを終了日付として範囲指定する<br>
		 ・喫煙、内風呂の真偽値を指定する<br>
	 * @param firstCheckIn　チェックイン日付
	 * @param finalCheckIn チェックアウト日付から１を引いた日付。（ReservableRoomInfo.businessDayは予約可能日＝チェックイン可能日であるため）
	 * @param indoorBathRoom　true:内風呂あり false:内風呂なし
	 * @param smoking true:喫煙可能 false:喫煙不可能
	 * @throws DataAccessExceptionまたはSQLExceptionをスロー
	 * @return　宿泊可能なお部屋情報をリストで返却
	 */
	public List<ReservableRoomInfo> search(
			@Param("firstCheckIn")LocalDate firstCheckIn, 
			@Param("finalCheckIn") LocalDate finalCheckIn, 
			@Param("inDoorBathRoom") boolean indoorBathRoom, 
			@Param("smoking") boolean smoking);
	
	
	/**
	 * [概要]<br>
	 *  指定したチェックイン日付と部屋IDで、そのお部屋情報を取得。このメソッドはトランザクション内で呼びされる事で排他ロックがかかる。<br>
	 * 
	 * SQLの組み立てかた<br>
	    1.予約可能テーブルと予約テーブルを内部結合する。<br>
	 *  2.結合条件に(1)(2)をANDで結ぶ<br>
	 *   (1)予約テーブルの部屋IDと、予約可能テーブルの部屋IDを結合条件に指定<br>
	 *   (2)予約テーブルのチェックイン日と、予約可能テーブルの営業日を結合条件に指定<br>
	 *  3.引数で渡されたチェックイン日と部屋IDを、WHEREで予約テーブルのチェックイン日と予約可能テーブルの部屋IDに指定<br>
	 *  4.SQL文の最後にFORUPDATEを指定し、排他ロックを掛ける<br>
	 * 
	 * @param roomId　部屋ID
	 * @param firstCheckIn チェックイン日付
	 * @throws DataAccessExceptionまたはSQLExceptionをスロー
	 * @return 非NULL:既に予約済み NULL:予約可能
	 */
	public ReservableRoomInfo findById(
			@Param("roomId") String  roomId,
			@Param("businessDay")LocalDate firstCheckIn);

}