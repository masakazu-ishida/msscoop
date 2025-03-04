package jp.co.msscoop.app.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.msscoop.app.dto.ReservableRoomInfo;

@Mapper
public interface ReservableRoomInfoDAO {
	public List<ReservableRoomInfo> search(
			@Param("firstCheckIn")LocalDate firstCheckIn, 
			@Param("finalCheckIn") LocalDate finalCheckIn, 
			@Param("inDoorBathRoom") boolean indoorBathRoom, 
			@Param("smoking") boolean smoking);
	
	
	public ReservableRoomInfo findById(
			@Param("roomId") String  roomId,
			@Param("businessDay")LocalDate firstCheckIn);

}
