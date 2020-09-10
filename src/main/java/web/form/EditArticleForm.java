package web.form;

import javax.validation.constraints.NotBlank;

public class EditArticleForm {
	private Integer articleId;

	@NotBlank
	private String title;

	@NotBlank
	private String content;

	public EditArticleForm() {

	}

	public EditArticleForm(Integer articleId, String title, String content) {
		this.articleId = articleId;
		this.title = title;
		this.content = content;
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

}
