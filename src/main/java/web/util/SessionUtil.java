package web.util;

import javax.servlet.http.HttpSession;

public final class SessionUtil {
	public static final String CURRENT_USER = "currentUser";
	public static final String TOTAL_REACTIONS = "totalReactions";

	public static void removeAllSession(HttpSession session) {
		session.removeAttribute(SessionUtil.CURRENT_USER);
		session.removeAttribute(SessionUtil.TOTAL_REACTIONS);
	}
}
