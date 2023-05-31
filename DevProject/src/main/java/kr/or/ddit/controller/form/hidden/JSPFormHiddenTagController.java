package kr.or.ddit.controller.form.hidden;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/formtag/hidden")
public class JSPFormHiddenTagController {
	
	@RequestMapping(value="registerFormHidden01", method=RequestMethod.GET)
	public String registerFormHidden01(Model model) {
		log.info("registerFormHidden01()실행...!");
		Member member = new Member();
		member.setUserId("hong123");
		member.setUserName("홍길순");
		model.addAttribute("member", member);
		return "form/hidden/registerFormHidden01";
	}
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public String registerFormHiddenResult(Member member) {
		log.info("registerFormHiddenResult()실행...!");
		log.info("userId: " + member.getUserId());
		log.info("userName: " + member.getUserName());
		return "form/hidden/result";
	}
}
