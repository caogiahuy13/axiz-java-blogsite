package web.service;

import java.util.List;

import web.entity.View;

public interface ViewService {
	public int insert(View view);

	public List<View> findByArticleId(Integer articleId);
}
