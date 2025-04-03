package jp.co.msscoop.app.exception;



/**
 * [概要]<br>
 * 業務例外。ある処理を一部やり直す場合に使用。
 */
public class BusinessException extends RuntimeException {
	
	/**
	 * シリアライズ時のバージョン番号
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * エラーメッセージ
	 */
	private String message;
	
	/**
	 * 
	 * [概要]<br>
	 * コンストラクタ<br><br>
	 * 
	 * [処理内容]<br>
	 * 1.スーパークラスのStringを一個引数に取るコンストラクタを呼ぶ。
	 * 2.このクラスのメンバ変数messageに引数errorMessageをセット
	 * @param errorMessage エラーメッセージ
	 */
	public BusinessException(String errorMessage){
		super(errorMessage);
		this.message = errorMessage;
	}
	
	/**
	 * 
	 * [概要]<br>
	 * メンバ変数messageを返す<br><br>
	 *
	 * @return エラーメッセージを返す
	 **/ 
	@Override
	public String getMessage() {
		return message;
	}

	
	
	
	
	

}
