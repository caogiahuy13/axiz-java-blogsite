package web.service;

import java.util.HashMap;
import java.util.List;

import web.entity.View;

public interface ViewService {
	public int insert(View view);

	public List<View> findByArticleId(Integer articleId);

	public HashMap<String, Integer> countByAccessByUserIdOfArticle(Integer userId);

}
