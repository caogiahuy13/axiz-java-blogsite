package web.dao;

import java.util.List;

import web.entity.Article;
import web.entity.ArticleWithExtraInfo;

public interface ArticleDao {
	public int create(Article article);

	public int update(Article article);

	public int delete(Integer articleId);

	public Article findById(Integer articleId);

	public List<Article> findByKeyword(String keyword);

	public List<ArticleWithExtraInfo> findByKeywordWithMostReaction(String keyword);

	public List<Article> findByKeywordReactedByUser(Integer userId, String keyword);

	public List<Article> findByUserId(Integer userId);

	public Article findLatestByUserId(Integer userId);
}
