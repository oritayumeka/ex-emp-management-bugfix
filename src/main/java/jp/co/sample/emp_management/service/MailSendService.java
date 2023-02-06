package jp.co.sample.emp_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {

	@Autowired
	private MailSender mailSender;
	
	public void sendRemindMail(String mailAddress, String randomStr) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("emp-management-admin@example.com");
        msg.setTo(mailAddress);
        msg.setSubject("【従業員管理システム】パスワード再設定URL送信");
        msg.setText("http://localhost:8080/reminder/reset/" + randomStr);
        try {
            mailSender.send(msg);
        } catch (MailException e) {
            e.printStackTrace();
        }	
	}
	

}
