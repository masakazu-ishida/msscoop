package jp.co.msscoop.app.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ReservableRoomInfo（予約可能テーブル）の列に対応するDTO
 */
@Data
@NoArgsConstructor
public class ReservableRoomInfo {
	private String roomId;
	private LocalDate businessday;
	private Room room;
}
