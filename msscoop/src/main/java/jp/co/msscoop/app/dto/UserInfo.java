package jp.co.msscoop.app.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [概要]<br>
 * UserInfo（ユーザテーブル）の列に対応するDTO
 */
@NoArgsConstructor
@Data
public class UserInfo  implements Serializable {
	/**
	 * シリアライズ時のバージョン番号
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	private String password;
	private String role;
	private String fullName;
	private String email;
	
}
