package jp.co.msscoop.app.form;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.AssertTrue;
import lombok.Data;
import jp.co.msscoop.app.common.SystemDateUtil;
import jp.co.msscoop.app.common.SystemDateUtilFormDevelop;

@Data
public class ReservableSearchForm {
	
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkin;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkout;
	private boolean inDoorBath;
	private boolean smoking;
	
}
