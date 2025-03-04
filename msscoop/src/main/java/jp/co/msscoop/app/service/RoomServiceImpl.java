package jp.co.msscoop.app.service;

import org.springframework.stereotype.Service;

import jp.co.msscoop.app.dao.RoomDAO;
import jp.co.msscoop.app.dto.Room;


@Service
public class RoomServiceImpl implements RoomService {

	private final RoomDAO dao;
	
	public RoomServiceImpl(RoomDAO dao) {
		this.dao = dao;
		
	}
	
	
	
	@Override
	public Room findById(String roomId) {
		
		return dao.findById(roomId);
		
	}

}
