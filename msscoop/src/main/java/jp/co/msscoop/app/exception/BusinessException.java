package jp.co.msscoop.app.exception;




public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public BusinessException(String errorMessage){
		super(errorMessage);
		this.message = errorMessage;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

	
	
	
	
	

}
