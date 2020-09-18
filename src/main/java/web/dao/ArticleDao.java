package web.dao;

import java.util.List;

import web.entity.Article;
import web.entity.ArticleWithExtraInfo;

public interface ArticleDao {
	public int create(Article article);

	public int update(Article article);

	public int delete(Integer articleId);

	public Article findById(Integer articleId);

	public List<ArticleWithExtraInfo> findByMemberId(Integer memberId);

	public List<ArticleWithExtraInfo> findByMemberId(Integer memberId, Integer pageNumber,
			Integer itemPerPage);

	public Integer countByMemberId(Integer memberId);

	public Article findLatestByMemberId(Integer memberId);

	public List<ArticleWithExtraInfo> find(String keyword, String sortBy, Integer memberId, Integer pageNumber,
			Integer itemPerPage);
}
