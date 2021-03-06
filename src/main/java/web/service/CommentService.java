package web.service;

import java.util.List;

import web.entity.Comment;
import web.entity.CommentWithExtraInfo;

public interface CommentService {
	public int create(Comment comment);

	public int update(Comment comment);

	public int delete(Integer commentId);

	public List<CommentWithExtraInfo> findByArticleId(Integer articleId);

	public Comment findById(Integer commentId);
}
