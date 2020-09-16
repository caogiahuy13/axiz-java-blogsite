package web.entity;

import java.sql.Timestamp;

public class View {
	public Integer viewId;
	public Integer articleId;
	public Integer memberId;
	public Timestamp createdAt;

	public View() {

	}

	public View(Integer viewId, Integer articleId, Integer memberId, Timestamp createdAt) {
		super();
		this.viewId = viewId;
		this.articleId = articleId;
		this.memberId = memberId;
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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}
