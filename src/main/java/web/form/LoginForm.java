package web.form;

import javax.validation.constraints.NotBlank;

public class LoginForm {
	@NotBlank
	private String loginId;

	@NotBlank
	private String password;

	public LoginForm() {
	}

	public LoginForm(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
