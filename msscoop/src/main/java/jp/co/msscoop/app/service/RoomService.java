package jp.co.msscoop.app.service;

import org.apache.ibatis.annotations.Param;

import jp.co.msscoop.app.dto.Room;

public interface RoomService {
	public Room findById(String roomId);

}
