package jp.co.msscoop.app.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [概要]<br>
 * ReservableRoomInfo（予約可能テーブル）の列に対応するDTO
 */
@Data
@NoArgsConstructor
public class Reserve {
	private String reserveId;
	private String roomId;
	private String userId;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private int stayNumberOfPeople;
	private boolean meal;
	private int amount;
	private String cancel;
	private UserInfo user;
	private Room room;
	

}
