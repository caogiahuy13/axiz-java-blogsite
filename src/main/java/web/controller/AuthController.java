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
import web.form.RePassForm;
import web.form.RegisterForm;
import web.service.ReactionService;
import web.service.UserService;
import web.util.Message;
import web.util.ScreenName;
import web.util.SessionUtil;

@Controller
public class AuthController {
	private static final String LOGIN = "login";
	private static final String LOGOUT = "logout";
	private static final String REGISTER = "register";
	private static final String REGISTER_CONFIRM = "registerConfirm";
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

		return "redirect:/" + ScreenName.MY_PAGE + "?id=" + user.getUserId();
	}

	@GetMapping(LOGOUT)
	public String logout(@ModelAttribute LoginForm loginForm) {
		SessionUtil.removeAllSession(session);
		return ScreenName.LOGIN;
	}

	@GetMapping(REGISTER)
	public String getRegister(@ModelAttribute RegisterForm registerForm) {
		return ScreenName.REGISTER;
	}

	@PostMapping(REGISTER)
	public String postRegister(@Validated @ModelAttribute RePassForm rePassForm, BindingResult bindingResult,
			Model model, @ModelAttribute RegisterForm registerForm) {
		if (bindingResult.hasErrors()) {
			return ScreenName.REGISTER_CONFIRM;
		}

		User registerUser = (User) session.getAttribute(SessionUtil.REGISTER_USER);

		if (!rePassForm.getRePass().equals(registerUser.getPassword())) {
			model.addAttribute("msg", Message.PASSWORD_IS_NOT_MATCH);
			return ScreenName.REGISTER_CONFIRM;
		}

		if (userService.register(registerUser) <= 0) {
			return ScreenName.REGISTER;
		}

		User currentUser = userService.authenticate(registerUser.getLoginId(), registerUser.getPassword());

		session.setAttribute(SessionUtil.CURRENT_USER, currentUser);
		session.removeAttribute(SessionUtil.REGISTER_USER);

		return "redirect:/" + ScreenName.MY_PAGE + "?id=" + currentUser.getUserId();
	}

	@PostMapping(REGISTER_CONFIRM)
	public String postRegisterConfirm(@Validated @ModelAttribute RegisterForm registerForm, BindingResult bindingResult,
			Model model, @ModelAttribute RePassForm rePassForm) {

		if (bindingResult.hasErrors()) {
			return ScreenName.REGISTER;
		}

		if (userService.findByLoginId(registerForm.getLoginId()) != null) {
			model.addAttribute("msg", Message.LOGIN_ID_IS_EXISTED);
			return ScreenName.REGISTER;
		}

		User user = new User();
		user.setLoginId(registerForm.getLoginId());
		user.setUserName(registerForm.getUserName());
		user.setNickname(registerForm.getNickname());
		user.setGender(registerForm.getGender());
		user.setBirthdate(registerForm.getBirthdate());
		user.setPassword(registerForm.getPassword());

		session.setAttribute(SessionUtil.REGISTER_USER, user);

		return ScreenName.REGISTER_CONFIRM;
	}
}
