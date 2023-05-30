package kr.or.ddit.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/fmttag")
public class JSPFmtController {
	
	/*
	 * 7. 숫자 및 날짜 포맷팅 처리태그
	 * 
	 *  - 숫자 및 날짜의 포맷팅과 관련된 태그입니다.
	 *  
	 *  1) <fmt:formatNumber>
	 *   - 숫자를 형식화 합니다.
	 */
	
	//1)타입속성을 지정하거나 pattern속성을 지정하여 숫자를 형식화한다.
	@RequestMapping(value="/home0101", method=RequestMethod.GET)
	public String home0101(Model model) {
		int coin = 100;
		model.addAttribute("coin",coin);
		return "home/fmttag/home0101";
	}
	//2) type속성이 지정되지않으면 기본값은  number이다.
	
	@RequestMapping(value="/home0201", method=RequestMethod.GET)
	public String home0201(Model model) {
		int coin = 1000;
		model.addAttribute("coin",coin);
		return "home/fmttag/home0201";
		
	}
	
	//만약 type속성이 percent인 경우엔 넘겨받아야할값이 '1000%'과 같이 %스타일의 형태값이 넘어가야한다.
	//그래서 type속성과 일치하여 값을 파싱할수있다.
	@RequestMapping(value="/home0202", method=RequestMethod.GET)
	public String home0202(Model model) {
		String coin = "￦1000";
		model.addAttribute("coin",coin);
		return "home/fmttag/home0202";
	}
	
	//pattern을 사용해 직접 사용할 서식을 지정한다.
	@RequestMapping(value="/home0204", method=RequestMethod.GET)
	public String home0204(Model model) {
		String coin = "1,00.25";
		model.addAttribute("coin",coin);
		return "home/fmttag/home0204";
	}
	
	//6) type속성을 date로 지정하여 날짜 포맷팅을 한다.
	@RequestMapping(value="/home0301", method=RequestMethod.GET)
	public String home0301(Model model) {
		Date date = new Date();
		model.addAttribute("now",date);
		return "home/fmttag/home0301";
	}
	
	
	//7) type속성을 time으로 지정해야 시간포맷팅을한다.
	@RequestMapping(value="/home0302", method=RequestMethod.GET)
	public String home0302(Model model) {
		Date date = new Date();
		model.addAttribute("now",date);
		return "home/fmttag/home0302";
	}
	
	//8) type속성을 both로 지정하여 날짜, 시간 둘 다 포매팅한다.
	@RequestMapping(value="/home0303", method = RequestMethod.GET)
	public String home0303(Model model){
		Date date = new Date();
		model.addAttribute("now",date);
		return "home/fmttag/home0303";
	}
	
	//pattern속성을 지정하여 날짜를 포맷팅한다.
	@RequestMapping(value="/home0304", method = RequestMethod.GET)
	public String home0304(Model model){
		Date date = new Date();
		model.addAttribute("now",date);
		return "home/fmttag/home0304";
	}
	
	//dateStyle의 속성을 지정하지않으면 기본값은 default이다.
	@RequestMapping(value="/home0401",method=RequestMethod.GET)
	public String home0401(Model model) {
		String dateValue= "2020. 10. 20";
		model.addAttribute("dateValue",dateValue);
		return "home/fmttag/home0401";
	}
	
	// dateStyle속성을 short로 지정하여 문자열을 Date객체로 변환한다.
	@RequestMapping(value="/home0402",method=RequestMethod.GET)
	public String home0402(Model model) {
		String dateValue= "20. 2. 1"; //style short의 형태로 지정
		//String dateValue = "2020.2.1"; // style medium 형태로 지정
		//String dateValue = "2019년02월01일(금)"; // style long 형태로 지정
		//String dateValue = "2019년02월01일 금요일"; // style full 형태로 지정
		
		//각 date스타일로 지정된 값이 페이지로 넘어가parsing이 진행될때, parseDate내에
		//dateStyle을 각값과 일치하는 스타일 형태로 지정해주어야 값이 파싱된다.
		model.addAttribute("dateValue",dateValue);
		return "home/fmttag/home0402";
	}
	
	//12) pattern 속성을 지정하여 문자열을 date객체로 변환한다.
	@RequestMapping(value="/home0403",method=RequestMethod.GET)
	public String home0403(Model model) {
		String dateValue= "2022-02-01 15:00:24";
		model.addAttribute("dateValue",dateValue);
		return "home/fmttag/home0403";
	}
	
}
