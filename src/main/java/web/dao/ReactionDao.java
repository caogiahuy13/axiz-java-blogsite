package web.dao;

import java.util.HashMap;
import java.util.List;

import web.entity.Reaction;
import web.entity.ReactionsByArticle;

public interface ReactionDao {
	public int insert(Reaction reaction);

	public int delete(Integer articleId, Integer userId);

	public Reaction findByUserIdAndArticleId(Integer userId, Integer articleId);

	public int countByArticleId(Integer articleId);

	public int countByUserId(Integer userId);

	public HashMap<Integer, Integer> countMultipleByArticleId(Integer articleId);

	public HashMap<String, Integer> countByGenderByUserIdOfArticle(Integer userId);

	public HashMap<String, Integer> countByAgeRangeByUserIdOfArticle(Integer userId);

	//	public HashMap<Integer, HashMap<String, Integer>> countMultipleByUserIdOfArticle(Integer userId);

	public List<ReactionsByArticle> countMultipleByUserIdOfArticle(Integer userId);
}
