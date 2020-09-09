package web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import web.entity.Article;
import web.form.SearchForm;
import web.service.ArticleService;
import web.util.ScreenName;

@Controller
public class ArticleController {
	private static final String SEARCH = "search";

	@Autowired
	ArticleService articleService;

	@GetMapping(SEARCH)
	public String getSearch(@ModelAttribute SearchForm searchForm) {
		return ScreenName.SEARCH;
	}

	@PostMapping(SEARCH)
	public String postSearch(@Validated @ModelAttribute SearchForm searchForm, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return ScreenName.SEARCH;
		}

		List<Article> articles = articleService.findArticleByContent(searchForm.getKeyword());

		model.addAttribute("articles", articles);

		return ScreenName.SEARCH;
	}
}
