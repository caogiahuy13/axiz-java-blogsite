package web.form;

import javax.validation.constraints.NotBlank;

public class CreateArticleForm {
	@NotBlank
	private String title;

	@NotBlank
	private String content;

	public CreateArticleForm() {

	}

	public CreateArticleForm(String title, String content) {
		this.title = title;
		this.content = content;
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
