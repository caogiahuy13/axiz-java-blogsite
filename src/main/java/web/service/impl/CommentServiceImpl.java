package web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.dao.CommentDao;
import web.entity.Comment;
import web.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao commentDao;

	@Override
	public int create(Comment comment) {
		return commentDao.create(comment);
	}

	@Override
	public int update(Comment comment) {
		return commentDao.update(comment);
	}

	@Override
	public int delete(Integer commentId) {
		return commentDao.delete(commentId);
	}
}
