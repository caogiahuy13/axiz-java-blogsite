package web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.dao.UserDao;
import web.entity.User;
import web.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public User authenticate(String loginId, String password) {
		return userDao.authenticate(loginId, password);
	}

	@Override
	public int register(User user) {
		return userDao.register(user);
	}

	@Override
	public int deleteByLoginId(String loginId) {
		return userDao.deleteByLoginId(loginId);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public List<User> findUsersReactAnArticle(Integer articleId) {
		return userDao.findUsersReactAnArticle(articleId);
	}

	@Override
	public User findByLoginId(String loginId) {
		return userDao.findByLoginId(loginId);
	}

	@Override
	public User findByUserId(Integer userId) {
		return userDao.findByUserId(userId);
	}
}
