package jp.co.msscoop.app.form;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * [概要]<br>
 * 
 */
@Data
public class ReserveForm  implements Serializable {

	/**
	 * シリアライズ時のバージョン番号
	 */
	private static final long serialVersionUID = 1L;
	
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
