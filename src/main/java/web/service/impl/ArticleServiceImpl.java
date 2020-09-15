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
	public List<? extends Article> find(Integer userId, String keyword, String type) {
		final String ALL = "all";
		final String FAVORITES = "favorites";
		final String RANKING = "ranking";

		switch (type) {
		case ALL:
			return articleDao.findByKeyword(keyword);
		case FAVORITES:
			return articleDao.findByKeywordReactedByUser(userId, keyword);
		case RANKING:
			return articleDao.findByKeywordWithMostReaction(keyword);
		default:
			return articleDao.findByKeyword(keyword);
		}
	}

	@Override
	public List<? extends Article> findByUserId(Integer userId) {
		return articleDao.findByUserId(userId);
	}

	@Override
	public List<? extends Article> findByUserIdPagination(Integer userId, Integer pageNumber, Integer itemPerPage) {
		return articleDao.findByUserIdPagination(userId, pageNumber, itemPerPage);
	}

	@Override
	public Integer countByUserId(Integer userId) {
		return articleDao.countByUserId(userId);
	}

	@Override
	public Article findLatestByUserId(Integer userId) {
		return articleDao.findLatestByUserId(userId);
	}
}
