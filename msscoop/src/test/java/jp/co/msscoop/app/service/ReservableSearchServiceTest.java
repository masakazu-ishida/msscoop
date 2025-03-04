package jp.co.msscoop.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.msscoop.app.dto.ReservableRoomInfo;


@SpringBootTest
public class ReservableSearchServiceTest {
	
	
	@Autowired
	private ReservableSearchService service;
	
	
	@Test
	public void search() {
		LocalDate checkIn = LocalDate.of(2025, 01, 6);
		LocalDate checkOut = LocalDate.of(2025, 01, 8);
		boolean smoking = false;
		boolean inDoorBathRoom = false;
		List<ReservableRoomInfo> list = service.search(checkIn, checkOut, inDoorBathRoom, smoking);
		
		assertThat(list).isNotEmpty().hasSize(2)
		.extracting("roomId","businessday", "room.roomId","room.roomName","room.inDoorBathRoom","room.price","room.smoking","room.roomImage")
		.containsExactly(
			tuple("202501050003", LocalDate.parse("2025-01-06",DateTimeFormatter.ofPattern("yyyy-MM-dd")),"202501050003","雉の間","0",35000,"0","/images/202501050003.png"),
			tuple("202501050003", LocalDate.parse("2025-01-07",DateTimeFormatter.ofPattern("yyyy-MM-dd")),"202501050003","雉の間","0",35000,"0","/images/202501050003.png")
		);
	}

}
