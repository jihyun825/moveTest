package kr.or.ddit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ajax")
@Slf4j
public class AjaxMemberController {
	@RequestMapping(value="/registerForm", method=RequestMethod.GET)
	public String ajaxRegisterForm() {
		log.info("ajaxRegisterForm() 실행...!");
		return "member/ajaxRegisterForm";
	}
	
	@RequestMapping(value="/register/{userId}")
	public ResponseEntity<String> ajaxRegister01(@PathVariable("userId")String userId){
		log.info("ajaxRegister01() 실행...!");
		log.info("userId : "+userId);
		
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		return entity;
	}
	
	@RequestMapping(value="/register/{userId}/{password}", method = RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister02(@PathVariable("userId")String userId, @PathVariable("password")String password){
		log.info("ajaxRegister02() 실행...!");
		log.info("userId : "+userId);
		log.info("password : "+password);
		
		return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
	}

}
