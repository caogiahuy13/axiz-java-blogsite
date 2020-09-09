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
import org.springframework.web.bind.annotation.RequestParam;

import web.entity.Article;
import web.entity.User;
import web.form.CreateArticleForm;
import web.form.SearchForm;
import web.service.ArticleService;
import web.util.ScreenName;
import web.util.SessionName;

@Controller
public class ArticleController {
	private static final String SEARCH = "search";
	private static final String CREATE_ARTICLE = "createArticle";
	private static final String ARTICLE = "article";

	@Autowired
	ArticleService articleService;

	@Autowired
	HttpSession session;

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

	@GetMapping(CREATE_ARTICLE)
	public String getCreateArticle(@ModelAttribute CreateArticleForm createArticleForm) {
		return ScreenName.CREATE_ARTICLE;
	}

	@PostMapping(CREATE_ARTICLE)
	public String postCreateArticle(@Validated @ModelAttribute CreateArticleForm createArticleForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return ScreenName.CREATE_ARTICLE;
		}

		User currentUser = (User) session.getAttribute(SessionName.CURRENT_USER);

		Article article = new Article();
		article.setArticleTypeId(1);
		article.setTitle(createArticleForm.getTitle());
		article.setContent(createArticleForm.getContent());
		article.setUserId(currentUser.getUserId());

		if (articleService.create(article) <= 0) {
			return ScreenName.CREATE_ARTICLE;
		}

		model.addAttribute("article", article);

		return ScreenName.ARTICLE;
	}

	@GetMapping(ARTICLE)
	public String getArticle(@RequestParam String id, Model model) {

		Article article = articleService.findById(Integer.parseInt(id));

		if (article == null) {
			return "redirect:/" + ScreenName.SEARCH;
		}

		model.addAttribute("article", article);

		return ScreenName.ARTICLE;
	}

}
