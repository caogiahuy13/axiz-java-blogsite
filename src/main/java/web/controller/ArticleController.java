package web.controller;

import java.sql.Timestamp;
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
import web.entity.CommentWithExtraInfo;
import web.entity.Member;
import web.entity.Reaction;
import web.entity.View;
import web.form.CreateArticleForm;
import web.form.EditArticleForm;
import web.service.ArticleService;
import web.service.CommentService;
import web.service.MemberService;
import web.service.ReactionService;
import web.service.ViewService;
import web.util.Milestone;
import web.util.RankName;
import web.util.ScreenName;
import web.util.SessionUtil;

@Controller
public class ArticleController {
	private static final String CREATE_ARTICLE = "createArticle";
	private static final String ARTICLE = "article";
	private static final String EDIT_ARTICLE = "editArticle";
	private static final String DELETE_ARTICLE = "deleteArticle";
	private static final String CREATE_ARTICLE_CONFIRM = "createArticleConfirm";

	@Autowired
	ArticleService articleService;

	@Autowired
	ReactionService reactionService;

	@Autowired
	CommentService commentService;

	@Autowired
	MemberService memberService;

	@Autowired
	ViewService viewService;

	@Autowired
	HttpSession session;

	@GetMapping(CREATE_ARTICLE)
	public String getCreateArticle(@ModelAttribute CreateArticleForm createArticleForm) {
		return ScreenName.POST_ARTICLE;
	}

	@PostMapping(CREATE_ARTICLE)
	public String postCreateArticle(Model model) {
		Member currentMember = (Member) session.getAttribute(SessionUtil.CURRENT_MEMBER);
		Article createArticle = (Article) session.getAttribute(SessionUtil.CREATE_ARTICLE);

		if (articleService.create(createArticle) <= 0) {
			return ScreenName.POST_ARTICLE;
		}

		Article article = articleService.findLatestByMemberId(currentMember.getMemberId());

		return "redirect:/" + ScreenName.ARTICLE + "?id=" + article.getArticleId();
	}

	@PostMapping(CREATE_ARTICLE_CONFIRM)
	public String postCreateArticleConfirm(@Validated @ModelAttribute CreateArticleForm createArticleForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return ScreenName.POST_ARTICLE;
		}

		Member currentMember = (Member) session.getAttribute(SessionUtil.CURRENT_MEMBER);
		int writerReactionCount = reactionService.countByMemberId(currentMember.getMemberId());
		String writerRank = RankName.getMemberRank(writerReactionCount);

		Article article = new Article();
		article.setTitle(createArticleForm.getTitle());
		article.setContent(createArticleForm.getContent());
		article.setMemberId(currentMember.getMemberId());
		article.setCreatedAt(new Timestamp(System.currentTimeMillis()));

		model.addAttribute("article", article);
		model.addAttribute("writer", currentMember);
		model.addAttribute("writerRank", writerRank);

		session.setAttribute(SessionUtil.CREATE_ARTICLE, article);

		return ScreenName.POST_ARTICLE_CONFIRM;
	}

	@GetMapping(ARTICLE)
	public String getArticle(@RequestParam String id, Model model) {
		Member currentMember = (Member) session.getAttribute(SessionUtil.CURRENT_MEMBER);
		int articleId = Integer.parseInt(id);

		Article article = articleService.findById(articleId);
		if (article == null) {
			return "redirect:/" + ScreenName.TOP;
		}

		Member writer = memberService.findByMemberId(article.getMemberId());
		int writerReactionCount = reactionService.countByMemberId(writer.getMemberId());
		String writerRank = RankName.getMemberRank(writerReactionCount);
		int reactionCount = reactionService.countByArticleId(articleId);
		List<CommentWithExtraInfo> comments = commentService.findByArticleId(articleId);
		List<Member> reactedMembers = memberService.findMembersReactAnArticle(articleId);
		HashMap<Integer, Integer> reactions = reactionService.countMultipleByArticleId(articleId);

		int articleMemberReactionCount = reactionService.countByMemberId(article.getMemberId());
		String articleMemberMySpace = "";
		if (articleMemberReactionCount >= Milestone.GOLD_RANK) {
			articleMemberMySpace = memberService.findByMemberId(article.getMemberId()).getMySpace();
		}

		if (currentMember != null) {
			Reaction reaction = reactionService.findByMemberIdAndArticleId(currentMember.getMemberId(), articleId);
			if (reaction != null) {
				model.addAttribute("isReacted", reaction.getStampId());
			}
		}

		View view = new View();
		view.setArticleId(articleId);
		view.setMemberId(currentMember == null ? null : currentMember.getMemberId());
		viewService.insert(view);

		model.addAttribute("article", article);
		model.addAttribute("writer", writer);
		model.addAttribute("writerRank", writerRank);
		model.addAttribute("reactionCount", reactionCount);
		model.addAttribute("comments", comments);
		model.addAttribute("reactedMembers", reactedMembers);
		model.addAttribute("reactions", reactions);
		model.addAttribute("articleMemberReactionCount", articleMemberReactionCount);
		model.addAttribute("articleMemberMySpace", articleMemberMySpace);

		return ScreenName.ARTICLE;
	}

	@GetMapping(EDIT_ARTICLE)
	public String getEditArticle(@RequestParam String id, @ModelAttribute EditArticleForm editArticleForm) {
		Article article = articleService.findById(Integer.parseInt(id));
		Member currentMember = (Member) session.getAttribute(SessionUtil.CURRENT_MEMBER);

		if (article == null || article.getMemberId() != currentMember.getMemberId()) {
			return ScreenName.TOP;
		}

		editArticleForm.setArticleId(article.getArticleId());
		editArticleForm.setTitle(article.getTitle());
		editArticleForm.setContent(article.getContent());

		return ScreenName.ARTICLE_UPDATE;
	}

	@PostMapping(EDIT_ARTICLE)
	public String postEditArticle(@Validated @ModelAttribute EditArticleForm editArticleForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return ScreenName.ARTICLE_UPDATE;
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

		Member writer = memberService.findByMemberId(article.getMemberId());
		int writerReactionCount = reactionService.countByMemberId(writer.getMemberId());
		String writerRank = RankName.getMemberRank(writerReactionCount);

		int articleReactionCount = reactionService.countByArticleId(article.getArticleId());
		boolean isDownRank = false;
		int writerReactionCountAfterDelete = writerReactionCount - articleReactionCount;
		String downRank = "";
		int currentMilestone = 0;

		if (writerRank.equals(RankName.GOLD)) {
			downRank = "シルバー";
			currentMilestone = Milestone.GOLD_RANK;
		} else if (writerRank.equals(RankName.SILVER)) {
			downRank = "ブロンズ";
			currentMilestone = Milestone.SILVER_RANK;
		} else if (writerRank.equals(RankName.SILVER)) {
			downRank = "ノーマル";
			currentMilestone = Milestone.BRONZE_RANK;
		}

		if (currentMilestone != 0 && writerReactionCount >= currentMilestone
				&& writerReactionCountAfterDelete < currentMilestone) {
			isDownRank = true;
		}

		model.addAttribute("article", article);
		model.addAttribute("writer", writer);
		model.addAttribute("writerRank", writerRank);

		if (isDownRank) {
			model.addAttribute("msg", String.format("※この記事を削除すると累計のお気に入り数が%dになるため、 %s会員になりますがよろしいですか？",
					writerReactionCountAfterDelete, downRank));
		}

		return ScreenName.ARTICLE_DELETE;
	}

	@PostMapping(DELETE_ARTICLE)
	public String postDeleteArticle(@RequestParam Integer articleId, Model model) {
		Article article = articleService.findById(articleId);

		if (article == null) {
			return ScreenName.TOP;
		}

		articleService.delete(articleId);

		return ScreenName.ARTICLE_DELETE_RESULT;
	}

}
