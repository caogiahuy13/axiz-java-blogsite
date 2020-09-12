package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.util.ScreenName;

@Controller
public class TopController {
	private static final String TOP = "top";

	@GetMapping(TOP)
	public String getTop() {
		return ScreenName.TOP;
	}

	@PostMapping(TOP)
	public String search(@RequestParam String searchType, @RequestParam String keyword) {

		System.out.println(searchType);
		System.out.println(keyword);

		return ScreenName.TOP;
	}
}
