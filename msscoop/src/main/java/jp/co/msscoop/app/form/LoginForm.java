package jp.co.msscoop.app.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * [概要]<br>
 * 
 */
@Data
public class LoginForm {
	
	
	@NotEmpty
	@Size(min=1, max=20)
	private String userId;
	@NotEmpty
	
	private String password;
	
	

}
