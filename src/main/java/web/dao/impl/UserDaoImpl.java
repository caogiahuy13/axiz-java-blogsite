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
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public User authenticate(String loginId, String password) {
		String sql = "SELECT * FROM users WHERE login_id = :loginId AND password = :password";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("loginId", loginId);
		paramMap.addValue("password", password);
		List<User> users = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<User>(User.class));
		return users.isEmpty() ? null : users.get(0);
	}
}
