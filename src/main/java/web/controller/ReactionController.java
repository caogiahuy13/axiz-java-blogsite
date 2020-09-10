package web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.entity.Reaction;
import web.entity.User;
import web.service.ReactionService;
import web.service.UserService;
import web.util.ScreenName;
import web.util.SessionName;

@Controller
public class ReactionController {
	private static final String REACTION = "reaction";
	private static final String ARTICLE_REACTIONS = "articleReactions";

	@Autowired
	ReactionService reactionService;

	@Autowired
	UserService userService;

	@Autowired
	HttpSession session;

	@PostMapping(REACTION)
	public String postReaction(@RequestParam String reactionIconIdStr, @RequestParam String articleIdStr,
			HttpServletRequest request) {
		User currentUser = (User) session.getAttribute(SessionName.CURRENT_USER);

		int reactionIconId = Integer.parseInt(reactionIconIdStr);
		int articleId = Integer.parseInt(articleIdStr);
		int userId = currentUser.getUserId();

		Reaction reaction = reactionService.findByUserIdAndArticleId(userId, articleId);

		if (reaction != null) {
			reactionService.delete(articleId, userId);
		} else {
			Reaction newReaction = new Reaction();
			newReaction.setArticleId(articleId);
			newReaction.setReactionIconId(reactionIconId);
			newReaction.setUserId(userId);

			reactionService.insert(newReaction);
		}

		return "redirect:" + request.getHeader("Referer");
	}

	@GetMapping(ARTICLE_REACTIONS)
	public String getArticleReaction(@RequestParam String id, Model model) {

		List<User> users = userService.findUsersReactAnArticle(Integer.parseInt(id));

		model.addAttribute("users", users);

		return ScreenName.ARTICLE_REACTIONS;
	}
}
