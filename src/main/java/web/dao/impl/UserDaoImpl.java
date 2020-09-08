package web.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import web.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
}
