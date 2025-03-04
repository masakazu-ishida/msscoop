package jp.co.msscoop.app.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import jp.co.msscoop.app.dao.ReservableRoomInfoDAO;
import jp.co.msscoop.app.dto.ReservableRoomInfo;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservableRoomInfoDAOTest {
	
	@Autowired
	private ReservableRoomInfoDAO dao;
	
	
	
	@Test
	public void searchTest() {
		
		LocalDate firstCheckIn = LocalDate.of(2025, 01, 06);
		LocalDate finalCheckIn = LocalDate.of(2025, 01, 07);
		boolean smoking = false;
		boolean inDoorBathRoom = true;
		List<ReservableRoomInfo> list = dao.search(firstCheckIn, finalCheckIn, inDoorBathRoom, smoking);
		assertThat(list).isNotEmpty().hasSize(5);
		
		
		
	}
	

}
