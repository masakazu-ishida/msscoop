package jp.co.msscoop.app.service;

import java.time.LocalDate;
import java.util.Locale;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.msscoop.app.dao.ReservableRoomInfoDAO;
import jp.co.msscoop.app.dao.ReserveDAO;
import jp.co.msscoop.app.dto.ReservableRoomInfo;
import jp.co.msscoop.app.dto.Reserve;
import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.exception.BusinessException;
import jp.co.msscoop.app.form.ReserveRegisterForm;
import jp.co.msscoop.app.session.UserSession;

@Service
public class ReserveServiceImpl implements ReserveService {

	private final UserSession userSession;
	private final ReserveDAO reserveDAO;
	private final ReservableRoomInfoDAO reservableRoomInfoDAO;
	private final MessageSource messageSource;
	
	public ReserveServiceImpl(ReserveDAO reserveDAO, ReservableRoomInfoDAO reservableRoomInfoDAO,MessageSource messageSource,UserSession userSession){
		this.reserveDAO = reserveDAO;
		this.reservableRoomInfoDAO = reservableRoomInfoDAO;
		this.messageSource = messageSource;
		this.userSession= userSession;
	}
	
	@Transactional
	@Override
	public String register(ReserveRegisterForm registerForm) {
		// TODO 自動生成されたメソッド・スタブ
		
		
		
		
		//予約のバッティングをしないよう、ロックを掛ける
		ReservableRoomInfo reservedInfo = reservableRoomInfoDAO.findById(registerForm.getRoomId(), registerForm.getCheckIn());
		if(reservedInfo != null) {
			//非NULLの場合、その日・部屋に既に予約が存在するので、タッチの差で別ユーザに予約が取られたという意味
			String message = messageSource.getMessage("bus.error.already_reserved", null, Locale.JAPAN);
			throw new BusinessException(message);
		}
		
		//insertの為の初期化
		Reserve reserve = new Reserve();
		BeanUtils.copyProperties(registerForm, reserve);
		
		
		UserInfo loginUser = userSession.getLoginUser();
		reserve.setUser(loginUser);
		
		String userId = userSession.getLoginUser().getUserId();
		reserve.setUserId(userId);
		
		
		//チェックアウト日をチェックインの翌日に設定
		LocalDate checkIn = reserve.getCheckIn();
		reserve.setCheckOut(checkIn.plusDays(1));
		
		
		String newId = reserveDAO.findNewId(checkIn);
		reserve.setReserveId(newId);
		try {
			if( reserveDAO.insert(reserve) == 1) {
				return newId;
			}
			else {
				return "";
			}
		}
		catch(Exception e) {
			throw e;
		}
	}

}
