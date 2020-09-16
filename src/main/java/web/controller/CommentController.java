package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.entity.Comment;
import web.entity.Member;
import web.service.CommentService;
import web.util.ScreenName;
import web.util.SessionUtil;

@Controller
public class CommentController {
	private static final String COMMENT = "comment";
	private static final String UPDATE_COMMENT = "updateComment";
	private static final String DELETE_COMMENT = "deleteComment";

	@Autowired
	CommentService commentService;

	@Autowired
	HttpSession session;

	@PostMapping(COMMENT)
	public String postComment(@RequestParam String articleIdStr, @RequestParam String contentStr,
			HttpServletRequest request) {
		Member currentMember = (Member) session.getAttribute(SessionUtil.CURRENT_MEMBER);

		if (currentMember == null) {
			return "redirect:/" + ScreenName.LOGIN;
		}
		int articleId = Integer.parseInt(articleIdStr);

		Comment comment = new Comment();
		comment.setArticleId(articleId);
		comment.setContent(contentStr);
		comment.setMemberId(currentMember.getMemberId());

		commentService.create(comment);

		return "redirect:" + request.getHeader("Referer");
	}

	@PostMapping(UPDATE_COMMENT)
	public String postUpdateComment(@RequestParam String commentId, @RequestParam String content,
			HttpServletRequest request) {
		Comment comment = commentService.findById(Integer.parseInt(commentId));

		if (comment != null) {
			comment.setContent(content);
			commentService.update(comment);
		}

		return "redirect:" + request.getHeader("Referer");
	}

	@PostMapping(DELETE_COMMENT)
	public String postDeleteComment(@RequestParam String commentId, HttpServletRequest request) {
		Comment comment = commentService.findById(Integer.parseInt(commentId));

		if (comment != null) {
			commentService.delete(Integer.parseInt(commentId));
		}

		return "redirect:" + request.getHeader("Referer");
	}

}
