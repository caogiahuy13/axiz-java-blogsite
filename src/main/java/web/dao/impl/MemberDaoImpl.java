package web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import web.dao.MemberDao;
import web.entity.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	private static final String SELECT_BASE = "SELECT m.* FROM members m ";

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public Member authenticate(String loginId, String password) {
		String sql = SELECT_BASE + " WHERE m.login_id = :loginId AND m.password = :password";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("loginId", loginId);
		paramMap.addValue("password", password);

		List<Member> members = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<Member>(Member.class));

		return members.isEmpty() ? null : members.get(0);
	}

	@Override
	public int register(Member member) {
		String sql = "INSERT INTO members (login_id, name, password, gender_id, birthdate)"
				+ " VALUES (:loginId, :name, :password, :genderId, :birthdate)";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("loginId", member.getLoginId());
		paramMap.addValue("name", member.getName());
		paramMap.addValue("password", member.getPassword());
		paramMap.addValue("genderId", member.getGenderId());
		paramMap.addValue("birthdate", member.getBirthdate());

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public int deleteByLoginId(String loginId) {
		String sql = "DELETE FROM members WHERE login_id = :loginId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("loginId", loginId);

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public int update(Member member) {
		String sql = "UPDATE members SET "
				+ " name = :name, login_id = :loginId, password = :password, "
				+ " birthdate = :birthdate, introduction = :introduction, my_space = :mySpace "
				+ " WHERE member_id = :memberId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("loginId", member.getLoginId());
		paramMap.addValue("name", member.getName());
		paramMap.addValue("password", member.getPassword());
		paramMap.addValue("birthdate", member.getBirthdate());
		paramMap.addValue("introduction", member.getIntroduction());
		paramMap.addValue("mySpace", member.getMySpace());
		paramMap.addValue("memberId", member.getMemberId());

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public List<Member> findMembersReactAnArticle(Integer articleId) {
		String sql = "SELECT members.member_id, login_id, name, password, members.created_at, members.updated_at FROM members "
				+ "JOIN reactions ON members.member_id = reactions.member_id WHERE reactions.article_id = :articleId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", articleId);

		return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<Member>(Member.class));
	}

	@Override
	public Member findByLoginId(String loginId) {
		String sql = SELECT_BASE + "WHERE m.login_id = :loginId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("loginId", loginId);

		List<Member> members = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<Member>(Member.class));

		return members.isEmpty() ? null : members.get(0);
	}

	@Override
	public Member findByMemberId(Integer memberId) {
		String sql = SELECT_BASE + "WHERE m.member_id = :memberId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memberId", memberId);

		List<Member> members = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<Member>(Member.class));

		return members.isEmpty() ? null : members.get(0);
	}

	@Override
	public Integer getRank(Integer memberId) {
		String sql = "SELECT COUNT(*) + 1 AS rank "
				+ " FROM ("
				+ "		SELECT COUNT(r.reaction_id) count, m.member_id "
				+ "		FROM members m "
				+ "		LEFT JOIN articles a ON a.member_id = m.member_id "
				+ "		LEFT JOIN reactions r ON r.article_id = a.article_id "
				+ "		GROUP BY m.member_id "
				+ "		ORDER BY COUNT(r.reaction_id) DESC "
				+ "	) as t "
				+ "	WHERE t.count > ( "
				+ "		SELECT COUNT(r.reaction_id) "
				+ "		FROM members m  "
				+ "		LEFT JOIN articles a ON a.member_id = m.member_id "
				+ "		LEFT JOIN reactions r ON r.article_id = a.article_id "
				+ "		WHERE m.member_id = :memberId "
				+ "		GROUP BY m.member_id "
				+ "		ORDER BY COUNT(r.reaction_id) DESC "
				+ "	)";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memberId", memberId);

		return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
	}
}
