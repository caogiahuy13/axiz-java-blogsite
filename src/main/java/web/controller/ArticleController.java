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
import web.entity.CommentWithUserInfo;
import web.entity.Reaction;
import web.entity.User;
import web.entity.View;
import web.form.CreateArticleForm;
import web.form.EditArticleForm;
import web.service.ArticleService;
import web.service.CommentService;
import web.service.ReactionService;
import web.service.UserService;
import web.service.ViewService;
import web.util.ScreenName;
import web.util.SessionUtil;

@Controller
public class ArticleController {
	private static final String CREATE_ARTICLE = "createArticle";
	private static final String ARTICLE = "article";
	private static final String EDIT_ARTICLE = "editArticle";
	private static final String DELETE_ARTICLE = "deleteArticle";

	@Autowired
	ArticleService articleService;

	@Autowired
	ReactionService reactionService;

	@Autowired
	CommentService commentService;

	@Autowired
	UserService userService;

	@Autowired
	ViewService viewService;

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

		User currentUser = (User) session.getAttribute(SessionUtil.CURRENT_USER);

		Article article = new Article();
		article.setTitle(createArticleForm.getTitle());
		article.setContent(createArticleForm.getContent());
		article.setUserId(currentUser.getUserId());

		if (articleService.create(article) <= 0) {
			return ScreenName.CREATE_ARTICLE;
		}

		article = articleService.findLatestByUserId(currentUser.getUserId());

		return "redirect:/" + ScreenName.ARTICLE + "?id=" + article.getArticleId();
	}

	@GetMapping(ARTICLE)
	public String getArticle(@RequestParam String id, Model model) {
		User currentUser = (User) session.getAttribute(SessionUtil.CURRENT_USER);
		int articleId = Integer.parseInt(id);

		Article article = articleService.findById(articleId);
		if (article == null) {
			return "redirect:/" + ScreenName.TOP;
		}

		int reactionCount = reactionService.countByArticleId(articleId);
		List<CommentWithUserInfo> comments = commentService.findByArticleId(articleId);
		List<User> reactedUsers = userService.findUsersReactAnArticle(articleId);
		HashMap<Integer, Integer> reactions = reactionService.countMultipleByArticleId(articleId);

		int articleUserReactionCount = reactionService.countByUserId(article.getUserId());
		String articleUserMySpace = "";
		if (articleUserReactionCount >= 15) {
			articleUserMySpace = userService.findByUserId(article.getUserId()).getMySpace();
		}

		if (currentUser != null) {
			Reaction reaction = reactionService.findByUserIdAndArticleId(currentUser.getUserId(), articleId);
			if (reaction != null) {
				model.addAttribute("isReacted", reaction.getStampId());
			}
		}

		View view = new View();
		view.setArticleId(articleId);
		view.setUserId(currentUser == null ? null : currentUser.getUserId());
		viewService.insert(view);

		model.addAttribute("article", article);
		model.addAttribute("reactionCount", reactionCount);
		model.addAttribute("comments", comments);
		model.addAttribute("reactedUsers", reactedUsers);
		model.addAttribute("reactions", reactions);
		model.addAttribute("articleUserReactionCount", articleUserReactionCount);
		model.addAttribute("articleUserMySpace", articleUserMySpace);

		return ScreenName.ARTICLE;
	}

	@GetMapping(EDIT_ARTICLE)
	public String getEditArticle(@RequestParam String id, @ModelAttribute EditArticleForm editArticleForm) {
		Article article = articleService.findById(Integer.parseInt(id));
		User currentUser = (User) session.getAttribute(SessionUtil.CURRENT_USER);

		if (article == null || article.getUserId() != currentUser.getUserId()) {
			return ScreenName.TOP;
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
			return ScreenName.EDIT_ARTICLE;
		}

		Article article = articleService.findById(editArticleForm.getArticleId());

		if (article == null) {
			return ScreenName.TOP;
		}

		article.setContent(editArticleForm.getContent());
		article.setTitle(editArticleForm.getTitle());

		if (articleService.update(article) <= 0) {
			return "redirect:/" + ScreenName.ARTICLE;
		}

		return "redirect:/" + ScreenName.ARTICLE + "?id=" + article.getArticleId();
	}

	@GetMapping(DELETE_ARTICLE)
	public String getDeleteArticle(@RequestParam String id, Model model) {

		Article article = articleService.findById(Integer.parseInt(id));

		if (article == null) {
			return ScreenName.TOP;
		}

		model.addAttribute("article", article);

		return ScreenName.DELETE_ARTICLE;
	}

	@PostMapping(DELETE_ARTICLE)
	public String postDeleteArticle(@RequestParam String articleId, Model model) {

		Article article = articleService.findById(Integer.parseInt(articleId));

		if (article == null) {
			return ScreenName.TOP;
		}

		articleService.delete(Integer.parseInt(articleId));

		return "redirect:/" + ScreenName.MY_PAGE;
	}

}
