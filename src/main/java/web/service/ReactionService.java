package web.service;

import web.entity.Reaction;

public interface ReactionService {
	public int insert(Reaction reaction);

	public int delete(Integer articleId, Integer userId);
}
