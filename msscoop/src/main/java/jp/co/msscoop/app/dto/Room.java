package jp.co.msscoop.app.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [概要]<br>
 * ReservableRoomInfo（予約可能テーブル）の列に対応するDTO
 */
@Data
@NoArgsConstructor
public class Room  implements Serializable {
	/**
	 * シリアライズ時のバージョン番号
	 */
	private static final long serialVersionUID = 1L;

	private String roomId;
	private String roomName;
	private boolean inDoorBathRoom;
	private boolean smoking;
	private int price;
	private String roomImage;
	
}
