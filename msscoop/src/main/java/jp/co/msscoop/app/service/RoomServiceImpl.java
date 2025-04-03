package jp.co.msscoop.app.service;

import org.springframework.stereotype.Service;

import jp.co.msscoop.app.dao.RoomDAO;
import jp.co.msscoop.app.dto.Room;

/**
 * [概要]<br>
 * 部屋管理機能に対するサービスインターフェース実装クラス
 * 
 */
@Service
public class RoomServiceImpl implements RoomService {

	/**
	 * 部屋テーブルDAO
	 */
	private final RoomDAO dao;
	
	
	/**
	 * [概要]
	   部屋情報管理機能に必要なオブジェクトのインターフェースをコンストラクタインジェクションする
	   
	 * @param dao　部屋テーブルDAO
	 */
	public RoomServiceImpl(RoomDAO dao) {
		this.dao = dao;
		
	}
	
	
	
	/**
	 * [概要]<br>
	 * 
	 * 
	 * 
	 */
	@Override
	public Room findById(String roomId) {
		
		return dao.findById(roomId);
		
	}

}
