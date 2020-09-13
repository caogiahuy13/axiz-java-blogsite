package web.entity;

public class ReactionsByArticle {
	private Integer stampId;
	private Integer count;
	private String title;

	public ReactionsByArticle() {

	}

	public ReactionsByArticle(Integer stampId, Integer count, String title) {
		this.stampId = stampId;
		this.count = count;
		this.title = title;
	}

	public Integer getStampId() {
		return stampId;
	}

	public void setStampId(Integer stampId) {
		this.stampId = stampId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
