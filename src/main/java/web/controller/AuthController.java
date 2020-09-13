package web.controller;

import javax.servlet.http.HttpSession;

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
import web.service.ReactionService;
import web.service.UserService;
import web.util.Message;
import web.util.ScreenName;
import web.util.SessionUtil;

@Controller
public class AuthController {
	private static final String LOGIN = "login";
	private static final String LOGOUT = "logout";

	@Autowired
	UserService userService;

	@Autowired
	ReactionService reactionService;

	@Autowired
	HttpSession session;

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

		int reactionCount = reactionService.countByUserId(user.getUserId());

		session.setAttribute(SessionUtil.CURRENT_USER, user);
		session.setAttribute(SessionUtil.TOTAL_REACTIONS, reactionCount);

		return "redirect:/" + ScreenName.MY_PAGE;
	}

	@GetMapping(LOGOUT)
	public String logout(@ModelAttribute LoginForm loginForm) {
		SessionUtil.removeAllSession(session);
		return ScreenName.LOGIN;
	}

}
