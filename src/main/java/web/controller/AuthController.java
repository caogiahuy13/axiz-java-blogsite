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

import web.entity.Member;
import web.form.LoginForm;
import web.form.RePassForm;
import web.form.RegisterForm;
import web.service.MemberService;
import web.service.ReactionService;
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
	MemberService memberService;

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

		Member member = memberService.authenticate(loginForm.getLoginId(), loginForm.getPassword());

		if (member == null) {
			model.addAttribute("msg", Message.LOGIN_FAIL);
			return ScreenName.LOGIN;
		}

		int reactionCount = reactionService.countByMemberId(member.getMemberId());

		session.setAttribute(SessionUtil.CURRENT_MEMBER, member);
		session.setAttribute(SessionUtil.TOTAL_REACTIONS, reactionCount);

		return "redirect:/" + ScreenName.MY_PAGE + "?id=" + member.getMemberId();
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

		Member registerMember = (Member) session.getAttribute(SessionUtil.REGISTER_MEMBER);

		if (!rePassForm.getRePass().equals(registerMember.getPassword())) {
			model.addAttribute("msg", Message.PASSWORD_IS_NOT_MATCH);
			return ScreenName.REGISTER_CONFIRM;
		}

		if (memberService.register(registerMember) <= 0) {
			return ScreenName.REGISTER;
		}

		Member currentMember = memberService.authenticate(registerMember.getLoginId(), registerMember.getPassword());

		session.setAttribute(SessionUtil.CURRENT_MEMBER, currentMember);
		session.removeAttribute(SessionUtil.REGISTER_MEMBER);

		return "redirect:/" + ScreenName.MY_PAGE + "?id=" + currentMember.getMemberId();
	}

	@PostMapping(REGISTER_CONFIRM)
	public String postRegisterConfirm(@Validated @ModelAttribute RegisterForm registerForm, BindingResult bindingResult,
			Model model, @ModelAttribute RePassForm rePassForm) {

		if (bindingResult.hasErrors()) {
			return ScreenName.REGISTER;
		}

		if (memberService.findByLoginId(registerForm.getLoginId()) != null) {
			model.addAttribute("msg", Message.LOGIN_ID_IS_EXISTED);
			return ScreenName.REGISTER;
		}

		Member member = new Member();
		member.setLoginId(registerForm.getLoginId());
		member.setName(registerForm.getName());
		member.setNickname(registerForm.getNickname());
		member.setGenderName(registerForm.getGenderName());
		member.setBirthdate(registerForm.getBirthdate());
		member.setPassword(registerForm.getPassword());

		session.setAttribute(SessionUtil.REGISTER_MEMBER, member);

		return ScreenName.REGISTER_CONFIRM;
	}
}
