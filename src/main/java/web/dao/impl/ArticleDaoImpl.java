package web.dao.impl;

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
	private static final String SELECT_BASE_JOIN_MEMBERS_AND_REACTIONS = "SELECT"
			+ " a.article_id, a.title, a.content, a.member_id, a.created_at, a.updated_at, COUNT(r.reaction_id) count, m.name, m.login_id "
			+ " FROM articles a "
			+ " LEFT JOIN reactions r ON a.article_id = r.article_id "
			+ " JOIN members m ON a.member_id = m.member_id ";
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
	public List<ArticleWithExtraInfo> findByKeyword(String keyword) {
		String sql = SELECT_BASE_JOIN_MEMBERS_AND_REACTIONS
				+ " WHERE a.content LIKE :keyword OR a.title LIKE :keyword"
				+ " GROUP BY a.article_id, m.member_id, m.login_id ";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("keyword", "%" + keyword + "%");

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<ArticleWithExtraInfo>(ArticleWithExtraInfo.class));
	}

	@Override
	public List<ArticleWithExtraInfo> findByKeywordWithMostReaction(String keyword) {
		String sql = SELECT_BASE_JOIN_MEMBERS_AND_REACTIONS
				+ " WHERE a.content LIKE :keyword OR a.title LIKE :keyword"
				+ " GROUP BY a.article_id, m.member_id, m.login_id "
				+ " ORDER BY count DESC";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("keyword", "%" + keyword + "%");

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<ArticleWithExtraInfo>(ArticleWithExtraInfo.class));
	}

	@Override
	public List<ArticleWithExtraInfo> findByKeywordReactedByMember(Integer memberId, String keyword) {
		String sql = SELECT_BASE_JOIN_MEMBERS_AND_REACTIONS
				+ " WHERE r.member_id = :memberId AND (a.content LIKE :keyword OR a.title LIKE :keyword)"
				+ " GROUP BY a.article_id, m.member_id, m.login_id "
				+ " ORDER BY a.article_id";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memberId", memberId);
		paramMap.addValue("keyword", "%" + keyword + "%");

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<ArticleWithExtraInfo>(ArticleWithExtraInfo.class));
	}

	@Override
	public List<ArticleWithExtraInfo> findByMemberId(Integer memberId) {
		String sql = SELECT_BASE_JOIN_MEMBERS_AND_REACTIONS
				+ " WHERE m.member_id = :memberId"
				+ " GROUP BY a.article_id, m.member_id, m.login_id ";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memberId", memberId);

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
	public List<ArticleWithExtraInfo> findByMemberIdPagination(Integer memberId, Integer pageNumber,
			Integer itemPerPage) {
		String sql = SELECT_BASE_JOIN_MEMBERS_AND_REACTIONS
				+ " WHERE m.member_id = :memberId"
				+ " GROUP BY a.article_id, m.member_id, m.login_id "
				+ " ORDER BY a.created_at DESC"
				+ " OFFSET :offset LIMIT :limit";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("memberId", memberId);
		paramMap.addValue("offset", (pageNumber - 1) * itemPerPage);
		paramMap.addValue("limit", itemPerPage);

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<ArticleWithExtraInfo>(ArticleWithExtraInfo.class));
	}
}
