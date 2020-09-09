package web.form;

import javax.validation.constraints.NotBlank;

public class RegisterForm {
	@NotBlank
	private String loginId;

	@NotBlank
	private String userName;

	@NotBlank
	private String password;

	@NotBlank
	private String rePassword;

	public RegisterForm() {

	}

	public RegisterForm(String loginId, String userName, String password, String rePassword) {
		this.loginId = loginId;
		this.userName = userName;
		this.password = password;
		this.rePassword = rePassword;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

}
