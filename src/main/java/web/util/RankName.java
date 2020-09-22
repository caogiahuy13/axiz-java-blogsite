package web.util;

public class RankName {
	public static final String NORMAL = "normal";
	public static final String BRONZE = "bronze";
	public static final String SILVER = "silver";
	public static final String GOLD = "gold";

	public static String getMemberRank(Integer memberReactionCount) {
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
}
