package web.entity;

import java.sql.Timestamp;

public class CommentWithMemberInfo {
	private Integer commentId;
	private String content;
	private Integer articleId;
	private Integer memberId;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String name;

	public CommentWithMemberInfo() {

	}

	public CommentWithMemberInfo(Integer commentId, String content, Integer articleId, Integer memberId,
			Timestamp createdAt,
			Timestamp updatedAt,
			String name) {
		this.commentId = commentId;
		this.content = content;
		this.articleId = articleId;
		this.memberId = memberId;
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

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
