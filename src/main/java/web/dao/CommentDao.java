package web.dao;

import java.util.List;

import web.entity.Comment;
import web.entity.CommentWithUserInfo;

public interface CommentDao {
	public int create(Comment comment);

	public int update(Comment comment);

	public int delete(Integer commentId);

	public List<CommentWithUserInfo> findByArticleId(Integer articleId);

	public Comment findById(Integer commentId);
}
