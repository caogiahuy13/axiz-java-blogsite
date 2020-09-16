package web.dao;

import java.util.HashMap;
import java.util.List;

import web.entity.View;

public interface ViewDao {
	public int insert(View view);

	public List<View> findByArticleId(Integer articleId);

	public HashMap<String, Integer> countByAccessByMemberIdOfArticle(Integer memberId);
}
