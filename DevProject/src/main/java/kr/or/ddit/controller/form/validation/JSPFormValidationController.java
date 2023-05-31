package kr.or.ddit.controller.form.validation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/formtag/validation")
public class JSPFormValidationController {
	
	@RequestMapping(value="/registerFormValidation01", method=RequestMethod.GET)
	public String registerFormValidation01(Model model) {
		log.info("registerFormValidation01실행...!");
		model.addAttribute("member",new Member());
		return "form/validation/registerFormValidation01";
	}
	
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public String registerFormValidationResult(Member member) {
		log.info("registerFormValidationResult()실행...!");
		log.info("userId : "+member.getUserId());
		log.info("userName : "+member.getUserName());
		log.info("Email : "+member.getEmail());
		
		return "form/validation/result";
		
	}
}
