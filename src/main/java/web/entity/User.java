package web.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
	private Integer userId;
	private String loginId;
	private String userName;
	private String nickname;
	private String password;
	private String gender;
	private Date birthdate;
	private String introduction;
	private String mySpace;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public User() {

	}

	public User(Integer userId, String loginId, String userName, String nickname, String password, String gender,
			Date birthdate, String introduction, String mySpace, Timestamp createdAt, Timestamp updatedAt) {
		this.userId = userId;
		this.loginId = loginId;
		this.userName = userName;
		this.nickname = nickname;
		this.password = password;
		this.gender = gender;
		this.birthdate = birthdate;
		this.introduction = introduction;
		this.mySpace = mySpace;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
