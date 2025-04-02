package jp.co.msscoop.app.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import jp.co.msscoop.app.common.SystemDateUtil;
import jp.co.msscoop.app.common.SystemDateUtilFormDevelop;
import jp.co.msscoop.app.dao.ReservableRoomInfoDAO;
import jp.co.msscoop.app.dto.ReservableRoomInfo;
import jp.co.msscoop.app.exception.BusinessException;

/**
 * 
 */
@Service
public class ReservableSearchServiceImpl implements ReservableSearchService {

	private final MessageSource messageSource;
	private final SystemDateUtil systemDateUtil;
	private final ReservableRoomInfoDAO reservableRoomInfoDAO;
	
	
	public ReservableSearchServiceImpl(ReservableRoomInfoDAO reservableRoomInfoDAO,MessageSource messageSource,SystemDateUtil systemDateUtil){
		this.messageSource = messageSource;
		this.reservableRoomInfoDAO = reservableRoomInfoDAO;
		this.systemDateUtil = systemDateUtil;
		
	}
	
	@Override
	public List<ReservableRoomInfo> search(LocalDate checkin, LocalDate checkout,boolean indoorBathRoom,
			boolean smoking) {
		// TODO 自動生成されたメソッド・スタブ
		long diffDays = ChronoUnit.DAYS.between(checkin, checkout);
		
		//チェックインインとチェックアウトが同一日付の場合前の画面に戻る
		if(diffDays == 0) {
			String message = messageSource.getMessage("bus.error.sameday", null, Locale.JAPAN);
			throw new BusinessException(message);
		}
		
		//チェックアウトがチェックインインより過去日付の場合前の画面に戻る
		if(diffDays < 0) {
			String message = messageSource.getMessage("bus.error.pastday", null, Locale.JAPAN);
			throw new BusinessException(message);
		}
		
		diffDays = ChronoUnit.DAYS.between(systemDateUtil.today() ,checkin);
		if(diffDays <= 0){
			String message = messageSource.getMessage("bus.error.tomorrow", null, Locale.JAPAN);
			throw new BusinessException(message);
		}
		
		
		
		//ReservableRoomInfoはあくまで予約可能日（つまりチェックイン可能日）として登録されている。
		//よってチェックアウト日の一日前を検索範囲とするため、チェックアウト日から１を引いた値を引数に渡す
		LocalDate finalCheckIn = checkout.minusDays(1);
		List<ReservableRoomInfo> list = reservableRoomInfoDAO.search(checkin, finalCheckIn, indoorBathRoom, smoking);
		
		
		if(list.isEmpty()) {
			String message = messageSource.getMessage("bus.error.searchresult_zero", null, Locale.JAPAN);
			throw new BusinessException(message);
		}
		
		for(ReservableRoomInfo roomInfo : list) {
			
			//画像のブラウザキャッシュを防ぐため、画像パスの末尾に【?version=ランダム値】を埋める
			Double randamValue = new Double(Math.random() * 100);
			String path = roomInfo.getRoom().getRoomImage() + "?version=" +  LocalDate.now().toString() +  randamValue.toString();
			roomInfo.getRoom().setRoomImage(path);
		}
		return list;
	}

}
