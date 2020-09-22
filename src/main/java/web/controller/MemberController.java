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
import web.entity.Member;
import web.entity.ReactionsByAgeRangeAndGender;
import web.entity.ReactionsByArticle;
import web.form.UpdateMemberForm;
import web.service.ArticleService;
import web.service.MemberService;
import web.service.ReactionService;
import web.service.ViewService;
import web.util.Message;
import web.util.RankName;
import web.util.ScreenName;
import web.util.SessionUtil;

@Controller
public class MemberController {
	private static final String MY_PAGE = "myPage";
	private static final String MY_ARTICLES = "myArticles";
	private static final String UPDATE_MEMBER = "updateMember";
	private static final String DELETE_MEMBER = "deleteMember";
	private static final String ANALYTICS = "analytics";

	@Autowired
	MemberService memberService;

	@Autowired
	ArticleService articleService;

	@Autowired
	ReactionService reactionService;

	@Autowired
	ViewService viewService;

	@Autowired
	HttpSession session;

	@GetMapping(MY_PAGE)
	public String getMyPage(Model model, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(name = "id") Integer memberId) {
		Member member = memberService.findByMemberId(memberId);

		final Integer LIMIT = 3;

		List<? extends Article> articles = articleService.findByMemberId(member.getMemberId(),
				pageNumber, LIMIT);

		int articleMaxPage = articleService.countByMemberId(memberId) / LIMIT + 1;
		int memberRank = memberService.getRank(memberId);
		int memberTotalReactions = reactionService.countByMemberId(memberId);
		String memberRankName = RankName.getMemberRank(memberTotalReactions);

		model.addAttribute("member", member);
		model.addAttribute("memberRank", memberRank);
		model.addAttribute("memberRankName", memberRankName);
		model.addAttribute("memberTotalReactions", memberTotalReactions);
		model.addAttribute("articles", articles);
		model.addAttribute("articleMaxPage", articleMaxPage);
		model.addAttribute("articleCurPage", pageNumber);

		return ScreenName.MY_PAGE;
	}

	@GetMapping(MY_ARTICLES)
	public String getMyArticles(Model model) {
		Member currentMember = (Member) session.getAttribute(SessionUtil.CURRENT_MEMBER);

		List<? extends Article> articles = articleService.findByMemberId(currentMember.getMemberId());

		if (articles.isEmpty()) {
			model.addAttribute("msg", Message.MY_ARTICLES_NO_RESULT);
		}

		model.addAttribute("articles", articles);

		return ScreenName.MY_ARTICLES;
	}

	@GetMapping(UPDATE_MEMBER)
	public String getUpdateMember(@ModelAttribute UpdateMemberForm updateMemberForm) {
		Member currentMember = (Member) session.getAttribute(SessionUtil.CURRENT_MEMBER);

		updateMemberForm.setMemberId(currentMember.getMemberId());
		updateMemberForm.setLoginId(currentMember.getLoginId());
		updateMemberForm.setNickname(currentMember.getNickname());
		updateMemberForm.setPassword(currentMember.getPassword());
		updateMemberForm.setIntroduction(currentMember.getIntroduction());
		updateMemberForm.setMySpace(currentMember.getMySpace());

		return ScreenName.UPDATE_MEMBER;
	}

	@PostMapping(UPDATE_MEMBER)
	public String postUpdateMember(@Validated @ModelAttribute UpdateMemberForm updateMemberForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return ScreenName.UPDATE_MEMBER;
		}

		if (!updateMemberForm.getPassword().equals(updateMemberForm.getRePassword())) {
			model.addAttribute("msg", Message.PASSWORD_IS_NOT_MATCH);
			return ScreenName.UPDATE_MEMBER;
		}

		Member existedMember = memberService.findByLoginId(updateMemberForm.getLoginId());
		if (existedMember != null && existedMember.getMemberId() != updateMemberForm.getMemberId()) {
			model.addAttribute("msg", Message.LOGIN_ID_IS_EXISTED);
			return ScreenName.UPDATE_MEMBER;
		}

		Member member = memberService.findByMemberId(updateMemberForm.getMemberId());
		member.setLoginId(updateMemberForm.getLoginId());
		member.setNickname(updateMemberForm.getNickname());
		member.setPassword(updateMemberForm.getPassword());
		member.setIntroduction(updateMemberForm.getIntroduction());
		member.setMySpace(updateMemberForm.getMySpace());

		memberService.update(member);

		Member updatedMember = memberService.findByMemberId(member.getMemberId());

		return "redirect:/" + ScreenName.MY_PAGE + "?id=" + updatedMember.getMemberId();
	}

	@GetMapping(DELETE_MEMBER)
	public String getDeleteMember() {
		return ScreenName.DELETE_MEMBER;
	}

	@PostMapping(DELETE_MEMBER)
	public String postDeleteMember(Model model) {
		Member currentMember = (Member) session.getAttribute(SessionUtil.CURRENT_MEMBER);

		if (memberService.deleteByLoginId(currentMember.getLoginId()) > 0) {
			SessionUtil.removeAllSession(session);
		}

		model.addAttribute("msg", Message.MEMBER_DELETE_SUCCESS);
		return ScreenName.DELETE_MEMBER_RESULT;
	}

	@GetMapping(ANALYTICS)
	public String getAnalytics(Model model) {
		Member currentMember = (Member) session.getAttribute(SessionUtil.CURRENT_MEMBER);
		if (currentMember == null) {
			return "redirect:/" + ScreenName.TOP;
		}

		Integer memberId = currentMember.getMemberId();

		HashMap<String, Integer> accessAnalytics = viewService
				.countByAccessByMemberIdOfArticle(currentMember.getMemberId());
		List<ReactionsByAgeRangeAndGender> ageRangeAndGenderAnalytics = reactionService
				.countByGenderAndAgeRangeAndMemberIdOfArticle(memberId);
		List<ReactionsByArticle> reactionAnalytics = reactionService
				.countMultipleByMemberIdOfArticle(memberId);

		model.addAttribute("loginAccess", accessAnalytics.get("login"));
		model.addAttribute("anonymousAccess", accessAnalytics.get("anonymous"));
		model.addAttribute("ageRangeAndGenderAnalytics", ageRangeAndGenderAnalytics);
		model.addAttribute("reactionAnalytics", reactionAnalytics);

		return ScreenName.ANALYTICS;
	}

}
