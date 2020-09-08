package web.entity;

import java.sql.Timestamp;

public class Comment {
	private Integer commentId;
	private String content;
	private Integer articleId;
	private Integer userId;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public Comment() {

	}

	public Comment(Integer commentId, String content, Integer articleId, Integer userId, Timestamp createdAt,
			Timestamp updatedAt) {
		this.commentId = commentId;
		this.content = content;
		this.articleId = articleId;
		this.userId = userId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
