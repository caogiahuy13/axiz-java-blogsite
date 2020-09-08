package web.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import web.dao.ReactionIconDao;

@Repository
public class ReactionIconDaoImpl implements ReactionIconDao {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
}
