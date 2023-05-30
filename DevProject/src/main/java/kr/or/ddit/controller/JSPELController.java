package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/el")
public class JSPELController {
	
	/*
	 * 8.EL함수
	 *  - JSTL은 표현언어(EL)에서 사용할 수 있는 함수를 제공한다.
	 * 
	 * fn:contains(str1,str2)
	 * 
	 * fnLcontainsIgnoreCase(Str1,str2)
	 * 
	 * fn:startsWith(str1,str2)
	 * 
	 * fn:endsWith(str1,str2)
	 * 
	 * fn:indexOf(str1,str2)
	 * 
	 * fn:length(obj)
	 * 
	 * fn:escapeXml(str)
	 * 
	 * fn:replace(str,src,dest)
	 * 
	 * fn:toLowerCase(str)
	 * 
	 * fn:toUpperCase(str)
	 */
	
	@RequestMapping(value="/home0101", method=RequestMethod.GET)
	public String home0101(Model model) {
		String str = "<font>Hello world!</font>";
		model.addAttribute("str",str);
		return "home/el/home0101";
		
	}
}
