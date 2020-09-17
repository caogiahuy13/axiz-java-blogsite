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
import web.entity.Member;
import web.form.SearchForm;
import web.service.ArticleService;
import web.util.Message;
import web.util.ScreenName;
import web.util.SessionUtil;

@Controller
public class TopController {
	private static final String TOP = "top";
	private static final String INDEX = "/";
	private static final String PARAM_KEYWORD_SEARCH = "keywordSearch";
	private static final String PARAM_ALL_SEARCH = "allSearch";

	@Autowired
	ArticleService articleService;

	@Autowired
	HttpSession session;

	@GetMapping({ TOP, INDEX })
	public String getTop(@ModelAttribute SearchForm searchForm) {
		return ScreenName.TOP;
	}

	@PostMapping(value = TOP, params = PARAM_KEYWORD_SEARCH)
	public String keywordSearch(@ModelAttribute SearchForm searchForm, Model model) {
		Member currentMember = (Member) session.getAttribute(SessionUtil.CURRENT_MEMBER);

		String keyword = searchForm.getKeyword();
		String searchType = searchForm.getSearchType();
		Integer memberId = null;
		String sortType = searchForm.getSortType();

		if (currentMember != null && searchForm.getSearchType() != null && !searchForm.getSearchType().isBlank()) {
			memberId = currentMember.getMemberId();
		}

		List<? extends Article> articles = articleService.find(memberId, keyword, searchType, sortType);

		if (articles.isEmpty()) {
			model.addAttribute("msg", Message.SEARCH_NO_RESULT);
		}

		model.addAttribute("articles", articles);

		return ScreenName.TOP;
	}

	@PostMapping(value = TOP, params = PARAM_ALL_SEARCH)
	public String allSearch(@ModelAttribute SearchForm searchForm, Model model) {
		String sortType = searchForm.getSortType();

		List<? extends Article> articles = articleService.find(null, "", null, sortType);

		if (articles.isEmpty()) {
			model.addAttribute("msg", Message.SEARCH_NO_RESULT);
		}

		model.addAttribute("articles", articles);

		return ScreenName.TOP;
	}
}
