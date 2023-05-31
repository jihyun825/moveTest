package kr.or.ddit.controller.form.radio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.CodeLabelValue;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value="/formtag/radio")
public class JSPFormRadioTagController {
	
	@RequestMapping(value="/registerFormRadios01", method=RequestMethod.GET)
	public String registerFormRadio1(Model model) {
		log.info("registerFormRadio01()실행...!");
		Map<String, String> genderCodeMap = new HashMap<String, String>();
		genderCodeMap.put("01", "male");
		genderCodeMap.put("02", "female");
		genderCodeMap.put("03", "other");
		
		model.addAttribute("genderCodeMap",genderCodeMap);
		model.addAttribute("member", new Member());
		return "form/radio/registerFormRadios01";
	}
	@RequestMapping(value="/registerFormRadios02", method=RequestMethod.GET)
	public String registerFormRadios02(Model model) {
		log.info("registerFormRadios02()실행...!");
		
		List<CodeLabelValue> genderCodeList =  new ArrayList<CodeLabelValue>();
		
		genderCodeList.add(new CodeLabelValue("01","male"));
		genderCodeList.add(new CodeLabelValue("02","female"));
		genderCodeList.add(new CodeLabelValue("03","other"));
		
		model.addAttribute("genderCodeList",genderCodeList);
		model.addAttribute("member",new Member());
		return "form/radio/registerFormRadios02";

	}
	
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public String registerFormResult(Model model, Member member) {
		log.info("registerFormResult()실행...!");
		log.info("member.gender : " + member.getGender());
		model.addAttribute("gender", member.getGender());
		
		return "form/radio/result";

	}
	
	
	//여기서부터는 단일 radop버튼입니다.
	
	/*
	 * 10. 라이오버튼 요소
	 *  - HTML 라디오 버튼을 출력
	 *  
	 */
	
	@RequestMapping(value="/registerFormRadio01", method=RequestMethod.GET)
	public String registerFormRadio01(Model model) {
		log.info("registerFormRadio01()실행...!");
		model.addAttribute("member",new Member());
		return "form/radio/registerFormRadio01";
	}
	
	@RequestMapping(value="/registerFormRadio02", method=RequestMethod.GET)
	public String registerFormRadio02(Model model) {
		log.info("registerFormRadio02()실행...!");
		Member member = new Member();
		member.setGender("female");
		model.addAttribute("member",member);
		return "form/radio/registerFormRadio01";
	}
	
}
