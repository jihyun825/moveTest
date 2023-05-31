package kr.or.ddit.controller.form.button;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/formtag/button")
public class JSPFormButtonTagController {
			
			@RequestMapping(value="/registerFormButton01",method=RequestMethod.GET)
			public String registerFormButton01(Model model) {
				log.info("registerFormButton01()실행..!");
				model.addAttribute("member",new Member());
				return "form/button/registerFormButton01";
			}

}
