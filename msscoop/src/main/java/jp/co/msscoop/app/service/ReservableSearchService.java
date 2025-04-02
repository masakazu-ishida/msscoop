package jp.co.msscoop.app.service;

import java.time.LocalDate;
import java.util.List;

import jp.co.msscoop.app.dto.ReservableRoomInfo;

/**
 * [概要]<br>
 * 予約可能機能に対するサービスインターフェース
 */
public interface ReservableSearchService {
	
	/**
	 * [概要]<br>
	 * 空室検索を行う
	   @param checkin チェックイン年月日
	 * @param checkout チェックアウト年月日
	 * @param indoorBathRoom 内風呂
	 * @param smoking 喫煙
	 * @return 空室情報をリストで返す。空室ゼロの場合、要素数ゼロのリストを返す
	 */
	public List<ReservableRoomInfo> search(LocalDate checkin, LocalDate checkout, boolean indoorBathRoom, boolean smoking);

}
