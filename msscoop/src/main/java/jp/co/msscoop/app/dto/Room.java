package jp.co.msscoop.app.dto;

import lombok.Data;

@Data
public class Room {
	private String roomId;
	private String roomName;
	private boolean inDoorBathRoom;
	private boolean smoking;
	private int price;
	private String roomImage;
	
}
