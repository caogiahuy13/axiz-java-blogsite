package web.dao;

import web.entity.User;

public interface UserDao {
	public User authenticate(String loginId, String password);
}
