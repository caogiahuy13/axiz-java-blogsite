package web.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.dao.ReactionDao;
import web.entity.Reaction;
import web.entity.ReactionsByArticle;
import web.service.ReactionService;

@Service
@Transactional
public class ReactionServiceImpl implements ReactionService {
	@Autowired
	ReactionDao reactionDao;

	@Override
	public int insert(Reaction reaction) {
		return reactionDao.insert(reaction);
	}

	@Override
	public int delete(Integer articleId, Integer userId) {
		return reactionDao.delete(articleId, userId);
	}

	@Override
	public Reaction findByUserIdAndArticleId(Integer userId, Integer articleId) {
		return reactionDao.findByUserIdAndArticleId(userId, articleId);
	}

	@Override
	public int countByArticleId(Integer articleId) {
		return reactionDao.countByArticleId(articleId);
	}

	@Override
	public int countByUserId(Integer userId) {
		return reactionDao.countByUserId(userId);
	}

	@Override
	public HashMap<Integer, Integer> countMultipleByArticleId(Integer articleId) {
		return reactionDao.countMultipleByArticleId(articleId);
	}

	@Override
	public HashMap<String, Integer> countByGenderByUserIdOfArticle(Integer userId) {
		return reactionDao.countByGenderByUserIdOfArticle(userId);
	}

	@Override
	public HashMap<String, Integer> countByAgeRangeByUserIdOfArticle(Integer userId) {
		return reactionDao.countByAgeRangeByUserIdOfArticle(userId);
	}

	@Override
	public List<ReactionsByArticle> countMultipleByUserIdOfArticle(Integer userId) {
		return reactionDao.countMultipleByUserIdOfArticle(userId);
	}
}
