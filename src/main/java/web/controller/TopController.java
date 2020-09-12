package web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.entity.Article;
import web.service.ArticleService;
import web.util.ScreenName;

@Controller
public class TopController {
	private static final String TOP = "top";

	@Autowired
	ArticleService articleService;

	@GetMapping(TOP)
	public String getTop() {
		return ScreenName.TOP;
	}

	@PostMapping(TOP)
	public String search(@RequestParam String searchType, @RequestParam String keyword, Model model) {

		List<Article> articles = articleService.find(keyword, searchType);
		model.addAttribute("articles", articles);

		return ScreenName.TOP;
	}
}
