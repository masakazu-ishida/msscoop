package jp.co.msscoop.app.service;

import java.time.LocalDate;
import java.util.List;

import jp.co.msscoop.app.dto.ReservableRoomInfo;

public interface ReservableSearchService {
	
	public List<ReservableRoomInfo> search(LocalDate checkin, LocalDate checkout, boolean indoorBathRoom, boolean smoking);

}
