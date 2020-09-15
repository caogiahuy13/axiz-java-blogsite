package web.service;

import java.util.List;

import web.entity.Article;

public interface ArticleService {
	public int create(Article article);

	public int update(Article article);

	public int delete(Integer articleId);

	public Article findById(Integer articleId);

	public List<? extends Article> find(Integer userId, String keyword, String type);

	public List<? extends Article> findByUserId(Integer userId);

	public List<? extends Article> findByUserIdPagination(Integer userId, Integer pageNumber, Integer itemPerPage);

	public Integer countByUserId(Integer userId);

	public Article findLatestByUserId(Integer userId);

}
