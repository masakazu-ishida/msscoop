package jp.co.msscoop.app.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Reserve {
	private String reserveId;
	private String roomId;
	private String userId;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private int stayNumberOfPeople;
	private boolean meal;
	private int amount;
	
	private UserInfo user;
	private Room room;
	

}
