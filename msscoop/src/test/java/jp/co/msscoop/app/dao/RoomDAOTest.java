package jp.co.msscoop.app.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import jp.co.msscoop.app.dao.UserInfoDAO;
import jp.co.msscoop.app.dto.Room;
import jp.co.msscoop.app.dto.UserInfo;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoomDAOTest {
	
	@Autowired
	public RoomDAO dao;
	
	@Test
	@Sql("/sqls/user_init.sql")
	public void test1() {
		
		Room room = dao.findById("202501050001");
		
		
		
		
		
		//少し賢い書き方
		assertThat(room).isNotNull();
		assertThat(room.getRoomId()).isEqualTo("202501050001");
		assertThat(room.getRoomName()).isEqualTo("雁の間");
		assertThat(room.isInDoorBathRoom()).isTrue();
		assertThat(room.isSmoking()).isTrue();
		assertThat(room.getPrice()).isEqualTo(100000);
		
		
						
	}
}
