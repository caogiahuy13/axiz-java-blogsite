package web.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;

import web.entity.Article;
import web.entity.ReactionsByArticle;
import web.entity.User;
import web.form.UpdateUserForm;
import web.service.ArticleService;
import web.service.ReactionService;
import web.service.UserService;
import web.service.ViewService;
import web.util.Message;
import web.util.ScreenName;
import web.util.SessionUtil;

@Controller
public class UserController {
	private static final String MY_PAGE = "myPage";
	private static final String MY_ARTICLES = "myArticles";
	private static final String UPDATE_MEMBER = "updateMember";
	private static final String DELETE_MEMBER = "deleteMember";
	private static final String ANALYTICS = "analytics";

	@Autowired
	UserService userService;

	@Autowired
	ArticleService articleService;

	@Autowired
	ReactionService reactionService;

	@Autowired
	ViewService viewService;

	@Autowired
	HttpSession session;

	@GetMapping(MY_PAGE)
	public String getMyPage(Model model, @RequestParam(defaultValue = "1") String pageNumber) {
		User currentUser = (User) session.getAttribute(SessionUtil.CURRENT_USER);

		final Integer LIMIT = 2;

		List<? extends Article> articles = articleService.findByUserIdPagination(currentUser.getUserId(),
				Integer.parseInt(pageNumber), LIMIT);
		int articleMaxPage = articleService.countByUserId(currentUser.getUserId()) / 2 + 1;

		model.addAttribute("articles", articles);
		model.addAttribute("articleMaxPage", articleMaxPage);
		model.addAttribute("articleCurPage", Integer.parseInt(pageNumber));

		return ScreenName.MY_PAGE;
	}

	@GetMapping(MY_ARTICLES)
	public String getMyArticles(Model model) {
		User currentUser = (User) session.getAttribute(SessionUtil.CURRENT_USER);

		List<? extends Article> articles = articleService.findByUserId(currentUser.getUserId());

		if (articles.isEmpty()) {
			model.addAttribute("msg", Message.MY_ARTICLES_NO_RESULT);
		}

		model.addAttribute("articles", articles);

		return ScreenName.MY_ARTICLES;
	}

	@GetMapping(UPDATE_MEMBER)
	public String getUpdateMember(@ModelAttribute UpdateUserForm updateUserForm) {
		User currentUser = (User) session.getAttribute(SessionUtil.CURRENT_USER);

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

		session.setAttribute(SessionUtil.CURRENT_USER, updatedUser);

		return ScreenName.MY_PAGE;
	}

	@GetMapping(DELETE_MEMBER)
	public String getDeleteMember() {
		return ScreenName.DELETE_MEMBER;
	}

	@PostMapping(DELETE_MEMBER)
	public String postDeleteMember(Model model) {
		User currentUser = (User) session.getAttribute(SessionUtil.CURRENT_USER);

		if (userService.deleteByLoginId(currentUser.getLoginId()) > 0) {
			SessionUtil.removeAllSession(session);
		}

		model.addAttribute("msg", Message.MEMBER_DELETE_SUCCESS);
		return ScreenName.NOTIFICATION;
	}

	@GetMapping(ANALYTICS)
	public String getAnalytics(Model model) {
		User currentUser = (User) session.getAttribute(SessionUtil.CURRENT_USER);
		if (currentUser == null) {
			return "redirect:/" + ScreenName.TOP;
		}

		HashMap<String, Integer> genderAnalytics = reactionService
				.countByGenderByUserIdOfArticle(currentUser.getUserId());
		HashMap<String, Integer> ageAnalytics = reactionService
				.countByAgeRangeByUserIdOfArticle(currentUser.getUserId());
		HashMap<String, Integer> accessAnalytics = viewService
				.countByAccessByUserIdOfArticle(currentUser.getUserId());
		List<ReactionsByArticle> reactionAnalytics = reactionService
				.countMultipleByUserIdOfArticle(currentUser.getUserId());

		model.addAttribute("genderAnalytics", genderAnalytics);
		model.addAttribute("ageAnalytics", ageAnalytics);
		model.addAttribute("accessAnalytics", accessAnalytics);
		model.addAttribute("reactionAnalytics", reactionAnalytics);

		return ScreenName.ANALYTICS;
	}

}
