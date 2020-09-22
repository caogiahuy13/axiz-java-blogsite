package web.dao;

import java.util.HashMap;
import java.util.List;

import web.entity.Reaction;
import web.entity.ReactionsByArticle;

public interface ReactionDao {
	public int insert(Reaction reaction);

	public int delete(Integer articleId, Integer memberId);

	public Reaction findByMemberIdAndArticleId(Integer memberId, Integer articleId);

	public int countByArticleId(Integer articleId);

	public int countByMemberId(Integer memberId);

	public HashMap<Integer, Integer> countMultipleByArticleId(Integer articleId);

	public List<ReactionsByArticle> countMultipleByMemberIdOfArticle(Integer memberId);

	public Integer countByGenderAndAgeRangeAndMemberIdOfArticle(Integer memberId, Integer gender, Integer minAge,
			Integer maxAge);
}
