package web.entity;

public class ReactionsByAgeRangeAndGender {
	private String gender;
	private String ageRange;
	private Integer count;

	public ReactionsByAgeRangeAndGender() {

	}

	public ReactionsByAgeRangeAndGender(String gender, String ageRange, Integer count) {
		super();
		this.gender = gender;
		this.ageRange = ageRange;
		this.count = count;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
