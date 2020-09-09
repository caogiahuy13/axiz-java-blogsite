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
import web.form.LoginForm;
import web.service.UserService;
import web.util.Message;
import web.util.ScreenName;

@Controller
public class AuthController {
	private static final String LOGIN = "login";

	@Autowired
	UserService userService;

	@GetMapping(LOGIN)
	public String getLogin(@ModelAttribute LoginForm loginForm) {
		return ScreenName.LOGIN;
	}

	@PostMapping(LOGIN)
	public String postLogin(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return ScreenName.LOGIN;
		}

		User user = userService.authenticate(loginForm.getLoginId(), loginForm.getPassword());

		if (user == null) {
			model.addAttribute("msg", Message.LOGIN_FAIL);
			return ScreenName.LOGIN;
		}

		return ScreenName.MENU;
	}

}
