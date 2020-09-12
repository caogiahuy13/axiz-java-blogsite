package web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import web.entity.Article;
import web.entity.User;
import web.form.RegisterForm;
import web.form.UpdateUserForm;
import web.service.ArticleService;
import web.service.UserService;
import web.util.Message;
import web.util.ScreenName;
import web.util.SessionName;

@Controller
public class UserController {
	private static final String REGISTER = "register";
	private static final String MY_PAGE = "myPage";
	private static final String MY_ARTICLES = "myArticles";
	private static final String UPDATE_MEMBER = "updateMember";
	private static final String DELETE_MEMBER = "deleteMember";

	@Autowired
	UserService userService;

	@Autowired
	ArticleService articleService;

	@Autowired
	HttpSession session;

	@GetMapping(REGISTER)
	public String getRegister(@ModelAttribute RegisterForm registerForm, Model model) {
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
		user.setGender(registerForm.getGender());
		user.setBirthYear(registerForm.getBirthYear());

		if (userService.register(user) <= 0) {
			return ScreenName.REGISTER;
		}

		user = userService.authenticate(user.getLoginId(), user.getPassword());
		session.setAttribute(SessionName.CURRENT_USER, user);

		return "redirect:/" + ScreenName.MY_PAGE;
	}

	@GetMapping(MY_PAGE)
	public String getMyPage() {
		return ScreenName.MY_PAGE;
	}

	@GetMapping(MY_ARTICLES)
	public String getMyArticles(Model model) {
		User currentUser = (User) session.getAttribute(SessionName.CURRENT_USER);

		List<Article> articles = articleService.findByUserId(currentUser.getUserId());

		if (articles.isEmpty()) {
			model.addAttribute("msg", Message.MY_ARTICLES_NO_RESULT);
		}

		model.addAttribute("articles", articles);

		return ScreenName.MY_ARTICLES;
	}

	@GetMapping(UPDATE_MEMBER)
	public String getUpdateMember(@ModelAttribute UpdateUserForm updateUserForm) {
		User currentUser = (User) session.getAttribute(SessionName.CURRENT_USER);

		updateUserForm.setUserId(currentUser.getUserId());
		updateUserForm.setLoginId(currentUser.getLoginId());
		updateUserForm.setUserName(currentUser.getUserName());
		updateUserForm.setPassword(currentUser.getPassword());
		updateUserForm.setGender(currentUser.getGender());
		updateUserForm.setBirthYear(currentUser.getBirthYear());
		updateUserForm.setIntroduction(currentUser.getIntroduction());
		updateUserForm.setMySpace(currentUser.getMySpace());

		return ScreenName.UPDATE_MEMBER;
	}

	@PostMapping(UPDATE_MEMBER)
	public String postUpdateMember(@Validated @ModelAttribute UpdateUserForm updateUserForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return ScreenName.UPDATE_MEMBER;
		}

		if (!updateUserForm.getPassword().equals(updateUserForm.getRePassword())) {
			model.addAttribute("msg", Message.PASSWORD_IS_NOT_MATCH);
			return ScreenName.UPDATE_MEMBER;
		}

		User existedUser = userService.findByLoginId(updateUserForm.getLoginId());
		if (existedUser != null && existedUser.getUserId() != updateUserForm.getUserId()) {
			model.addAttribute("msg", Message.LOGIN_ID_IS_EXISTED);
			return ScreenName.UPDATE_MEMBER;
		}

		User user = userService.findByUserId(updateUserForm.getUserId());
		user.setLoginId(updateUserForm.getLoginId());
		user.setUserName(updateUserForm.getUserName());
		user.setPassword(updateUserForm.getPassword());
		user.setGender(updateUserForm.getGender());
		user.setBirthYear(updateUserForm.getBirthYear());
		user.setIntroduction(updateUserForm.getIntroduction());
		user.setMySpace(updateUserForm.getMySpace());

		userService.update(user);

		User updatedUser = userService.findByUserId(user.getUserId());

		session.setAttribute(SessionName.CURRENT_USER, updatedUser);

		return ScreenName.MY_PAGE;
	}

	@GetMapping(DELETE_MEMBER)
	public String getDeleteMember() {
		return ScreenName.DELETE_MEMBER;
	}

	@PostMapping(DELETE_MEMBER)
	public String postDeleteMember(Model model) {
		User currentUser = (User) session.getAttribute(SessionName.CURRENT_USER);

		if (userService.deleteByLoginId(currentUser.getLoginId()) > 0) {
			session.removeAttribute(SessionName.CURRENT_USER);
		}

		model.addAttribute("msg", Message.MEMBER_DELETE_SUCCESS);
		return ScreenName.NOTIFICATION;
	}
}
