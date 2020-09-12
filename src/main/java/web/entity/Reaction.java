package web.entity;

import java.sql.Timestamp;

public class Reaction {
	private Integer reactionId;
	private Integer articleId;
	private Integer userId;
	private Integer stampId;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public Reaction() {

	}

	public Reaction(Integer reactionId, Integer articleId, Integer userId, Integer stampId, Timestamp createdAt,
			Timestamp updatedAt) {
		this.reactionId = reactionId;
		this.articleId = articleId;
		this.userId = userId;
		this.stampId = stampId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getReactionId() {
		return reactionId;
	}

	public void setReactionId(Integer reactionId) {
		this.reactionId = reactionId;
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

	public Integer getStampId() {
		return stampId;
	}

	public void setStampId(Integer stampId) {
		this.stampId = stampId;
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
