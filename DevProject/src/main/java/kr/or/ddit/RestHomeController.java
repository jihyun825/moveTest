package kr.or.ddit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

//@RestController는@Controller와 @ResponseBody를 포함한 어노테이션이다.
@RestController
@Slf4j
public class RestHomeController {
	
	//반환값이 객체 타입이면 JSON 타입으로 자동변환된다.
	@RequestMapping(value="/goRestHome0301", method=RequestMethod.GET)
	public Member restHome0301() {
		log.info("restHome0301()실행...!");
		return new Member();
	}
	
	// 반환값이 컬렉션 List이면 JSON객체 배열타입으로 자동 변환된다.
	@RequestMapping(value="/goRestHome0401", method= RequestMethod.GET)
	public List<Member> restHome0401(){
		log.info("restHome0401()실행....!");
		
		List<Member> list = new ArrayList<Member>();
		
		// 첫번째 회원 자바빈즈 객체
		Member member = new Member();
		list.add(member);
		// 두번째 회원 자바빈즈 객체
		Member member2 = new Member();
		list.add(member2);
		
		return list;
	}
	// 반환값이 컬렉션 Map이면 JSON객체 배열타입으로 자동 변환된다.
	@RequestMapping(value="/goRestHome0501", method= RequestMethod.GET)
	public Map<String, Member> goRestHome0501(){
		log.info("goRestHome0501() 실행...!");
		
		Map<String, Member> map = new HashMap<String, Member>();
		Member member = new Member();
		Member member2 = new Member();
		
		map.put("key1", member);
		map.put("key2", member2);
		return map;
	}
	
	//200 OK상태코드를 전송한다.
	// Void 타입은 아무런 형태가 아닌데 제네릭 타입의 뭔가는 채워야겠어서 채우는 PlaceHolder같은 느낌..이랄까..
	@RequestMapping(value="/goRestHome0601", method=RequestMethod.GET)
	public ResponseEntity<Void> goRestHome0601(){
		log.info("goHome0601()실행....!");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	

	//"SUCCESS" 메세지와 200OK 상태코드를 전송한다.
	@RequestMapping(value="/goRestHome0701", method=RequestMethod.GET)
	public ResponseEntity<String> goRestHome0701(){
		log.info("goRestHome0701()실행...!");
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	//객체JSON타입의 데이터와 200 OK 상태코드를 전송한다
	@RequestMapping(value="/goRestHome0801", method=RequestMethod.GET)
	public ResponseEntity<Member> goRestHome0801(){
		log.info("goRestHome0801()실행...!");
		Member member = new Member();
		
		return new ResponseEntity<Member>(member,HttpStatus.OK);
	}
	
	//객체의 Json객체 배열 타입 데이터와 200 OK 상태코드를 전송한다.
	@RequestMapping(value="/goRestHome0901", method=RequestMethod.GET)
	public ResponseEntity<List<Member>> goRestHome0901(){
		log.info("goRestHome0901()실행...!");
		List<Member> list = new ArrayList<Member>();
		Member member = new Member();
		Member member2 = new Member();
		list.add(member);
		list.add(member2);
		
		return new ResponseEntity<List<Member>>(list,HttpStatus.OK);
		
	}
	
	//객체의 Json객체 배열 타입 데이터와 200 OK 상태코드를 전송한다.
	@RequestMapping(value="/goRestHome1001", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Member>> goRestHome1001(){
		log.info("goRestHome1001()실행...!");
		Map<String, Member> map = new HashMap<String, Member>();
		Member member = new Member();
		Member member2 = new Member();
		
		map.put("key1", member);
		map.put("key2", member2);
		return new ResponseEntity<Map<String,Member>> (map,HttpStatus.OK);
	}
	
	@RequestMapping(value="/goRestHome1101", method=RequestMethod.GET)
	public ResponseEntity<byte[]> goRestHome1101(){
		log.info("goRestHome1101()실행...!");
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		HttpHeaders headers = new HttpHeaders();
		try {
			
			//파일경로를 나타내는 두가지방법
			in = new FileInputStream("D:\\A_TeachingMaterial\\6.JspSpring\\02.SPRING2\\chopa.jpg");
			headers.setContentType(MediaType.IMAGE_JPEG);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
		
	// 파일의 데이터를 브라우저다운로드 받도록 한다.
	@RequestMapping(value="/goRestHome1102", method=RequestMethod.GET)
	public ResponseEntity<byte[]> goRestHome1102() throws Exception{
		log.info("goRestHome1102()실행...!");
		String fileName = "data.zip";
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		HttpHeaders headers = new HttpHeaders();
		try {
			
			//파일경로를 나타내는 두가지방법
			in = new FileInputStream("D:\\A_TeachingMaterial\\6.JspSpring\\02.SPRING2\\"+fileName);
			//MediaType.APPLICATION_OCTET_STREAM은 이진파일을 위한 기본값이다.
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition","attachment; filename=\""+
			new String(fileName.getBytes("utf-8"),"ISO-8859-1")+"\"");
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
	}

}
