package web.service;

import java.util.HashMap;
import java.util.List;

import web.entity.Reaction;
import web.entity.ReactionsByArticle;

public interface ReactionService {
	public int insert(Reaction reaction);

	public int delete(Integer articleId, Integer memberId);

	public Reaction findByMemberIdAndArticleId(Integer memberId, Integer articleId);

	public int countByArticleId(Integer articleId);

	public int countByMemberId(Integer memberId);

	public HashMap<Integer, Integer> countMultipleByArticleId(Integer articleId);

	public HashMap<String, Integer> countByGenderByMemberIdOfArticle(Integer memberId);

	public HashMap<String, Integer> countByAgeRangeByMemberIdOfArticle(Integer memberId);

	public List<ReactionsByArticle> countMultipleByMemberIdOfArticle(Integer memberId);
}
