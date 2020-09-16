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
	public int delete(Integer articleId, Integer memberId) {
		return reactionDao.delete(articleId, memberId);
	}

	@Override
	public Reaction findByMemberIdAndArticleId(Integer memberId, Integer articleId) {
		return reactionDao.findByMemberIdAndArticleId(memberId, articleId);
	}

	@Override
	public int countByArticleId(Integer articleId) {
		return reactionDao.countByArticleId(articleId);
	}

	@Override
	public int countByMemberId(Integer memberId) {
		return reactionDao.countByMemberId(memberId);
	}

	@Override
	public HashMap<Integer, Integer> countMultipleByArticleId(Integer articleId) {
		return reactionDao.countMultipleByArticleId(articleId);
	}

	@Override
	public HashMap<String, Integer> countByGenderByMemberIdOfArticle(Integer memberId) {
		return reactionDao.countByGenderByMemberIdOfArticle(memberId);
	}

	@Override
	public HashMap<String, Integer> countByAgeRangeByMemberIdOfArticle(Integer memberId) {
		return reactionDao.countByAgeRangeByMemberIdOfArticle(memberId);
	}

	@Override
	public List<ReactionsByArticle> countMultipleByMemberIdOfArticle(Integer memberId) {
		return reactionDao.countMultipleByMemberIdOfArticle(memberId);
	}
}
