package web.dao;

import java.util.List;

import web.entity.Article;

public interface ArticleDao {
	public int create(Article article);

	public int update(Article article);

	public int delete(Integer articleId);

	public List<Article> findArticleByContent(String content);

	public List<Article> findArticleWithMostReaction();

	public List<Article> findArticleReactedByUser(Integer userId);
}
