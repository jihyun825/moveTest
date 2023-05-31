package kr.or.ddit.controller.form.checkboxes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.CodeLabelValue;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/formtag/checkboxes")
public class JSPFormCheckboxesTagController {
	@RequestMapping(value="/registerForm01", method=RequestMethod.GET)
	public String registerForm01(Model model) {
		log.info("registerForm01()실행..!");
		
		Map<String, String> hobbyMap = new HashMap<String, String>();
		hobbyMap.put("01","sports");
		hobbyMap.put("02","music");
		hobbyMap.put("03","movie");
		model.addAttribute("hobbyMap",hobbyMap);
		model.addAttribute("member",new Member());
		return "form/checkboxes/registerForm01";
		
	}
	@RequestMapping(value="/registerForm02", method=RequestMethod.GET)
	public String registerForm02(Model model) {
		log.info("registerForm02()실행...!");
		List<CodeLabelValue> hobbyCodeList = new ArrayList<CodeLabelValue>();
		hobbyCodeList.add(new CodeLabelValue("01","sports"));
		hobbyCodeList.add(new CodeLabelValue("02","music"));
		hobbyCodeList.add(new CodeLabelValue("03","movie"));
		model.addAttribute("hobbyCodeList",hobbyCodeList);
		model.addAttribute("member", new Member());
		return "form/checkboxes/registerForm02";
	}

}
