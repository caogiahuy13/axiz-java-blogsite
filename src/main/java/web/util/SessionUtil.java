package web.util;

import javax.servlet.http.HttpSession;

public final class SessionUtil {
	public static final String CURRENT_USER = "currentUser";
	public static final String TOTAL_REACTIONS = "totalReactions";
	public static final String REGISTER_USER = "registerUser";

	public static void removeAllSession(HttpSession session) {
		session.removeAttribute(CURRENT_USER);
		session.removeAttribute(TOTAL_REACTIONS);
		session.removeAttribute(REGISTER_USER);
	}
}
