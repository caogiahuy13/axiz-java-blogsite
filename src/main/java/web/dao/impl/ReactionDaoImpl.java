package web.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import web.dao.ReactionDao;

@Repository
public class ReactionDaoImpl implements ReactionDao {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
}
