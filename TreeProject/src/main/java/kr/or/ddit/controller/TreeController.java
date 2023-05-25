package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/tree",method=RequestMethod.GET)
public class TreeController {
	
	
		
		@RequestMapping(value="/example")
		public String exampleTree() {
			return "example";
		}



}
