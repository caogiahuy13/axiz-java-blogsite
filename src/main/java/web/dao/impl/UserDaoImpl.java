package web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import web.dao.UserDao;
import web.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	private static final String SELECT_BASE = "SELECT user_id, login_id, user_name, password, created_at, updated_at FROM users ";

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public User authenticate(String loginId, String password) {
		String sql = SELECT_BASE + "WHERE login_id = :loginId AND password = :password";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("loginId", loginId);
		paramMap.addValue("password", password);

		List<User> users = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<User>(User.class));

		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public int register(User user) {
		String sql = "INSERT INTO users (login_id, user_name, password) VALUES (:loginId, :userName, :password)";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("loginId", user.getLoginId());
		paramMap.addValue("userName", user.getUserName());
		paramMap.addValue("password", user.getPassword());

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public int deleteByLoginId(String loginId) {
		String sql = "DELETE FROM users WHERE login_id = :loginId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("loginId", loginId);

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public int update(User user) {
		String sql = "UPDATE users SET user_name = :userName, login_id = :loginId, password = :password WHERE user_id = :userId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("loginId", user.getLoginId());
		paramMap.addValue("userName", user.getUserName());
		paramMap.addValue("password", user.getPassword());
		paramMap.addValue("userId", user.getUserId());

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public List<User> findUsersReactAnArticle(Integer articleId) {
		String sql = "SELECT users.user_id, login_id, user_name, password, users.created_at, users.updated_at FROM users "
				+ "JOIN reactions ON users.user_id = reactions.user_id WHERE reactions.article_id = :articleId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", articleId);

		return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<User>(User.class));
	}

}
