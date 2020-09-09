package web.service;

import web.entity.User;

public interface UserService {
	public User authenticate(String loginId, String password);
}
