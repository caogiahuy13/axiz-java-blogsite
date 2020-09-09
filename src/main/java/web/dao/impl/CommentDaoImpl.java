package web.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import web.dao.CommentDao;
import web.entity.Comment;

@Repository
public class CommentDaoImpl implements CommentDao {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public int create(Comment comment) {
		String sql = "INSERT INTO comments (content, article_id, user_id) VALUES (:content, :articleId, :userId)";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("content", comment.getContent());
		paramMap.addValue("articleId", comment.getArticleId());
		paramMap.addValue("userId", comment.getUserId());

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public int update(Comment comment) {
		String sql = "UPDATE comments SET content = :content WHERE comment_id = :commentId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("content", comment.getContent());
		paramMap.addValue("commentId", comment.getCommentId());

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public int delete(Integer commentId) {
		String sql = "DELETE FROM comments WHERE comment_id = :commentId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("commentId", commentId);

		return jdbcTemplate.update(sql, paramMap);
	}

}
