package web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.entity.Member;
import web.entity.Reaction;
import web.service.MemberService;
import web.service.ReactionService;

@RestController
public class ApiController {
	private static final String API_REACTION = "/api/reaction";

	@Autowired
	ReactionService reactionService;

	@Autowired
	MemberService memberService;

	@Autowired
	HttpSession session;

	@PostMapping(value = API_REACTION, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> postReaction(@RequestParam Integer stampId, @RequestParam Integer articleId,
			@RequestParam Integer memberId) {
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

		HashMap<Integer, Integer> reactions = reactionService.countMultipleByArticleId(articleId);
		List<Member> reactedMembers = memberService.findMembersReactAnArticle(articleId);
		Reaction existedReaction = reactionService.findByMemberIdAndArticleId(memberId, articleId);
		Integer isReacted = 0;
		if (existedReaction != null) {
			isReacted = existedReaction.getStampId();
		}

		HashMap<String, Object> map = new HashMap<>();
		map.put("reactions", reactions);
		map.put("reactedMembers", reactedMembers);
		map.put("isReacted", isReacted);

		return map;
	}

}
