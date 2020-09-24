package web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import web.dao.ArticleDao;
import web.entity.Article;
import web.entity.ArticleWithExtraInfo;

@Repository
public class ArticleDaoImpl implements ArticleDao {
	private static final String SELECT_BASE = "SELECT * FROM articles ";

	private static final String ARTICLES_JOIN_MEMBERS_JOIN_REACTIONS_TABLE = " SELECT "
			+ "	x.*, y.nickname, y.member_reaction_count "
			+ " FROM ( "
			+ "		SELECT a.*, COUNT(r.reaction_id) article_reaction_count "
			+ "		FROM articles a "
			+ "		LEFT JOIN reactions r ON a.article_id = r.article_id "
			+ "		GROUP BY a.article_id "
			+ " ) AS x "
			+ " JOIN ( "
			+ "		SELECT m.member_id, m.nickname, COUNT(r.reaction_id) member_reaction_count "
			+ "		FROM members m  "
			+ "		LEFT JOIN articles a ON a.member_id = m.member_id "
			+ "		LEFT JOIN reactions r ON r.article_id = a.article_id "
			+ "		GROUP BY m.member_id, m.name "
			+ " ) AS y ON x.member_id = y.member_id ";

	private static final String SELECT_BASE_EXTRA_INFO = "SELECT * FROM ( "
			+ ARTICLES_JOIN_MEMBERS_JOIN_REACTIONS_TABLE
			+ " ) AS a ";

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public int create(Article article) {
		String sql = "INSERT INTO articles (title, content, member_id) VALUES (:title, :content, :memberId)";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("title", article.getTitle());
		paramMap.addValue("content", article.getContent());
		paramMap.addValue("memberId", article.getMemberId());

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public int update(Article article) {
		String sql = "UPDATE articles SET title = :title, content = :content WHERE article_id = :articleId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("title", article.getTitle());
		paramMap.addValue("content", article.getContent());
		paramMap.addValue("articleId", article.getArticleId());

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public int delete(Integer articleId) {
		String sql = "DELETE FROM articles WHERE article_id = :articleId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", articleId);

		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public Article findById(Integer articleId) {
		String sql = SELECT_BASE + " WHERE article_id = :articleId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("articleId", articleId);

		List<Article> articles = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<Article>(Article.class));

		return articles.isEmpty() ? null : articles.get(0);
	}

	@Override
	public List<ArticleWithExtraInfo> findByMemberId(Integer memberId) {
		String sql = SELECT_BASE_EXTRA_INFO
				+ " WHERE m.member_id = :memberId"
				+ " ORDER BY a.created_at DESC";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memberId", memberId);

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<ArticleWithExtraInfo>(ArticleWithExtraInfo.class));
	}

	@Override
	public List<ArticleWithExtraInfo> findByMemberId(Integer memberId, Integer pageNumber,
			Integer itemPerPage) {
		String sql = SELECT_BASE_EXTRA_INFO
				+ " WHERE a.member_id = :memberId"
				+ " ORDER BY a.created_at DESC"
				+ " OFFSET :offset LIMIT :limit";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memberId", memberId);
		paramMap.addValue("offset", (pageNumber - 1) * itemPerPage);
		paramMap.addValue("limit", itemPerPage);

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<ArticleWithExtraInfo>(ArticleWithExtraInfo.class));
	}

	@Override
	public Article findLatestByMemberId(Integer memberId) {
		String sql = SELECT_BASE + " WHERE member_id = :memberId ORDER BY created_at DESC";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memberId", memberId);

		List<Article> articles = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<Article>(Article.class));

		return articles.isEmpty() ? null : articles.get(0);
	}

	@Override
	public Integer countByMemberId(Integer memberId) {
		String sql = " SELECT COUNT(*) FROM articles WHERE member_id = :memberId";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memberId", memberId);

		return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
	}

	@Override
	public List<ArticleWithExtraInfo> find(String keyword, String sortBy, Integer memberId, Integer pageNumber,
			Integer itemPerPage) {
		String sql = SELECT_BASE_EXTRA_INFO;
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		List<String> whereStr = new ArrayList<>();

		if (memberId != null) {
			sql += " JOIN reactions r ON a.article_id = r.article_id ";
			whereStr.add(" r.member_id = :memberId ");
			paramMap.addValue("memberId", memberId);
		}

		sql += "WHERE (a.content LIKE :keyword OR a.title LIKE :keyword) ";
		paramMap.addValue("keyword", "%" + keyword + "%");

		for (int i = 0; i < whereStr.size(); i++) {
			sql += " AND " + whereStr.get(i) + " ";
		}

		if (sortBy == null) {
			sql += " ORDER BY a.created_at DESC ";
		}

		if (sortBy != null && !sortBy.isBlank()) {
			sql += " ORDER BY a." + sortBy + " DESC ";
		}

		if (pageNumber != null && itemPerPage != null) {
			sql += " OFFSET :offset LIMIT :limit ";
			paramMap.addValue("offset", (pageNumber - 1) * itemPerPage);
			paramMap.addValue("limit", itemPerPage);
		}

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<ArticleWithExtraInfo>(ArticleWithExtraInfo.class));
	}
}
