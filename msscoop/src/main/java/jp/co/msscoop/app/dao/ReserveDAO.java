package jp.co.msscoop.app.dao;



import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.msscoop.app.dto.Reserve;
import jp.co.msscoop.app.dto.Room;

@Mapper
public interface ReserveDAO {
	public Reserve findById(@Param("reserveId")String reserveId);
	
	public String findNewId(@Param("checkIn") LocalDate checkIn);
	
	public int  insert(Reserve reserve);

}
