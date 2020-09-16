package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.entity.Reaction;
import web.entity.Member;
import web.service.ReactionService;
import web.util.ScreenName;
import web.util.SessionUtil;

@Controller
public class ReactionController {
	private static final String REACTION = "reaction";

	@Autowired
	ReactionService reactionService;

	@Autowired
	HttpSession session;

	@PostMapping(REACTION)
	public String postReaction(@RequestParam String stampIdStr, @RequestParam String articleIdStr,
			HttpServletRequest request) {
		Member currentMember = (Member) session.getAttribute(SessionUtil.CURRENT_MEMBER);

		if (currentMember == null) {
			return "redirect:/" + ScreenName.LOGIN;
		}

		int stampId = Integer.parseInt(stampIdStr);
		int articleId = Integer.parseInt(articleIdStr);
		int memberId = currentMember.getMemberId();

		Reaction reaction = reactionService.findByMemberIdAndArticleId(memberId, articleId);

		if (reaction != null) {
			reactionService.delete(articleId, memberId);
			if (reaction.getStampId() != stampId) {
				Reaction newReaction = new Reaction();
				newReaction.setArticleId(articleId);
				newReaction.setStampId(stampId);
				newReaction.setMemberId(memberId);

				reactionService.insert(newReaction);
			}
		} else {
			Reaction newReaction = new Reaction();
			newReaction.setArticleId(articleId);
			newReaction.setStampId(stampId);
			newReaction.setMemberId(memberId);

			reactionService.insert(newReaction);
		}

		return "redirect:" + request.getHeader("Referer");
	}
}
