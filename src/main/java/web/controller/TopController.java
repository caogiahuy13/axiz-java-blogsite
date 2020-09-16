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
	@Autowired
	ArticleService articleService;

	@Autowired
	HttpSession session;

	@GetMapping({ TOP, INDEX })
	public String getTop(@ModelAttribute SearchForm searchForm) {
		return ScreenName.TOP;
	}

	@PostMapping(TOP)
	public String search(@ModelAttribute SearchForm searchForm, Model model) {
		Member currentMember = (Member) session.getAttribute(SessionUtil.CURRENT_MEMBER);

		Integer memberId = (currentMember == null) ? null : currentMember.getMemberId();
		String keyword = searchForm.getKeyword();
		String searchType = searchForm.getSearchType();

		List<? extends Article> articles = articleService.find(memberId, keyword, searchType);

		if (articles.isEmpty()) {
			model.addAttribute("msg", Message.SEARCH_NO_RESULT);
		}
		model.addAttribute("articles", articles);

		return ScreenName.TOP;
	}
}
