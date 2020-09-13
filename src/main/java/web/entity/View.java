package web.entity;

import java.sql.Timestamp;

public class View {
	public Integer viewId;
	public Integer articleId;
	public Integer userId;
	public Timestamp createdAt;

	public View() {

	}

	public View(Integer viewId, Integer articleId, Integer userId, Timestamp createdAt) {
		super();
		this.viewId = viewId;
		this.articleId = articleId;
		this.userId = userId;
		this.createdAt = createdAt;
	}

	public Integer getViewId() {
		return viewId;
	}

	public void setViewId(Integer viewId) {
		this.viewId = viewId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}
