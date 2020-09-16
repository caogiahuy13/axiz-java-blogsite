package web.util;

import javax.servlet.http.HttpSession;

public final class SessionUtil {
	public static final String CURRENT_MEMBER = "currentMember";
	public static final String TOTAL_REACTIONS = "totalReactions";
	public static final String REGISTER_MEMBER = "registerMember";

	public static void removeAllSession(HttpSession session) {
		session.removeAttribute(CURRENT_MEMBER);
		session.removeAttribute(TOTAL_REACTIONS);
		session.removeAttribute(REGISTER_MEMBER);
	}
}
