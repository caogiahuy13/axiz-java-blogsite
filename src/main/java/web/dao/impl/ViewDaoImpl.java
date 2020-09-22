package web.dao.impl;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import web.dao.ViewDao;
import web.entity.View;

@Repository
public class ViewDaoImpl implements ViewDao {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public int insert(View view) {
		String sql = "INSERT INTO views (article_id, member_id) VALUES (:articleId, :memberId)";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", view.getArticleId());
		paramMap.addValue("memberId", view.getMemberId());

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public List<View> findByArticleId(Integer articleId) {
		String sql = "SELECT * FROM views WHERE article_id = :articleId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", articleId);

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<View>(View.class));
	}

	@Override
	public HashMap<String, Integer> countByAccessByMemberIdOfArticle(Integer memberId) {
		String sql = "SELECT "
				+ "	CASE"
				+ "		WHEN v.member_id IS NOT NULL THEN 'login'"
				+ "		WHEN v.member_id IS NULL THEN 'anonymous'"
				+ "	END AS access, COUNT(*) count"
				+ "	FROM views v"
				+ "	JOIN articles a ON v.article_id = a.article_id"
				+ "	JOIN members m ON a.member_id = m.member_id"
				+ "	WHERE a.member_id = 3"
				+ "	GROUP BY access";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memberId", memberId);

		return jdbcTemplate.query(sql, paramMap, (ResultSet rs) -> {
			HashMap<String, Integer> results = new HashMap<>();
			while (rs.next()) {
				results.put(rs.getString("access"), rs.getInt("count"));
			}
			return results;
		});
	}
}
