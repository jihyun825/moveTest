package kr.or.ddit;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tree")
public class TreeController {
	
	@RequestMapping(value="/example")
	public String treepractice() {
		return "exmple";
		
	}
	

}
