package jp.co.msscoop.app.service;

import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.util.BeanUtil;

import jp.co.msscoop.app.dao.ReservableRoomInfoDAO;
import jp.co.msscoop.app.dao.ReserveDAO;
import jp.co.msscoop.app.dto.Reserve;
import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.form.ReserveForm;

/**
 * [概要]<br>
 * 予約変更機能に対するサービスインターフェース実装クラス
 */
@Service
public class ReserveUpdateServiceImpl implements ReserveUpdateService {
	
	private final ReserveDAO reserveDAO;
	
	/**
	 * messageas.propertiesアクセス用インターフェース
	 */
	private final MessageSource messageSource;
	
	
	 
	/**
	 * [概要]
	 * 予約変更機能に必要なオブジェクトのインターフェースをコンストラクタインジェクションする
	 * 	 
	 * @param reserveDAO
	 * @param messageSource
	 */
	public ReserveUpdateServiceImpl(ReserveDAO reserveDAO, MessageSource messageSource) {
		this.reserveDAO = reserveDAO;
		this.messageSource = messageSource;
	
	}
	

	@Override
	public ReserveForm input(String reserveId) {
		// TODO 自動生成されたメソッド・スタブ
		
		Reserve reserveDTO = reserveDAO.findById(reserveId);
		ReserveForm reserveForm  = new ReserveForm();
		BeanUtils.copyProperties(reserveDTO, reserveForm);
		
		reserveForm.setStayNumberOfPeople(reserveDTO.getStayNumberOfPeople());
		reserveForm.setAmount(reserveDTO.getStayNumberOfPeople() * reserveDTO.getRoom().getPrice());
		reserveForm.setPrice(reserveDTO.getRoom().getPrice());
		reserveForm.setRoomImage(reserveDTO.getRoom().getRoomImage());
		reserveForm.setRoomName(reserveDTO.getRoom().getRoomName());
		
		
		
		return reserveForm;
	}

	@Override
	public ReserveForm confirm(ReserveForm updateForm) {
		
		
		Reserve reserveDTO = reserveDAO.findById(updateForm.getReserveId());
		
		//料金を再計算
		updateForm.setAmount(reserveDTO.getRoom().getPrice() * updateForm.getStayNumberOfPeople());
		return updateForm;
	}

	@Transactional
	@Override
	public int commit(ReserveForm updateForm) {
		
		Reserve reserveDTO = reserveDAO.findById(updateForm.getReserveId());
		
		BeanUtils.copyProperties(updateForm,reserveDTO);
		
		return reserveDAO.update(reserveDTO);
		
		
	}

}
