package web.dao;

import java.util.HashMap;

import web.entity.Reaction;

public interface ReactionDao {
	public int insert(Reaction reaction);

	public int delete(Integer articleId, Integer userId);

	public Reaction findByUserIdAndArticleId(Integer userId, Integer articleId);

	public int countByArticleId(Integer articleId);

	public int countByUserId(Integer userId);

	public HashMap<Integer, Integer> countMultipleByArticleId(Integer articleId);

}
