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
	public List<Article> findArticleByContent(String content) {
		return articleDao.findArticleByContent(content);
	}

	@Override
	public List<Article> findArticleWithMostReaction() {
		return articleDao.findArticleWithMostReaction();
	}

	@Override
	public List<Article> findArticleReactedByUser(Integer userId) {
		return articleDao.findArticleReactedByUser(userId);
	}

	@Override
	public Article findById(Integer articleId) {
		return articleDao.findById(articleId);
	}
}
