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
import web.entity.ReactionsByArticle;

@Repository
public class ReactionDaoImpl implements ReactionDao {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public int insert(Reaction reaction) {
		String sql = "INSERT INTO reactions (article_id, member_id, stamp_id) VALUES (:articleId, :memberId, :stampId)";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", reaction.getArticleId());
		paramMap.addValue("memberId", reaction.getMemberId());
		paramMap.addValue("stampId", reaction.getStampId());

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public int delete(Integer articleId, Integer memberId) {
		String sql = "DELETE FROM reactions WHERE article_id = :articleId AND member_id = :memberId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", articleId);
		paramMap.addValue("memberId", memberId);

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public Reaction findByMemberIdAndArticleId(Integer memberId, Integer articleId) {
		String sql = "SELECT * FROM reactions WHERE member_id = :memberId AND article_id = :articleId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", articleId);
		paramMap.addValue("memberId", memberId);

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
	public int countByMemberId(Integer memberId) {
		String sql = "SELECT COUNT(*) FROM reactions r JOIN articles a ON r.article_id = a.article_id WHERE a.member_id = :memberId;";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memberId", memberId);

		return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
	}

	@Override
	public HashMap<Integer, Integer> countMultipleByArticleId(Integer articleId) {
		String sql = "SELECT stamp_id, COUNT(*) count FROM reactions WHERE article_id = :articleId GROUP BY stamp_id";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", articleId);

		return jdbcTemplate.query(sql, paramMap, (ResultSet rs) -> {
			HashMap<Integer, Integer> results = new HashMap<>();
			while (rs.next()) {
				results.put(rs.getInt("stamp_id"), rs.getInt("count"));
			}
			return results;
		});
	}

	@Override
	public List<ReactionsByArticle> countMultipleByMemberIdOfArticle(Integer memberId) {
		String sql = " SELECT s.stamp_id, a.title, COUNT(CASE WHEN reaction_id IS NOT NULL THEN 1 ELSE NULL END)"
				+ " FROM stamps s"
				+ " JOIN (SELECT a.article_id, a.title FROM articles a JOIN members m ON m.member_id = a.member_id WHERE m.member_id = :memberId) a ON TRUE"
				+ " LEFT JOIN reactions r ON s.stamp_id = r.stamp_id AND a.article_id = r.article_id"
				+ " GROUP BY s.stamp_id, a.article_id, a.title"
				+ " ORDER BY s.stamp_id";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memberId", memberId);

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<ReactionsByArticle>(ReactionsByArticle.class));
	}

	@Override
	public Integer countByGenderAndAgeRangeAndMemberIdOfArticle(Integer memberId, Integer gender, Integer minAge,
			Integer maxAge) {
		String sql = "SELECT COUNT(*) count "
				+ "	FROM ( "
				+ "		SELECT DISTINCT a.member_id, m.gender, m.birthdate "
				+ "		FROM reactions r "
				+ " 	JOIN articles a ON r.article_id = a.article_id "
				+ " 	JOIN members m ON r.member_id = m.member_id "
				+ " ) x "
				+ " WHERE x.member_id = :memberId AND x.gender = :gender AND date_part('year',age(x.birthdate)) BETWEEN :minAge AND :maxAge ";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memberId", memberId);
		paramMap.addValue("gender", gender);
		paramMap.addValue("minAge", minAge);
		paramMap.addValue("maxAge", maxAge);

		return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
	}

}
