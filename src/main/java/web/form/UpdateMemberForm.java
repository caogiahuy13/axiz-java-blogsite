package web.form;

import javax.validation.constraints.NotBlank;

public class UpdateMemberForm {
	private Integer memberId;

	@NotBlank
	private String loginId;

	@NotBlank
	private String nickname;

	@NotBlank
	private String password;

	@NotBlank
	private String rePassword;

	private String introduction;

	private String mySpace;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
