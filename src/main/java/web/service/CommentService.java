package web.service;

import java.util.List;

import web.entity.Comment;
import web.entity.CommentWithUserInfo;

public interface CommentService {
	public int create(Comment comment);

	public int update(Comment comment);

	public int delete(Integer commentId);

	public List<CommentWithUserInfo> findByArticleId(Integer articleId);

}
