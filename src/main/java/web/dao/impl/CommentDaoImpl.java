package web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import web.dao.CommentDao;
import web.entity.Comment;
import web.entity.CommentWithExtraInfo;

@Repository
public class CommentDaoImpl implements CommentDao {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public int create(Comment comment) {
		String sql = "INSERT INTO comments (content, article_id, member_id) VALUES (:content, :articleId, :memberId)";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("content", comment.getContent());
		paramMap.addValue("articleId", comment.getArticleId());
		paramMap.addValue("memberId", comment.getMemberId());

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

	@Override
	public List<CommentWithExtraInfo> findByArticleId(Integer articleId) {
		String sql = "SELECT comment_id, article_id, c.member_id, content, c.created_at, c.updated_at, m.name, m.nickname FROM comments c JOIN members m ON c.member_id = m.member_id WHERE article_id = :articleId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", articleId);

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<CommentWithExtraInfo>(CommentWithExtraInfo.class));
	}

	@Override
	public Comment findById(Integer commentId) {
		String sql = "SELECT * FROM comments WHERE comment_id = :commentId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("commentId", commentId);

		List<Comment> comments = jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<Comment>(Comment.class));

		return comments.isEmpty() ? null : comments.get(0);
	}
}
