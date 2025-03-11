package jp.co.msscoop.app.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Component
public class EMailSenderImpl implements EMailSender{
	private final JavaMailSender javaMailSender;
	 
	 @Autowired
	public EMailSenderImpl(JavaMailSender javaMailSender) {
		 this.javaMailSender = javaMailSender;
		 
	}
	
	public boolean send(String mailAddress, String subject, String message, boolean bHtmlMail) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();  // 2. MimeMessage の作成
        try {
            // 3. MimeMessageHelper を使用して MIME メッセージを構成
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom("me@mail.com");
            helper.setTo(mailAddress);
            helper.setSubject(subject);
            helper.setText(message, bHtmlMail);  // 第二引数に true を指定することで、Content-Type が text/html になる
            javaMailSender.send(mimeMessage);  // 4. メールの送信
            return false;
		} catch (MessagingException | MailException ex) {
			System.err.println(ex.getMessage());
        }
		return false;
	}
}
