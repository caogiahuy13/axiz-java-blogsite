package web.form;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

public class RegisterForm {
	@NotBlank
	private String loginId;

	@NotBlank
	private String name;

	@NotBlank
	private String nickname;

	@NotBlank
	private String password;

	private String genderName;

	private Date birthdate;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String gender) {
		this.genderName = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

}
