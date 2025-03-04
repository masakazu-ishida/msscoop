package jp.co.msscoop.app.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SearchForm {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkin;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkout;
	private boolean inDoorBath;
	private boolean smoking;
}
