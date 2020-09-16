package web.service;

import java.util.List;

import web.entity.Article;

public interface ArticleService {
	public int create(Article article);

	public int update(Article article);

	public int delete(Integer articleId);

	public Article findById(Integer articleId);

	public List<? extends Article> find(Integer memberId, String keyword, String type);

	public List<? extends Article> findByMemberId(Integer memberId);

	public List<? extends Article> findByMemberIdPagination(Integer memberId, Integer pageNumber, Integer itemPerPage);

	public Integer countByMemberId(Integer memberId);

	public Article findLatestByMemberId(Integer memberId);

}
