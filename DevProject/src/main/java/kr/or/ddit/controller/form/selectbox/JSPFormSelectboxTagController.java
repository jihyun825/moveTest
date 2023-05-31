package kr.or.ddit.controller.form.selectbox;

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
@RequestMapping("/formtag/selectbox")
public class JSPFormSelectboxTagController {
	
	@RequestMapping(value="/registerFormSelectbox01", method=RequestMethod.GET)
	public String registerFormSelectbox01(Model model) {
		log.info("registerFormSelectbox01()실행...!");
		
		Map<String, String> nationalityCodeMap = new HashMap<String, String>();
		nationalityCodeMap.put("01", "korea");
		nationalityCodeMap.put("02", "canada");
		nationalityCodeMap.put("03", "Austrailia");
		
		model.addAttribute("nationalityCodeMap",nationalityCodeMap);
		model.addAttribute("member",new Member());
		return "form/selectbox/registerFormSelectbox01";
		
	}
	

	@RequestMapping(value="/registerFormSelectbox02", method=RequestMethod.GET)
	public String registerFormSelectbox02(Model model) {
		log.info("registerFormSelectbox02()실행...!");
		
		List<CodeLabelValue> nationalityCodeList = new ArrayList<CodeLabelValue>();
		nationalityCodeList.add(new CodeLabelValue("01", "korea"));
		nationalityCodeList.add(new CodeLabelValue("02", "canada"));
		nationalityCodeList.add(new CodeLabelValue("03", "Austrailia"));
		
		model.addAttribute("nationalityCodeList",nationalityCodeList);
		model.addAttribute("member",new Member());
		return "form/selectbox/registerFormSelectbox02";
		
	}
	
	@RequestMapping(value="/registerFormSelectbox03", method=RequestMethod.GET)
	public String registerFormSelectbox03(Model model) {
		log.info("registerFormSelectbox03()실행...!");
		List<CodeLabelValue> carCodeList = new ArrayList<CodeLabelValue>();
		carCodeList.add(new CodeLabelValue("01","Jeep"));
		carCodeList.add(new CodeLabelValue("02","Bmw"));
		carCodeList.add(new CodeLabelValue("03","Volvo"));
		
		model.addAttribute("carCodeList",carCodeList);
		model.addAttribute("member", new Member());
		return "form/selectbox/registerFormSelectbox03";
	}
	
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public String registerFormSelectboxResult(Member member, Model model) {
		log.info("registerFormSelectboxResult()실행...!");
		log.info("nationality:" + member.getNationality());
		model.addAttribute("nationality", member.getNationality());
		return "form/selectbox/result";
		
	}
	
	
}
