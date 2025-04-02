package jp.co.msscoop.app.dao;



import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.msscoop.app.dto.Reserve;
import jp.co.msscoop.app.dto.Room;
import jp.co.msscoop.app.dto.UserInfo;

/**
 * [概要]<br>
 * 予約テーブルのDAO
 */
@Mapper
public interface ReserveDAO {
	
	/**
	 * [概要]<br>
	 * 予約IDを指定して、予約情報を１件取得する<br>
	 * 
	 * [処理内容]<br>
	 * SQLの組み立てかた<br>
	 * 　1.予約テーブルとお部屋テーブル・ユーザテーブルを内部結合する。<br>
	 * 　2.結合条件(1)(2)を結ぶ。<br>
	 *   (1)予約テーブルのユーザIDとユーザテーブルのユーザID。<br>
	 *   (2)予約テーブルのお部屋IDとお部屋テーブルの部屋ID。<br>
	 *  3.WHEREで予約IDを指定する。<br>
	 * 
	 * @param　reserveId　予約ID
	 * @throws DataAccessExceptionまたはSQLExceptionをスロー
	 * @return　主キーを元に検索したReserveを返す。該当データがない場合NULLを返す。
	 */
	public Reserve findById(@Param("reserveId")String reserveId);
	
	
	
	/**
	 * 
	 * [概要]<br>
	 * 予約新規登録時の主キー値を採番する。本メソッドを利用するときはトランザクション内で実行すること<br>
	 * 
	 * [処理内容]<br>
	 * SQLの組み立てかた<br>
	 * 　1.予約テーブルから検索。結合はなし
	 *  2.WHEREで予約テーブルのチェックイン日付を本日の日付を指定する。
	 *  3.取得したチェックイン日付をCHAR型10桁に変換する
	 * 　4.予約テーブルの予約IDでCOUNT関数の戻り値に+1したものを、CHAR型に4桁に変換する（4桁先頭ゼロ埋め）。
	 *  5.3と4の結果を文字列連結し、その結果を新規IDとし、これをSELECTの列として返す。
	 * 
	 * @param checkIn チェックイン日付を指定
	 * @throws DataAccessExceptionまたはSQLExceptionをスロー
	 * @return 該当データがない場合NULLを返す。
	 */
	public String findNewId(@Param("checkIn") LocalDate checkIn);
	
	
	/**
	 * [概要]<br>
	 * 予約新規登録時を実行する<br>
	 * [処理内容]<br>
	 * SQLの組み立てかた　INSERTのvalue区に以下を指定<br>
	 * 　1. reserveId・・・予約IDを指定
	    2. roomId・・・部屋ID
	    3. userId・・・ログインユーザID
		4. checkIn・・・チェックイン日付
		5. checkOut・・・チェックアウト日付
		6. stayNumberOfPeople・・・宿泊人数
		7. meal・・・食事あり・なし
	    8. amount・・・宿泊合計
		9. cancel・・・キャンセルかどうか（予約の場合false）
	 
	 * @param reserve 登録内容を格納したReserve
	 * @throws DataAccessExceptionまたはSQLExceptionをスロー
	 * @return 登録に成功した場合１を、登録されなかった場合は０を返す。
	 */
	public int  insert(Reserve reserve);

}
