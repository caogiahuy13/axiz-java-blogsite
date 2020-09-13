package web.dao;

import java.util.List;

import web.entity.View;

public interface ViewDao {
	public int insert(View view);

	public List<View> findByArticleId(Integer articleId);
}
