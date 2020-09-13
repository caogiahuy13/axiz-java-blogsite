package web.dao.impl;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
		String sql = "INSERT INTO reactions (article_id, user_id, stamp_id) VALUES (:articleId, :userId, :stampId)";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", reaction.getArticleId());
		paramMap.addValue("userId", reaction.getUserId());
		paramMap.addValue("stampId", reaction.getStampId());

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

	@Override
	public Reaction findByUserIdAndArticleId(Integer userId, Integer articleId) {
		String sql = "SELECT * FROM reactions WHERE user_id = :userId AND article_id = :articleId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", articleId);
		paramMap.addValue("userId", userId);

		List<Reaction> reactions = jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<Reaction>(Reaction.class));

		return reactions.isEmpty() ? null : reactions.get(0);
	}

	@Override
	public int countByArticleId(Integer articleId) {
		String sql = "SELECT COUNT(*) AS count FROM reactions WHERE article_id = :articleId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", articleId);

		return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
	}

	@Override
	public int countByUserId(Integer userId) {
		String sql = "SELECT COUNT(*) FROM reactions r JOIN articles a ON r.article_id = a.article_id WHERE a.user_id = :userId;";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("userId", userId);

		return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
	}

	@Override
	public HashMap<Integer, Integer> countMultipleByArticleId(Integer articleId) {
		String sql = "SELECT stamp_id, COUNT(*) count FROM reactions WHERE article_id = :articleId GROUP BY stamp_id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", articleId);
		//		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, paramMap);
		//
		//		return (List<Map<String, Integer>>) mapList.stream()
		//				.collect(Collectors.toMap(k -> (Integer) k.get("stamp_id"), k -> (Integer) k.get("count")));

		return jdbcTemplate.query(sql, paramMap, (ResultSet rs) -> {
			HashMap<Integer, Integer> results = new HashMap<>();
			while (rs.next()) {
				results.put(rs.getInt("stamp_id"), rs.getInt("count"));
			}
			return results;
		});
	}
}
