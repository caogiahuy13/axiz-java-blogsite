package web.dao;

import java.util.List;

import web.entity.User;

public interface UserDao {
	public User authenticate(String loginId, String password);

	public int register(User user);

	public int deleteByLoginId(String loginId);

	public int update(User user);

	public List<User> findUsersReactAnArticle(Integer articleId);
}
