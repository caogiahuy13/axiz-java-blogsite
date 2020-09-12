package web.util;

import java.util.Locale;
import java.util.ResourceBundle;

public final class Message {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

	public static final String LOGIN_FAIL = getMessage("login.fail");
	public static final String PASSWORD_IS_NOT_MATCH = getMessage("password.isNotMatch");
	public static final String SEARCH_NO_RESULT = getMessage("search.noResult");
	public static final String MY_ARTICLES_NO_RESULT = getMessage("screen.myArticles.noResult");
	public static final String LOGIN_ID_IS_EXISTED = getMessage("loginId.isExisted");
	public static final String MEMBER_DELETE_SUCCESS = getMessage("member.delete.success");

	private static String getMessage(String key) {
		return bundle.getString(key);
	}
}
