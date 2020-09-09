package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import web.entity.User;
import web.form.RegisterForm;
import web.service.UserService;
import web.util.Message;
import web.util.ScreenName;

@Controller
public class UserController {
	private static final String REGISTER = "register";

	@Autowired
	UserService userService;

	@GetMapping(REGISTER)
	public String getRegister(@ModelAttribute RegisterForm registerForm) {
		return ScreenName.REGISTER;
	}

	@PostMapping(REGISTER)
	public String postRegister(@Validated @ModelAttribute RegisterForm registerForm, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return ScreenName.REGISTER;
		}

		if (!registerForm.getPassword().equals(registerForm.getRePassword())) {
			model.addAttribute("msg", Message.PASSWORD_IS_NOT_MATCH);
			return ScreenName.REGISTER;
		}

		User user = new User();
		user.setLoginId(registerForm.getLoginId());
		user.setUserName(registerForm.getUserName());
		user.setPassword(registerForm.getPassword());

		if (userService.register(user) <= 0) {
			return ScreenName.REGISTER;
		}

		return "redirect:/" + ScreenName.LOGIN;
	}
}
