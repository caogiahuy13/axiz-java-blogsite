package web.form;

import javax.validation.constraints.NotBlank;

public class SearchForm {
	@NotBlank
	private String keyword;

	public SearchForm() {

	}

	public SearchForm(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
