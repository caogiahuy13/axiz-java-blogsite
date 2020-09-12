package web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.entity.Article;
import web.entity.User;
import web.service.ArticleService;
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
	public String getTop() {
		return ScreenName.TOP;
	}

	@PostMapping(TOP)
	public String search(@RequestParam String searchType, @RequestParam String keyword, Model model) {
		User currentUser = (User) session.getAttribute(SessionName.CURRENT_USER);

		List<? extends Article> articles;

		if (currentUser == null) {
			articles = articleService.find(null, keyword, searchType);
		} else {
			articles = articleService.find(currentUser.getUserId(), keyword, searchType);
		}

		model.addAttribute("articles", articles);

		return ScreenName.TOP;
	}
}
