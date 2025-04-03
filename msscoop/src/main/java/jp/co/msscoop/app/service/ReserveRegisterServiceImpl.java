package jp.co.msscoop.app.service;


import java.time.LocalDate;
import java.util.Locale;


import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import jp.co.msscoop.app.dao.ReservableRoomInfoDAO;
import jp.co.msscoop.app.dao.ReserveDAO;
import jp.co.msscoop.app.dto.ReservableRoomInfo;
import jp.co.msscoop.app.dto.Reserve;
import jp.co.msscoop.app.dto.Room;
import jp.co.msscoop.app.dto.UserInfo;
import jp.co.msscoop.app.exception.BusinessException;
import jp.co.msscoop.app.exception.UseCaseException;
import jp.co.msscoop.app.form.ReserveForm;

/**
 * [概要]<br>
 * 予約登録機能に対するサービスインターフェース実装クラス
 */
@Service
public class ReserveRegisterServiceImpl implements ReserveRegisterService {

	/**
	 * 予約DAOインターフェース
	 */
	private final ReserveDAO reserveDAO;
	
	/**
	 * 空室検索DAOインターフェース
	 */
	private final ReservableRoomInfoDAO reservableRoomInfoDAO;
	
	/**
	 * messageas.propertiesアクセス用インターフェース
	 */
	private final MessageSource messageSource;
	
	
	/**
	 * 予約に必要な部屋情報アクセス用インターフェース
	 */
	private final RoomService roomService;
	
	
	/**
	 * 
	 * [概要]
	 * 予約登録に必要なオブジェクトのインターフェースをコンストラクタインジェクションする
	 
	 * @param reserveDAO
	 * @param reservableRoomInfoDAO
	 * @param messageSource
	 * @param roomService
	 */
	public ReserveRegisterServiceImpl(ReserveDAO reserveDAO, ReservableRoomInfoDAO reservableRoomInfoDAO,MessageSource messageSource,RoomService roomService){
		this.reserveDAO = reserveDAO;
		this.reservableRoomInfoDAO = reservableRoomInfoDAO;
		this.messageSource = messageSource;
		this.roomService = roomService;
		
	}
	
	/**
	 * [概要]<br>
	 * 予約の登録を実行する
	 * 
	 * [処理内容]<br>
	 * @param registerForm
	 * @param userinfo
	 * @return 
	 */
	@Transactional
	@Override
	public String register(ReserveForm registerForm, UserInfo userinfo) {
		
		//ReservableRoomInfo型変数reservedInfoを宣言
		//reservableRoomInfoDAO.findByIdを呼び出し、戻り値をreservedInfoにセット。部屋・日時の予約バッティングをしないよう、ロックを掛ける
		//第一引数　本メソッドの引数registerFormの部屋IDを渡す
		//第二引数　本メソッドの引数registerFormのチェックイン日付を渡す
		ReservableRoomInfo reservedInfo = reservableRoomInfoDAO.findById(registerForm.getRoomId(), registerForm.getCheckIn());
		
		//reservedInfoがNULLでないかをチェックする（NULLなら予約済みなので空室検索画面に戻る）
		if(reservedInfo != null) {
			//trueの時の処理
			//String型変数messageを宣言。
			//プロパティファイルmessages.propertiesからキー"bus.error.pastday"のメッセージを取得し、変数messageを初期化
			String message = messageSource.getMessage("bus.error.already_reserved", null, Locale.JAPAN);
			//引数messageで初期値を与えてBusinessExceptionをインスタンス化し、throwする。（Controllerでキャッチして空室検索画面に戻る）
			throw new BusinessException(message);
		}
		
		//Reserve型変数reserveをインスタンス化して初期化
		Reserve reserve = new Reserve();
		
		//BeanUtils.copyPropertiesメソッドを呼び出す。reserveをフォームの中身で初期化
		//第一引数　本メソッドの引数registerForm
		//第二引数　Reserve型変数reserve		
		BeanUtils.copyProperties(registerForm, reserve);
		
		
		//ユーザ情報の初期化
		//reserve.setUserを呼び出す。引数には本メソッドの引数userinfoを引き渡す
		reserve.setUser(userinfo);
	
		String userId = userinfo.getUserId();
		
		//reserve.setUserIdを呼び出す。引数にuserinfoのgetUserIdの戻り値を渡す
		reserve.setUserId(userId);
		
		//reserve.setCancelを呼び出し、引数に０を引き渡す
		reserve.setCancel("0");
		
		
		
		LocalDate checkIn = reserve.getCheckIn();
		
		//reserve.setCheckOutを呼び出す。引数にはチェックイン日付＋１の日付を渡す
		reserve.setCheckOut(checkIn.plusDays(1));
		
		
		//String型変数newIdを宣言し、reserveDAO.findNewIdを呼び出し、戻り値で初期化
		//第一引数　チェックイン日付
		String newId = reserveDAO.findNewId(checkIn);
		
		//reserve.setReserveIdを呼び出す
		//第一引数　newId日付
		reserve.setReserveId(newId);
		try {
			//int型変数resultを宣言し、reserveDAO.insertで初期化
			//第一引数　reserve
			
			//resultが１（登録に成功した）かどうか条件判定
			if( reserveDAO.insert(reserve) == 1) {
				//true:newIdを返す
				return newId;
			}
			else {
				//falseの時の処理
				//String型変数messageを宣言。
				//プロパティファイルmessages.propertiesからキー"bus.error.register_error"のメッセージを取得し、変数messageを初期化
				String message = messageSource.getMessage("bus.error.register_error", null, Locale.JAPAN);
				//引数messageで初期値を与えてUseCaseExceptionをインスタンス化し、throwする。（Controllerでキャッチして空室検索画面に戻る）
				throw new UseCaseException(message);
			}
		}
		//例外がスローされた場合catchする。引数にはExceptionを指定する
		catch(Exception e) {
			//引数Exceptionをそのまま呼び出し側にスロー
			throw e;
		}
	}
	
	/**
	 * [概要]<br>
	 * 予約登録用画面を表示するための初期化処理を実行
	 * [処理内容]<br>
	 * 
	 * @param registerForm
	 * @return 
	 */
	
	@Override
	public ReserveForm input(ReserveForm registerForm) {
		
		//　registerForm.setInDoorBathRoomを呼び出す。初期値は『内風呂あり』とする
		registerForm.setInDoorBathRoom(true);
		
		//　registerForm.setMealを呼び出す。初期値は『食事あり』とする
		registerForm.setMeal(true);
		
		
		
		//　registerForm.setStayNumberOfPeopleを呼び出す。初期値として2を指定（宿泊人数２名をデフォルト）
		registerForm.setStayNumberOfPeople(2);
		
		// 初期化済みのregisterFormを返す
		return registerForm;
	}
	
	/**
	 * [概要]<br>
	 * 予約登録内容を確認する
	 * 
	 * [処理内容]<br>
	 * 
	 * @param registerForm
	 * @return 
	 */
	
	@Override
	public ReserveForm confirm(ReserveForm registerForm) {
		// TODO 自動生成されたメソッド・スタブ
		
		
		//Room型変数roomを宣言し、roomService.findByIdを呼び出し、戻り値で初期化する
		Room room = roomService.findById(registerForm.getRoomId());
		
		//registerForm.setAmountを呼び出す。一泊二日×人数分の宿泊料金を設定
		registerForm.setAmount(room.getPrice() * registerForm.getStayNumberOfPeople());
		
		//
		return registerForm;
	}

}
