package web.dao;

import web.entity.Reaction;

public interface ReactionDao {
	public int insert(Reaction reaction);

	public int delete(Integer articleId, Integer userId);
}
