package jp.co.msscoop.app.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.msscoop.app.dto.ReservableRoomInfo;

@Mapper
public interface ReservableRoomInfoDAO {
	
	/**
	 * SQLの組み立てかた
		1.予約可能テーブルと予約テーブルを左外部結合（左：予約可能テーブル）する。
		  これにより予約と紐づいたもの(NOT NULL)とないもの(NULL)が一覧で表示されるので、
		  抽出条件(WHERE)で予約と紐づかない（つまりNULL）で絞りこんだものを予約可能情報としてサブクエリとしてする。
		2.サブクエリ結果とお部屋テーブルを内部結合
		3.画面から入力された値を抽出条件(WHERE)とする
		 ・ReservableRoomInfo.businessDayに対し	チェックインを開始日付とし、チェックアウト日付から１を引いたものを終了日付として範囲指定する
		 ・喫煙、内風呂を指定する

	 * @param firstCheckIn　チェックイン日付
	 * @param finalCheckIn チェックアウト日付から１を引いた日付。（ReservableRoomInfo.businessDayは予約可能日＝チェックイン可能日であるため）
	 * @param indoorBathRoom　true:内風呂あり false:内風呂なし
	 * @param smoking true:喫煙可能 false:喫煙不可能
	 * @return　宿泊可能なお部屋情報をリストで返却
	 */
	public List<ReservableRoomInfo> search(
			@Param("firstCheckIn")LocalDate firstCheckIn, 
			@Param("finalCheckIn") LocalDate finalCheckIn, 
			@Param("inDoorBathRoom") boolean indoorBathRoom, 
			@Param("smoking") boolean smoking);
	
	
	public ReservableRoomInfo findById(
			@Param("roomId") String  roomId,
			@Param("businessDay")LocalDate firstCheckIn);

}