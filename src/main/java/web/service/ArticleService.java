package web.service;

import java.util.List;

import web.entity.Article;
import web.entity.ArticleWithReactionCount;

public interface ArticleService {
	public int create(Article article);

	public int update(Article article);

	public int delete(Integer articleId);

	public List<Article> findArticleByContent(String content);

	public List<ArticleWithReactionCount> findArticleWithMostReaction();

	public List<Article> findArticleReactedByUser(Integer userId);

	public Article findById(Integer articleId);

}
