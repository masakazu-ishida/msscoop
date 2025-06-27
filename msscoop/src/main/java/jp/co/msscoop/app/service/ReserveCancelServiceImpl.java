package jp.co.msscoop.app.service;

import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.msscoop.app.dao.ReserveDAO;
import jp.co.msscoop.app.dto.Reserve;
import jp.co.msscoop.app.form.ReserveForm;


@Service
public class ReserveCancelServiceImpl implements ReserveCancelService {

	
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
	public ReserveCancelServiceImpl(ReserveDAO reserveDAO, MessageSource messageSource) {
		this.reserveDAO = reserveDAO;
		this.messageSource = messageSource;
	
	}
	

	@Override
	public ReserveForm confirm(String  reserveId) {
		
		Reserve reserveDTO = reserveDAO.findById(reserveId);
		ReserveForm reserveCancelForm  = new ReserveForm();
		BeanUtils.copyProperties(reserveDTO, reserveCancelForm);
		
		reserveCancelForm.setStayNumberOfPeople(reserveDTO.getStayNumberOfPeople());
		reserveCancelForm.setAmount(reserveDTO.getStayNumberOfPeople() * reserveDTO.getRoom().getPrice());
		reserveCancelForm.setPrice(reserveDTO.getRoom().getPrice());
		reserveCancelForm.setRoomImage(reserveDTO.getRoom().getRoomImage());
		reserveCancelForm.setRoomName(reserveDTO.getRoom().getRoomName());
		
		
		
		
		return reserveCancelForm;
	}

	@Transactional
	@Override
	public int commit(ReserveForm updateForm) {
		
		Reserve reserveDTO = reserveDAO.findById(updateForm.getReserveId());
		
		
		reserveDTO.setCancel("1");
		
		return reserveDAO.update(reserveDTO);
		
		
	}

}
