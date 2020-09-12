package web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import web.dao.ArticleDao;
import web.entity.Article;
import web.entity.ArticleWithReactionCount;

@Repository
public class ArticleDaoImpl implements ArticleDao {
	private static final String SELECT_BASE = "SELECT * FROM articles ";
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
	public List<Article> findArticleByContent(String content) {
		String sql = SELECT_BASE + "WHERE content LIKE :content";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("content", "%" + content + "%");

		return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<Article>(Article.class));
	}

	@Override
	public List<ArticleWithReactionCount> findArticleWithMostReaction() {
		String sql = "SELECT a.article_id, a.title, a.content, a.user_id, a.article_type_id, a.created_at, a.updated_at, COUNT(r.reaction_id) count"
				+ " FROM articles a"
				+ " JOIN reactions r ON a.article_id = r.article_id"
				+ " GROUP BY a.article_id"
				+ " ORDER BY count DESC";

		return jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<ArticleWithReactionCount>(ArticleWithReactionCount.class));
	}

	@Override
	public List<Article> findArticleReactedByUser(Integer userId) {
		String sql = SELECT_BASE
				+ "JOIN reactions ON articles.article_id = reactions.article_id WHERE reactions.user_id = :userId ORDER BY articles.article_id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("userId", userId);

		return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<Article>(Article.class));
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
	public List<Article> findByKeyword(String keyword) {
		String sql = SELECT_BASE + "WHERE content LIKE :keyword OR title LIKE :keyword";

		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("keyword", "%" + keyword + "%");

		return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<Article>(Article.class));
	}
}
