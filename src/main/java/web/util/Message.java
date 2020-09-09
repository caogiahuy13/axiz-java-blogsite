package web.util;

import java.util.Locale;
import java.util.ResourceBundle;

public final class Message {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

	public static final String LOGIN_FAIL = getMessage("login.fail");

	private static String getMessage(String key) {
		return bundle.getString(key);
	}
}
