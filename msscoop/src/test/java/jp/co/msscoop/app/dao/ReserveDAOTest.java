package jp.co.msscoop.app.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.time.LocalDate;

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
public class ReserveDAOTest {
	
	@Autowired
	public ReserveDAO dao;
	
	@Test
	@Sql("/sqls/user_init.sql")
	public void test1() {
		String id = dao.findNewId(LocalDate.parse("2025-01-06"));
		//少し賢い書き方
		assertThat(id).isEqualTo("2025010604");
		
						
	}
	
	
	
}
