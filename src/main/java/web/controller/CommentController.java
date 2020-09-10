package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.entity.Comment;
import web.entity.User;
import web.service.CommentService;
import web.util.SessionName;

@Controller
public class CommentController {
	private static final String COMMENT = "comment";

	@Autowired
	CommentService commentService;

	@Autowired
	HttpSession session;

	@PostMapping(COMMENT)
	public String postComment(@RequestParam String articleIdStr, @RequestParam String commentStr,
			HttpServletRequest request) {
		int articleId = Integer.parseInt(articleIdStr);

		User currentUser = (User) session.getAttribute(SessionName.CURRENT_USER);

		Comment comment = new Comment();
		comment.setArticleId(articleId);
		comment.setContent(commentStr);
		comment.setUserId(currentUser.getUserId());

		commentService.create(comment);

		return "redirect:" + request.getHeader("Referer");
	}
}
