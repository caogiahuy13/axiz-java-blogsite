package web.entity;

import java.sql.Timestamp;

public class ArticleWithReactionCount {
	private Integer articleId;
	private String title;
	private String content;
	private Integer userId;
	private Integer articleTypeId;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Integer count;

	public ArticleWithReactionCount() {

	}

	public ArticleWithReactionCount(Integer articleId, String title, String content, Integer userId,
			Integer articleTypeId,
			Timestamp createdAt,
			Timestamp updatedAt,
			Integer count) {
		this.articleId = articleId;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.articleTypeId = articleTypeId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.count = count;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getReviewContent() {
		final int LENGTH = 50;
		return content.length() > LENGTH ? content.substring(0, LENGTH) : content;
	}
}
