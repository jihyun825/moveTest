package kr.or.ddit.controller.password;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/formtag/password")
public class JSPFormPasswordTagController {
	
	
	/*
	 * 8장. 스프링 폼 태그
	 * 
	 */
	@RequestMapping(value="/registerForm01",method = RequestMethod.GET)
	public String registerForm01(Model model) {
		log.info("registerForm01()실행...!");
		model.addAttribute("member",new Member());
		return "form/password/registerForm01";
		
	}
	
	@RequestMapping(value="/registerForm02",method = RequestMethod.GET)
	public String registerForm02(Model model) {
		log.info("registerForm02()실행...!");
		Member member = new Member();
		//값을 설정해서 뷰에 전달하더라도 패스워드는 필드에 반영되지않는다.(패스워드만 그려)
		member.setPassword("0825");
		model.addAttribute("member",member);
		return "form/password/registerForm01";
		
	}
}
