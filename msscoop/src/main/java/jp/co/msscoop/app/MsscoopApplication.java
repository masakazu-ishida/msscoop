package jp.co.msscoop.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jp.co.msscoop.app.service.UserSharedServiceImpl;

@SpringBootApplication
public class MsscoopApplication {

	public static void main(String[] args) {
		
		//PasswordEncoder passEncoder = new BCryptPasswordEncoder();
		//String encodedPass = passEncoder.encode("12345");
		SpringApplication.run(MsscoopApplication.class, args);
	}

}
