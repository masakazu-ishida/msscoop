package jp.co.msscoop.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [概要]<br>
 * UserInfo（ユーザテーブル）の列に対応するDTO
 */
@NoArgsConstructor
@Data
public class UserInfo {
	
	private String userId;
	private String password;
	private String role;
	private String fullName;
	private String email;
	
}
