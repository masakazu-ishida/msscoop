package jp.co.msscoop.app.common;

public interface EMailSender {
	 boolean send(String mailAddress, String subject, String message, boolean bHtmlMail);
	

}
