package web.dao;

import java.util.List;

import web.entity.Article;
import web.entity.ArticleWithExtraInfo;

public interface ArticleDao {
	public int create(Article article);

	public int update(Article article);

	public int delete(Integer articleId);

	public Article findById(Integer articleId);

	public List<ArticleWithExtraInfo> findByKeyword(String keyword);

	public List<ArticleWithExtraInfo> findByKeywordWithMostReaction(String keyword);

	public List<ArticleWithExtraInfo> findByKeywordReactedByUser(Integer userId, String keyword);

	public List<ArticleWithExtraInfo> findByUserId(Integer userId);

	public Article findLatestByUserId(Integer userId);
}
