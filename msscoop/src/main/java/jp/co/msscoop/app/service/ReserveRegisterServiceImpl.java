package jp.co.msscoop.app.service;

import java.time.LocalDate;
import java.util.Locale;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.msscoop.app.common.EMailSender;
import jp.co.msscoop.app.dao.ReservableRoomInfoDAO;
import jp.co.msscoop.app.dao.ReserveDAO;
import jp.co.msscoop.app.dto.ReservableRoomInfo;
import jp.co.msscoop.app.dto.Reserve;
import jp.co.msscoop.app.dto.Room;
import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.exception.BusinessException;
import jp.co.msscoop.app.form.ReserveForm;

/**
 * [概要]<br>
 * 
 */
@Service
public class ReserveRegisterServiceImpl implements ReserveRegisterService {

	
	private final ReserveDAO reserveDAO;
	private final ReservableRoomInfoDAO reservableRoomInfoDAO;
	private final MessageSource messageSource;
	private final EMailSender emailSender;
	
	/**
	 * 部屋情報にアクセスするためRoomServiceをインジェクションする
	 */
	private final RoomService roomService;
	
	
	public ReserveRegisterServiceImpl(ReserveDAO reserveDAO, ReservableRoomInfoDAO reservableRoomInfoDAO,MessageSource messageSource,RoomService roomService,EMailSender emailSender){
		this.reserveDAO = reserveDAO;
		this.reservableRoomInfoDAO = reservableRoomInfoDAO;
		this.messageSource = messageSource;
		this.roomService = roomService;
		this.emailSender = emailSender;
	}
	
	@Transactional
	@Override
	public String register(ReserveForm registerForm, UserInfo userinfo) {
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
		
		
		//ユーザ情報の初期化
		
		reserve.setUser(userinfo);
		
		String userId = userinfo.getUserId();
		reserve.setUserId(userId);
		
		//キャンセルフラグを０で初期化
		reserve.setCancel("0");
		
		
		//チェックアウト日をチェックインの翌日に設定
		LocalDate checkIn = reserve.getCheckIn();
		reserve.setCheckOut(checkIn.plusDays(1));
		
		
		String newId = reserveDAO.findNewId(checkIn);
		reserve.setReserveId(newId);
		try {
			if( reserveDAO.insert(reserve) == 1) {
				
				emailSender.send(userinfo.getEmail(), "ご予約の案内", "本日はご予約ありがとうございました。", false);
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
	@Override
	public ReserveForm input(ReserveForm registerForm) {
		// TODO 自動生成されたメソッド・スタブ
		registerForm.setMeal(true);
		registerForm.setStayNumberOfPeople(1);
		return registerForm;
	}
	
	@Override
	public ReserveForm confirm(ReserveForm registerForm) {
		// TODO 自動生成されたメソッド・スタブ
		//お部屋情報を取得する。
		Room room = roomService.findById(registerForm.getRoomId());
		//Formに１泊二日の宿泊料金をセット
		registerForm.setAmount(room.getPrice() * registerForm.getStayNumberOfPeople());
		return registerForm;
	}

}
