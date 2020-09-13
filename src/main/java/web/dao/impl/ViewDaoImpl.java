package web.dao.impl;

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
		String sql = "INSERT INTO views (article_id, user_id) VALUES (:articleId, :userId)";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", view.getArticleId());
		paramMap.addValue("userId", view.getUserId());

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
}
