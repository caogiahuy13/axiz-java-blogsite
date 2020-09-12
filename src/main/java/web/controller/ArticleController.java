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
import web.entity.CommentWithUserInfo;
import web.entity.User;
import web.form.CreateArticleForm;
import web.form.EditArticleForm;
import web.service.ArticleService;
import web.service.CommentService;
import web.service.ReactionService;
import web.service.UserService;
import web.util.ScreenName;
import web.util.SessionName;

@Controller
public class ArticleController {
	private static final String CREATE_ARTICLE = "createArticle";
	private static final String ARTICLE = "article";
	private static final String EDIT_ARTICLE = "editArticle";

	@Autowired
	ArticleService articleService;

	@Autowired
	ReactionService reactionService;

	@Autowired
	CommentService commentService;

	@Autowired
	UserService userService;

	@Autowired
	HttpSession session;

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
		int reactions = reactionService.countByArticleId(Integer.parseInt(id));
		List<CommentWithUserInfo> comments = commentService.findByArticleId(Integer.parseInt(id));
		List<User> reactedUsers = userService.findUsersReactAnArticle(Integer.parseInt(id));

		if (article == null) {
			return "redirect:/" + ScreenName.SEARCH;
		}

		model.addAttribute("article", article);
		model.addAttribute("reactions", reactions);
		model.addAttribute("comments", comments);
		model.addAttribute("reactedUsers", reactedUsers);

		return ScreenName.ARTICLE;
	}

	@GetMapping(EDIT_ARTICLE)
	public String getEditArticle(@RequestParam String id, @ModelAttribute EditArticleForm editArticleForm) {
		Article article = articleService.findById(Integer.parseInt(id));
		User currentUser = (User) session.getAttribute(SessionName.CURRENT_USER);

		if (article == null || article.getUserId() != currentUser.getUserId()) {
			return ScreenName.SEARCH;
		}

		editArticleForm.setArticleId(article.getArticleId());
		editArticleForm.setTitle(article.getTitle());
		editArticleForm.setContent(article.getContent());

		return ScreenName.EDIT_ARTICLE;
	}

	@PostMapping(EDIT_ARTICLE)
	public String postEditArticle(@Validated @ModelAttribute EditArticleForm editArticleForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return ScreenName.ARTICLE;
		}

		Article article = articleService.findById(editArticleForm.getArticleId());

		if (article == null) {
			return ScreenName.SEARCH;
		}

		article.setContent(editArticleForm.getContent());
		article.setTitle(editArticleForm.getTitle());

		if (articleService.update(article) <= 0) {
			return ScreenName.ARTICLE;
		}

		return "redirect:/" + ScreenName.ARTICLE + "?id=" + article.getArticleId();
	}

}
