package web.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import web.dao.ReactionDao;
import web.entity.Reaction;

@Repository
public class ReactionDaoImpl implements ReactionDao {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public int insert(Reaction reaction) {
		String sql = "INSERT INTO reactions (article_id, user_id, reaction_icon_id) VALUES (:articleId, :userId, :reactionIconId)";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", reaction.getArticleId());
		paramMap.addValue("userId", reaction.getUserId());
		paramMap.addValue("reactionIconId", reaction.getReactionIconId());

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public int delete(Integer articleId, Integer userId) {
		String sql = "DELETE FROM reactions WHERE article_id = :articleId AND user_id = :userId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", articleId);
		paramMap.addValue("userId", userId);

		return jdbcTemplate.update(sql, paramMap);
	}
}
