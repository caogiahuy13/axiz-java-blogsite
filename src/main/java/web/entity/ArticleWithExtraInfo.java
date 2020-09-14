package web.entity;

public class ArticleWithExtraInfo extends Article {

	private Integer count;

	private String userName;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
