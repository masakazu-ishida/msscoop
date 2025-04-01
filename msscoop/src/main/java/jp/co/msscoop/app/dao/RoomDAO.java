package jp.co.msscoop.app.dao;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.msscoop.app.dto.Room;

@Mapper
public interface RoomDAO {
	/**
	 * * [概要]<br>
	 * 部屋IDを指定して、部屋情報を１件取得する<br>
	 * 
	 * [処理内容]<br>
	 * SQLの組み立てかた<br>
	 * 　1.お部屋テーブルから取得する。<br>
	 * 　2.WHEREで部屋IDを指定する。<br>
	 * 
	 * @param roomId お部屋テーブルの部屋IDを指定
	 * @throws DataAccessExceptionまたはSQLExceptionをスロー
	 * @return
	 */
	public Room findById(@Param("roomId")String roomId);

}
