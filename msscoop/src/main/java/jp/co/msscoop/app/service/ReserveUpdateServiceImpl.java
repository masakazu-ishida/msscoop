package jp.co.msscoop.app.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import jp.co.msscoop.app.common.EMailSender;
import jp.co.msscoop.app.dao.ReservableRoomInfoDAO;
import jp.co.msscoop.app.dao.ReserveDAO;
import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.form.ReserveForm;
import jp.co.msscoop.app.session.UserSession;


@Service
public class ReserveUpdateServiceImpl implements ReserveUpdateService {
	
	private final UserSession userSession;
	private final ReserveDAO reserveDAO;
	private final MessageSource messageSource;
	private final EMailSender emailSender;
	
	public ReserveUpdateServiceImpl(UserSession userSession,ReserveDAO reserveDAO, MessageSource messageSource,EMailSender emailSender) {
		this.userSession  =userSession;
		this.reserveDAO = reserveDAO;
		this.messageSource = messageSource;
		this.emailSender = emailSender;
	}
	

	@Override
	public ReserveForm input() {
		// TODO 自動生成されたメソッド・スタブ
		
		UserInfo loginUser = userSession.getLoginUser();
		
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
