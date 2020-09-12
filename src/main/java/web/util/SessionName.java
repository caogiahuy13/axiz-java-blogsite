package web.util;

import javax.servlet.http.HttpSession;

public final class SessionName {
	public static final String CURRENT_USER = "currentUser";
	public static final String TOTAL_REACTIONS = "totalReactions";

	public static void removeAllSession(HttpSession session) {
		session.removeAttribute(SessionName.CURRENT_USER);
		session.removeAttribute(SessionName.TOTAL_REACTIONS);
	}
}
