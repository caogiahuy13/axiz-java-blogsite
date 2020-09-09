package web.dao;

import web.entity.Comment;

public interface CommentDao {
	public int create(Comment comment);

	public int update(Comment comment);

	public int delete(Integer commentId);
}
