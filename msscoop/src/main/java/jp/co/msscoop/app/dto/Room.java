package jp.co.msscoop.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [概要]<br>
 * ReservableRoomInfo（予約可能テーブル）の列に対応するDTO
 */
@Data
@NoArgsConstructor
public class Room {
	private String roomId;
	private String roomName;
	private boolean inDoorBathRoom;
	private boolean smoking;
	private int price;
	private String roomImage;
	
}
