package jp.co.sample.emp_management.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.emp_management.domain.Administrator;
import jp.co.sample.emp_management.domain.Reminder;
import jp.co.sample.emp_management.repository.AdministratorRepository;
import jp.co.sample.emp_management.repository.ReminderRepository;

@Service
@Transactional
public class ReminderService {
	
	@Autowired
	private AdministratorRepository administratorRepository;
//	private AdministratorService administratorService;
	
	@Autowired
	private MailSendService mailSendService;
	
	@Autowired
	private ReminderRepository reminderRepository;
	
	public Reminder findByRandomStr(String randomStr) {
		return reminderRepository.findByRandomStr(randomStr);
	}
	
	public void sendUrl(String mailAddress) {
		Administrator administrator = administratorRepository.findByMailAddress(mailAddress);
		
		if (administrator != null) {
			String randomStr = UUID.randomUUID().toString();
			System.out.println(randomStr);
			// reminderテーブルにinsert
			Reminder reminder = new Reminder();
			reminder.setAdministratorId(administrator.getId());
			reminder.setRandomStr(randomStr);
			reminder = reminderRepository.insert(reminder);
			System.out.println(reminder);
			// メール送信
			mailSendService.sendRemindMail(mailAddress, randomStr);
		}
	}
	
	public void resetPassword(String randomStr, String password) {
		Reminder reminder = reminderRepository.findByRandomStr(randomStr);
		Administrator administrator = administratorRepository.load(reminder.getAdministratorId());
		if (administrator != null) {
			administratorRepository.updatePassword(administrator.getId(), password);
			reminderRepository.updateDelFlg(reminder.getId());
		}
		
	}
}
