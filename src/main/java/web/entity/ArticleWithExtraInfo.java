package web.entity;

import web.util.Milestone;
import web.util.RankName;

public class ArticleWithExtraInfo extends Article {

	private Integer articleReactionCount;

	private String name;

	private Integer memberReactionCount;

	public String getMemberRank() {
		if (memberReactionCount >= Milestone.GOLD_RANK) {
			return RankName.GOLD;
		} else if (memberReactionCount >= Milestone.SILVER_RANK) {
			return RankName.SILVER;
		} else if (memberReactionCount >= Milestone.BRONZE_RANK) {
			return RankName.BRONZE;
		} else {
			return RankName.NORMAL;
		}
	}

	public boolean hasTrophy() {
		return articleReactionCount >= Milestone.TROPHY ? true : false;
	}

	public Integer getArticleReactionCount() {
		return articleReactionCount;
	}

	public void setArticleReactionCount(Integer articleReactionCount) {
		this.articleReactionCount = articleReactionCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMemberReactionCount() {
		return memberReactionCount;
	}

	public void setMemberReactionCount(Integer memberReactionCount) {
		this.memberReactionCount = memberReactionCount;
	}

}
