package web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.dao.ReactionDao;
import web.entity.Reaction;
import web.entity.ReactionsByAgeRangeAndGender;
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
	public List<ReactionsByArticle> countMultipleByMemberIdOfArticle(Integer memberId) {
		return reactionDao.countMultipleByMemberIdOfArticle(memberId);
	}

	@Override
	public List<ReactionsByAgeRangeAndGender> countByGenderAndAgeRangeAndMemberIdOfArticle(Integer memberId) {
		Map<Integer, String> genderMap = new HashMap<>();
		genderMap.put(1, "男性");
		genderMap.put(2, "女性");

		int[] minAge = { 10, 20, 30, 40, 50, 60 };
		int[] maxAge = { 20, 30, 40, 50, 60, 150 };
		String[] ageRangeName = { "10~20", "20年代", "30年代", "40年代", "50年代", "60~" };

		List<ReactionsByAgeRangeAndGender> ageRangeAndGenderAnalytics = new ArrayList<>();
		for (int i = 0; i < minAge.length; i++) {
			for (int j = 1; j < genderMap.size() + 1; j++) {
				int count = reactionDao.countByGenderAndAgeRangeAndMemberIdOfArticle(memberId, j, minAge[i],
						maxAge[i]);
				ReactionsByAgeRangeAndGender ele = new ReactionsByAgeRangeAndGender(
						genderMap.get(j), ageRangeName[i], count);
				ageRangeAndGenderAnalytics.add(ele);
			}
		}

		return ageRangeAndGenderAnalytics;
	}
}
