package jp.co.msscoop.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.msscoop.app.dto.UserInfo;


/**
 * [概要]<br>
 * ユーザテーブルのDAO
 */
@Mapper
public interface UserInfoDAO {
	/**
	 *  * * [概要]<br>
	 * ユーザID（ユーザテーブルの主キー）を指定定して、ユーザ情報を１件取得する<br>
	 * 
	 * [処理内容]<br>
	 * SQLの組み立てかた<br>
	 * 　1.ユーザテーブルから取得する。<br>
	 * 　2.WHEREでユーザIDを指定する。<br>
	 * @param userId ユーザID（ユーザテーブルの主キー）を指定
	 * @throws DataAccessExceptionまたはSQLExceptionをスロー
	 * @return　主キーを元に検索したUserInfoを返す。該当データがない場合NULLを返す。
	 */
	UserInfo findById(@Param("userId") String userId);
	
	
	/**
	 * 
	 * [概要]<br>
	 * ユーザ情報を全件取得する<br>
	 * 
	 * [処理内容]<br>
	 * SQLの組み立てかた<br>
	 * 　1.ユーザテーブルから取得する。<br>
	 * 　
	 * @throws DataAccessExceptionまたはSQLExceptionをスロー
	 * @return ユーザテーブルを全件検索し、UserInfoのListを返す。該当データがない場合、要素０件のListインスタンスを返す。
	 */
	List<UserInfo> findAll();
}
