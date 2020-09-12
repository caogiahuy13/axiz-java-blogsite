package web.form;

public class SearchForm {
	private String keyword;
	private String searchType;

	public SearchForm() {

	}

	public SearchForm(String keyword, String searchType) {
		this.keyword = keyword;
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

}
