package jp.co.msscoop.app.dao;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.msscoop.app.dto.Room;

@Mapper
public interface RoomDAO {
	public Room findById(@Param("roomId")String roomId);

}
