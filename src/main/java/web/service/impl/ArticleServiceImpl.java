package web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.dao.ArticleDao;
import web.entity.Article;
import web.service.ArticleService;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	ArticleDao articleDao;

	@Override
	public int create(Article article) {
		return articleDao.create(article);
	}

	@Override
	public int update(Article article) {
		return articleDao.update(article);
	}

	@Override
	public int delete(Integer articleId) {
		return articleDao.delete(articleId);
	}

	@Override
	public Article findById(Integer articleId) {
		return articleDao.findById(articleId);
	}

	@Override
	public List<? extends Article> find(Integer memberId, String keyword, String searchType, String sortType,
			Integer pageNumber,
			Integer itemPerPage) {
		final String FAVORITES = "favorites";
		final String RANKING = "ranking";
		final String NEWEST = "newest";

		String sortBy = null;

		if (sortType != null && sortType.equals(RANKING)) {
			sortBy = "article_reaction_count";
		} else if (sortType != null && sortType.equals(NEWEST)) {
			sortBy = "created_at";
		}

		return articleDao.find(keyword, sortBy, memberId, pageNumber, itemPerPage);
	}

	@Override
	public List<? extends Article> findByMemberId(Integer memberId) {
		return articleDao.findByMemberId(memberId);
	}

	@Override
	public List<? extends Article> findByMemberId(Integer memberId, Integer pageNumber, Integer itemPerPage) {
		return articleDao.findByMemberId(memberId, pageNumber, itemPerPage);
	}

	@Override
	public Integer countByMemberId(Integer memberId) {
		return articleDao.countByMemberId(memberId);
	}

	@Override
	public Article findLatestByMemberId(Integer memberId) {
		return articleDao.findLatestByMemberId(memberId);
	}
}
