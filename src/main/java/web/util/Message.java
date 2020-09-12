package web.util;

import java.util.Locale;
import java.util.ResourceBundle;

public final class Message {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

	public static final String LOGIN_FAIL = getMessage("login.fail");
	public static final String PASSWORD_IS_NOT_MATCH = getMessage("password.isNotMatch");
	public static final String SEARCH_NO_RESULT = getMessage("search.noResult");
	public static final String MY_ARTICLES_NO_RESULT = getMessage("screen.myArticles.noResult");

	private static String getMessage(String key) {
		return bundle.getString(key);
	}
}
