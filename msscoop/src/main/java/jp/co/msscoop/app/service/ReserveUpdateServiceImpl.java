package jp.co.msscoop.app.service;

import java.util.Locale;

import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.util.BeanUtil;

import jp.co.msscoop.app.dao.ReservableRoomInfoDAO;
import jp.co.msscoop.app.dao.ReserveDAO;
import jp.co.msscoop.app.dto.Reserve;
import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.exception.UseCaseException;
import jp.co.msscoop.app.form.ReserveForm;

/**
 * [概要]<br>
 * 予約変更機能に対するサービスインターフェース実装クラス
 */
@Transactional
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
	public ReserveForm input(String reserveId,ReserveForm reserveForm) {
		// TODO 自動生成されたメソッド・スタブ
		
		Reserve reserveDTO = reserveDAO.findById(reserveId);
		BeanUtils.copyProperties(reserveDTO, reserveForm);
		
		//reserveForm.setStayNumberOfPeople(reserveDTO.getStayNumberOfPeople());
		//reserveForm.setAmount(reserveDTO.getStayNumberOfPeople() * reserveDTO.getRoom().getPrice());
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

	
	@Override
	public boolean update(ReserveForm updateForm) {
		
		Reserve reserveDTO = reserveDAO.findById(updateForm.getReserveId());
		
		BeanUtils.copyProperties(updateForm,reserveDTO);
		
		if (reserveDAO.update(reserveDTO) == 1) {
			return true;
		}
		else {
				//falseの時の処理
				//String型変数messageを宣言。
				//プロパティファイルmessages.propertiesからキー"bus.error.update_error"のメッセージを取得し、変数messageを初期化
				String message = messageSource.getMessage("bus.error.update_error", null, Locale.JAPAN);
				//引数messageで初期値を与えてUseCaseExceptionをインスタンス化し、throwする。（Controllerでキャッチして空室検索画面に戻る）
				throw new UseCaseException(message);
			}
	}
}

