package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/tree")
public class TreeController {
	
	@RequestMapping(value="/example")
	public String exampleTree() {
		return "example";
	}

}
