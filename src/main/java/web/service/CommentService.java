package web.service;

import web.entity.Comment;

public interface CommentService {
	public int create(Comment comment);

	public int update(Comment comment);

	public int delete(Integer commentId);
}
