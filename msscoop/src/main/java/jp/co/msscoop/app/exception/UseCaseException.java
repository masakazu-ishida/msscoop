package jp.co.msscoop.app.exception;



/**
 * [概要]<br>
 * ユースケース例外。業務例外の一種だが、ある機能を最初からやり直す場合にスローする。
 */
public class UseCaseException extends RuntimeException {

	/**
	 * シリアライズ時のバージョン番号
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public UseCaseException(String errorMessage){
		super(errorMessage);
		this.message = errorMessage;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	

}
