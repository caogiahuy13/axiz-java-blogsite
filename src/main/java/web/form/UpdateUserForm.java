package web.form;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

public class UpdateUserForm {
	private Integer userId;

	@NotBlank
	private String loginId;

	@NotBlank
	private String userName;

	@NotBlank
	private String password;

	@NotBlank
	private String rePassword;

	private String gender;
	private Date birthdate;
	private String introduction;
	private String mySpace;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getMySpace() {
		return mySpace;
	}

	public void setMySpace(String mySpace) {
		this.mySpace = mySpace;
	}

}
