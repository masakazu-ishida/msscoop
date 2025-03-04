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
public class UserInfoDAOTest {
	
	@Autowired
	public UserInfoDAO dao;
	
	@Test
	@Sql("/sqls/user_init.sql")
	public void test1() {
		UserInfo user = dao.findById("12345");
		
		
		
		//愚直な書き方
		ObjectAssert<UserInfo> mathes = assertThat(user);
		mathes.isNotNull();
		
		AbstractStringAssert<?> mathes2 = assertThat(user.getUserId());
		mathes2.isEqualTo("12345");
		
		AbstractStringAssert<?> mathes3 = assertThat(user.getPassword());
		mathes3.isEqualTo("pass");
		
		AbstractStringAssert<?> mathes4 = assertThat(user.getRole());
		mathes4.isEqualTo("admin");
		
		
		//少し賢い書き方
		assertThat(user).isNotNull();
		assertThat(user.getUserId()).isEqualTo("12345");
		assertThat(user.getPassword()).isEqualTo("pass");
		assertThat(user.getRole()).isEqualTo("admin");
		
		
		//とても賢い書き方
		assertThat(user)
		.isNotNull()
		.extracting("username", "password", "role")		
		.containsExactly("12345", "pass", "admin");
		
		
		
		assertThat(dao.findAll()).hasSize(3)
		.extracting("username", "password", "role")
		.containsExactly(
				tuple("12345","pass","admin"),
				tuple("6789","pass","admin"),
				tuple("abcd1","pass","general")
				);
						
	}

	
	
	
	

}
