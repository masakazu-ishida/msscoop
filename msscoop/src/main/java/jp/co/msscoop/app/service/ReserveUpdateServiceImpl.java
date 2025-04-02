package jp.co.msscoop.app.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import jp.co.msscoop.app.common.EMailSender;
import jp.co.msscoop.app.dao.ReservableRoomInfoDAO;
import jp.co.msscoop.app.dao.ReserveDAO;
import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.form.ReserveForm;

/**
 * [概要]<br>
 * 
 */
@Service
public class ReserveUpdateServiceImpl implements ReserveUpdateService {
	
	private final ReserveDAO reserveDAO;
	private final MessageSource messageSource;
	private final EMailSender emailSender;
	
	public ReserveUpdateServiceImpl(ReserveDAO reserveDAO, MessageSource messageSource,EMailSender emailSender) {
		this.reserveDAO = reserveDAO;
		this.messageSource = messageSource;
		this.emailSender = emailSender;
	}
	

	@Override
	public ReserveForm input() {
		// TODO 自動生成されたメソッド・スタブ
		
		
		return null;
	}

	@Override
	public ReserveForm confirm(ReserveForm registerForm) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String register(ReserveForm registerForm) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
