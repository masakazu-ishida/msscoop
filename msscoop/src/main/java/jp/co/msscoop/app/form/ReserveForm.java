package jp.co.msscoop.app.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReserveForm {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkIn;
	
	private String roomId;
	private String roomName;
	private boolean inDoorBathRoom;
	private boolean smoking;
	private String roomImage;
	private int stayNumberOfPeople;
	private boolean meal; 
	private int price;
	private int amount;
	
	

}
