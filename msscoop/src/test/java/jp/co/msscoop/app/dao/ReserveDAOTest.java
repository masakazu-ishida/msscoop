package jp.co.msscoop.app.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import jp.co.msscoop.app.dao.UserInfoDAO;
import jp.co.msscoop.app.dto.Reserve;
import jp.co.msscoop.app.dto.Room;
import jp.co.msscoop.app.dto.UserInfo;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReserveDAOTest {
	
	@Autowired
	public ReserveDAO dao;
	
	@Test
	@Sql("/sqls/init_data.sql")
	public void findByNewIdTest() {
		String id = dao.findNewId(LocalDate.parse("2025-01-06"));
		//少し賢い書き方
		assertThat(id).isEqualTo("202501060004");
		
						
	}
	
	@Test
	@Sql("/sqls/init_data.sql")
	public void searchTest() {
		List<Reserve> list= dao.search("12345", LocalDate.parse("2025-01-09"));
		//少し賢い書き方
		
		assertThat(list).hasSize(1).isNotNull();
		assertThat(list).extracting("reserveId","roomId","userId","checkIn","checkOut","stayNumberOfPeople","meal","amount","cancel","room.roomName","user.fullName","user.role")
		.containsExactly(
				tuple("202501090001", "202501050001", "12345", LocalDate.of(2025,1,9), LocalDate.of(2025, 1, 10), 2, true, 200000, "0","雁の間","鳥取一郎","ROLE_ADMIN")
			);

		
						
	}
	
	@Test
	@Sql("/sqls/init_data.sql")
	public void findByIdTest() {
		Reserve reserve= dao.findById("202501090001");
		//少し賢い書き方
		
		assertThat(reserve).isNotNull();
		
		
		assertThat(reserve.getReserveId()).isEqualTo("202501090001");
		assertThat(reserve.getRoomId()).isEqualTo("202501050001");
		assertThat(reserve.getUserId()).isEqualTo("12345");
		assertThat(reserve.getCheckIn()).isEqualTo(LocalDate.of(2025,1,9));
		assertThat(reserve.getCheckOut()).isEqualTo(LocalDate.of(2025, 1, 10));
		assertThat(reserve.getStayNumberOfPeople()).isEqualTo(2);
		assertThat(reserve.isMeal()).isEqualTo(true);
		assertThat(reserve.getAmount()).isEqualTo(200000);
		
		
		assertThat(reserve.getRoom().getRoomName()).isEqualTo("雁の間");
		assertThat(reserve.getUser().getUserId()).isEqualTo("12345");
		assertThat(reserve.getUser().getFullName()).isEqualTo("鳥取一郎");
		assertThat(reserve.getUser().getRole()).isEqualTo("ROLE_ADMIN");
		
		
		
						
	}
	
	
	
	
	
	
}
