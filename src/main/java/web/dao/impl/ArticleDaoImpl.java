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
	private static final String SELECT_BASE_JOIN_USERS_AND_REACTIONS = "SELECT"
			+ " a.article_id, a.title, a.content, a.user_id, a.created_at, a.updated_at, COUNT(r.reaction_id) count, u.user_name, u.login_id "
			+ " FROM articles a "
			+ " JOIN reactions r ON a.article_id = r.article_id "
			+ " JOIN users u ON a.user_id = u.user_id ";
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public int create(Article article) {
		String sql = "INSERT INTO articles (title, content, user_id) VALUES (:title, :content, :userId)";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("title", article.getTitle());
		paramMap.addValue("content", article.getContent());
		paramMap.addValue("userId", article.getUserId());

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
		String sql = SELECT_BASE_JOIN_USERS_AND_REACTIONS
				+ " WHERE a.content LIKE :keyword OR a.title LIKE :keyword"
				+ " GROUP BY a.article_id, u.user_id, u.login_id ";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("keyword", "%" + keyword + "%");

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<ArticleWithExtraInfo>(ArticleWithExtraInfo.class));
	}

	@Override
	public List<ArticleWithExtraInfo> findByKeywordWithMostReaction(String keyword) {
		String sql = SELECT_BASE_JOIN_USERS_AND_REACTIONS
				+ " WHERE a.content LIKE :keyword OR a.title LIKE :keyword"
				+ " GROUP BY a.article_id, u.user_id, u.login_id "
				+ " ORDER BY count DESC";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("keyword", "%" + keyword + "%");

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<ArticleWithExtraInfo>(ArticleWithExtraInfo.class));
	}

	@Override
	public List<ArticleWithExtraInfo> findByKeywordReactedByUser(Integer userId, String keyword) {
		String sql = SELECT_BASE_JOIN_USERS_AND_REACTIONS
				+ " WHERE r.user_id = :userId AND (a.content LIKE :keyword OR a.title LIKE :keyword)"
				+ " GROUP BY a.article_id, u.user_id, u.login_id "
				+ " ORDER BY a.article_id";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("userId", userId);
		paramMap.addValue("keyword", "%" + keyword + "%");

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<ArticleWithExtraInfo>(ArticleWithExtraInfo.class));
	}

	@Override
	public List<ArticleWithExtraInfo> findByUserId(Integer userId) {
		String sql = SELECT_BASE_JOIN_USERS_AND_REACTIONS
				+ " WHERE u.user_id = :userId"
				+ " GROUP BY a.article_id, u.user_id, u.login_id ";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("userId", userId);

		return jdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<ArticleWithExtraInfo>(ArticleWithExtraInfo.class));
	}

	@Override
	public Article findLatestByUserId(Integer userId) {
		String sql = SELECT_BASE + " WHERE user_id = :userId ORDER BY created_at DESC";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("userId", userId);

		List<Article> articles = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<Article>(Article.class));

		return articles.isEmpty() ? null : articles.get(0);
	}
}
