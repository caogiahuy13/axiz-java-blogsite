package web.form;

import javax.validation.constraints.NotBlank;

public class RePassForm {
	@NotBlank
	public String rePass;

	public String getRePass() {
		return rePass;
	}

	public void setRePass(String rePass) {
		this.rePass = rePass;
	}

}
