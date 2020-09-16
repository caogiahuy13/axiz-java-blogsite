package web.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.dao.ViewDao;
import web.entity.View;
import web.service.ViewService;

@Service
public class ViewServiceImpl implements ViewService {

	@Autowired
	ViewDao viewDao;

	@Override
	public int insert(View view) {
		return viewDao.insert(view);
	}

	@Override
	public List<View> findByArticleId(Integer articleId) {
		return viewDao.findByArticleId(articleId);
	}

	@Override
	public HashMap<String, Integer> countByAccessByMemberIdOfArticle(Integer memberId) {
		return viewDao.countByAccessByMemberIdOfArticle(memberId);
	}

}
