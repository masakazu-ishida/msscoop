package jp.co.msscoop.app.service;

import org.apache.ibatis.annotations.Param;

import jp.co.msscoop.app.dto.Room;

/**
 * [概要]<br>
 * 部屋機能に対するサービスインターフェース
 */
public interface RoomService {
	public Room findById(String roomId);

}
