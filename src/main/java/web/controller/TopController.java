package web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import web.entity.Article;
import web.entity.User;
import web.form.SearchForm;
import web.service.ArticleService;
import web.util.Message;
import web.util.ScreenName;
import web.util.SessionName;

@Controller
public class TopController {
	private static final String TOP = "top";

	@Autowired
	ArticleService articleService;

	@Autowired
	HttpSession session;

	@GetMapping(TOP)
	public String getTop(@ModelAttribute SearchForm searchForm) {
		return ScreenName.TOP;
	}

	@PostMapping(TOP)
	public String search(@ModelAttribute SearchForm searchForm, Model model) {
		User currentUser = (User) session.getAttribute(SessionName.CURRENT_USER);

		Integer userId = (currentUser == null) ? null : currentUser.getUserId();
		String keyword = searchForm.getKeyword();
		String searchType = searchForm.getSearchType();

		List<? extends Article> articles = articleService.find(userId, keyword, searchType);

		if (articles.isEmpty()) {
			model.addAttribute("msg", Message.SEARCH_NO_RESULT);
		}
		model.addAttribute("articles", articles);

		return ScreenName.TOP;
	}
}
