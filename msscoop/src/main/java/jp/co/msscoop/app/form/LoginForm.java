package jp.co.msscoop.app.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * [概要]<br>
 * 
 */
@Data
public class LoginForm implements Serializable {
	

	/**
	 * シリアライズ時のバージョン番号
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@Size(min=1, max=20)
	private String userId;
	@NotEmpty
	
	private String password;
	
	

}
