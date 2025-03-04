package jp.co.msscoop.app.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginForm {
	
	@NotNull
	@NotEmpty
	private String username;
	@NotEmpty
	@NotNull
	private String password;
	
	

}
