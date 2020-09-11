package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import web.util.ScreenName;

@Controller
public class TopController {
	private static final String TOP = "top";

	@GetMapping(TOP)
	public String getTop() {
		return ScreenName.TOP;
	}
}
