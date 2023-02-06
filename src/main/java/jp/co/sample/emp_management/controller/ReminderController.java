package jp.co.sample.emp_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.emp_management.domain.Reminder;
import jp.co.sample.emp_management.form.ResetPasswordForm;
import jp.co.sample.emp_management.service.ReminderService;

@Controller
@RequestMapping("/reminder")
public class ReminderController {

	@Autowired
	private ReminderService reminderService;
	
	@GetMapping("/")
	public String toRequest() {
		return "reminder/request";
	}

	@PostMapping("/send")
	public String send(String mailAddress, RedirectAttributes redirect) {
		reminderService.sendUrl(mailAddress);
		redirect.addFlashAttribute("message", true);
		return "redirect:/reminder/sendFinish";
	}
	
	@GetMapping("/sendFinish")
	public String sendFinish() {
		return "reminder/request";   // 同じ再発行依頼画面で送った旨の文言だけ表示
	}

	@GetMapping("/reset/{param}")
	public String reset(@PathVariable("param") String randomStr, ResetPasswordForm form) {
		Reminder reminder = reminderService.findByRandomStr(randomStr);
		if (reminder != null) {
			form.setRandomStr(randomStr);
			return "reminder/reset";
		}
		return "reminder/valid";
	}
	
	@PostMapping("/update")
	public String update(@Validated ResetPasswordForm form
			, BindingResult result
			, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "reminder/reset";
		}
		if (!form.getPassword().equals(form.getConfirmPassword())) {
			result.rejectValue("confirmPassword", "error.passwordNotMatch");
			return "reminder/reset";
		}
		reminderService.resetPassword(form.getRandomStr(), form.getPassword());
		return "redirect:/reminder/finish";
	}
	
	@GetMapping("/finish")
	public String finish() {
		return "reminder/finish";
	}
	
}
