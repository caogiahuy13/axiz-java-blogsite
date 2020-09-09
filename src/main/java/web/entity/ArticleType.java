package web.entity;

public class ArticleType {
	private Integer articleTypeId;
	private String name;

	public ArticleType() {

	}

	public ArticleType(Integer articleTypeId, String name) {
		this.articleTypeId = articleTypeId;
		this.name = name;
	}

	public Integer getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
