package web.entity;

public class ReactionIcon {
	private Integer reactionIconId;
	private String name;

	public ReactionIcon() {

	}

	public ReactionIcon(Integer reactionIconId, String name) {
		this.reactionIconId = reactionIconId;
		this.name = name;
	}

	public Integer getReactionIconId() {
		return reactionIconId;
	}

	public void setReactionIconId(Integer reactionIconId) {
		this.reactionIconId = reactionIconId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
