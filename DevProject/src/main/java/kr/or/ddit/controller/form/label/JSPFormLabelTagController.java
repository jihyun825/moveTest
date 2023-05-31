package kr.or.ddit.controller.form.label;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/formtag/label")
public class JSPFormLabelTagController {
	
	@RequestMapping(value="/registerFormLabel01", method=RequestMethod.GET)
	public String registerFormLabel01(Model model) {
		log.info("registerFormLabel01()실행...!");
		model.addAttribute("member", new Member());
		return "form/label/registerFormLabel01";
	}

}
