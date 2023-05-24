package kr.or.ddit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.FileMember;
import kr.or.ddit.vo.Member;
import kr.or.ddit.vo.MultipartFileMember;
import kr.or.ddit.vo.allform.MemberAllForm;
import lombok.extern.slf4j.Slf4j;


/*
 *  5장 컨트롤러
 *  1. 컨트롤러 메소드 매개변수
 *  Model 
 *  - 이동대상에 전달할 데이터를 가지고있는 인터페이스
 *  
 *  RedirectAttributes
 *  - 리다이렉트 대상에 전달할 데이터를 가지고있는 인터페이스
 *  
 *  MultipartFile
 *  - 멀티파트 요청을 사용해 업로드 된 파일 정보를 가지고있는 인터페이스
 *  
 *  BindingResult
 *  - 도메인클래스의 입력값 검증을 가지고있는 인터페이스
 *  
 *  Java.security.Principal
 *  - 클라이언트 인증을 위한 사용자정보를 가지고 있는 인터페이스
 */
@Controller
@Slf4j
public class MemberController {
	@RequestMapping(value="/registerForm", method=RequestMethod.GET)
	public String registerForm() {
		log.info("registerForm() 실행...!");
		return "member/registerForm";
	}
	
	/*
	 * 2.컨트롤러 메소드 매개변수 (요청처리)
	 * 
	 * 1)URL경로상의 쿼리파라미터 정보로부터 요청데이터를 취득할수있다.
	 */
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerByParameter(String userId, String password) {
		
		log.info("registerByParameter() 실행...!");
		log.info("userId:" + userId);
		log.info("password:" + password);
		return "success";
	}
	// 2) URL경로 상의 경로 변수로부터 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register/{userId}",method=RequestMethod.GET)
	public String registerByPath(@PathVariable String userId) {
		// 결과로 userId는 null이 표시된다.
		// 넘겨받은 id를 위와 같은 형태로 받으려면 @PathVeraiables를 이용한다.
		log.info("registerByPath()실행....!");
		log.info("userId: " + userId);
		return "success";
		
	}
	//3) HTML Form 필드명과 컨트롤러 매개변수명이 일치하면 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register01", method=RequestMethod.POST)
	public String register01(String userId) {
		log.info("register01()실행....!");
		log.info("userId :" + userId);
		
		return "success";
	}
	
	//4) HTML Form 필드가 여러개 일 경우에도 컨트롤러 매개변수명이 일치하면 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register02", method=RequestMethod.POST)
	public String register02(String userId, String password) {
		log.info("register02()실행....!");
		log.info("userId :" + userId);
		log.info("password :" + password);
		return "success";
	}
	
	//5) HTML Form 필드가 여러개 일 경우에 컨트롤러 매개변수명의 위치는 상관이 있는가
	@RequestMapping(value="/register03", method=RequestMethod.POST)
	public String register03(String password, String userId) {
		log.info("register03()실행....!");
		log.info("password :" + password);
		log.info("userId :" + userId);
		return "success";
	}
	
	 //6) HTML Form 필드값이 숫자일 경우에는 컨트롤러 매개변수 타입이 문자열이면 그대로 문자열 형태로 들어가는가?
	@RequestMapping(value="/register04", method=RequestMethod.POST)
	public String register04(String password, String userId, String coin) {
		log.info("register04()실행....!");
		log.info("password :" + password);
		log.info("userId :" + userId);
		log.info("coin :" + coin);
		return "success";
	}
	
	//7) HTML Form 필드값이 숫자일 경우에는 컨트롤러 매개변수 타입이 숫자형이면 숫자로 타입변환하여 데이터를 취득하는가형태로 들어가는가?
	@RequestMapping(value="/register05", method=RequestMethod.POST)
	public String register05(String password, String userId, int coin) {
		log.info("register05()실행....!");
		log.info("password :" + password);
		log.info("userId :" + userId);
		log.info("coin :" + coin);
		return "success";
	}
	
	/*
	 * 3.요청데이터 처리 어노테이션
	 * 
	 * @PathVariables
	 * -URL에서 경로변수 값을 가져오기 위한 어노테이션
	 * 
	 * @RequestParam
	 * -요청 파라미터 값을 가져오기 위한 어노테이션
	 * 
	 * @RequestBody
	 * - 요청 본문 내용을 가져오기위한 어노테이션
	 * 
	 * @CookieValue
	 * -쿠키값을 가져오기 위한 어노테이션
	 */
	
	// 1)URL경로상의 경로 변수가 여러개일때 @PathVariable 어노테이션을 사용하여 특정한 경로 변수명을 지정해준다
	
	@RequestMapping(value="/register/{userId}/{coin}", method=RequestMethod.GET)
	public String registerByPath(@PathVariable("userId") String userId,@PathVariable("coin") int coin) {
		log.info("registerByPath()실행....!");
		log.info("userId :" + userId);
		log.info("coin :" + coin);
		return "success";
		
	}
	// 2) HTML 폼의 필드명과 컨트롤러 매개변수명이 일치하면 요청을 처리할 수 있다.
	
	@RequestMapping(value="/register0101", method=RequestMethod.POST)
	public String register0101(String userId) {
		log.info("register0101()실행....!");
		log.info("userId :" + userId);
		return "success";
		
	}
	
	//3) HTML 폼 필드명과 컨트롤러 매개변수명이 일치(대소문자구분)하지 않으면 요청을 처리할 수 없다.
	// 클라이언트에서 선언된 필드명은  userId인데 서버 컨트롤러 메소드에서 받는 필드명이 username이면 파라미터를 받을 수 없다.
	//정확하게 각 필드명이 일치했을때만 파라미터를 받을수있다.
	
	@RequestMapping(value="/register0201", method=RequestMethod.POST)
	public String register0201(String userName) {
		log.info("register0201()실행....!");
		log.info("userId :" + userName);
		return "success";
		
	} 
	
	//4) @RequestParam 어노테이션을 사용하여 특정한 HTML Form 의 필드명을 지정하여 요청을 처리한다.
	// 데이터를 받는 필드명은 동일하게 userId로 하되, 사용하는 변수명은 userName으로 달리설정할 수있다.
	// 우리가 페이징을 구현할때 page를 넘기는 방법을 해당이녀석을 채택해서 구현함.
	@RequestMapping(value="/register0202", method=RequestMethod.POST)
	public String register0202(@RequestParam("userId") String userName) {
		log.info("register0202()실행....!");
		log.info("userName :" + userName);
		return "success";
	} 
	
	
	/*
	 * 4. 요청 처리 자바빈즈
	 */
	//1) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/beans/register01", method=RequestMethod.POST)
	public String registerJavaBeans01(Member member) {
		log.info("registerJavaBeans01()실행....!");
		log.info("member.userid :" + member.getUserId());
		log.info("member.password :" + member.getPassword());
		log.info("member.coin :" + member.getCoin());
		return "success";
	}
	//2) 폼 텍스트 필드 요소값을 자바빈즈 매개변수와 기본 데이터타입인 정수타입 매개변수로 처리한다.
	@RequestMapping(value="/beans/register02", method=RequestMethod.POST)
	public String registerJavaBeans02(Member member, int coin) {
		log.info("registerJavaBeans02()실행....!");
		log.info("member.userid :" + member.getUserId());
		log.info("member.password :" + member.getPassword());
		log.info("member.coin :" + member.getCoin());
		log.info("coin :"+coin);
		
		return "success";
	}
	
	//3) 여러개의 폼텍스트 필드 요소값을 매개변수 순서와 상관없이 매개변수명을 기준으로 처리한다.
	
	@RequestMapping(value="/beans/register03", method=RequestMethod.POST)
	public String registerJavaBeans03(int uid, Member member) {
		log.info("registerJavaBeans03()실행....!");
		log.info("uid :" +uid);
		log.info("member.userid :" + member.getUserId());
		log.info("member.password :" + member.getPassword());
		log.info("member.coin :" + member.getCoin());
		
		return "success";
	}
	
	/*
	 * 5. Date 타입 처리 
	 * - 스프링MVC 는 Date타입의 데이터를 처리하는 여러방법을 제공한다.
	 * - 따로 지정하지않으면 변환에 적합한 날짜 문자열 형식이 어떤것이 있는지 알아보자.
	 * 
	 */
	
	@RequestMapping(value="/registerByGet01", method=RequestMethod.GET)
	public String registerByGet01(String userId,@DateTimeFormat(pattern = "yyyyMMdd") Date dateOfBirth) {
		log.info("registerByGet01()실행....!");
		log.info("userId :" +userId);
		log.info("dateOfBirth :" +dateOfBirth);
		return "success";
	}
	
	@RequestMapping(value="/registerByGet02", method=RequestMethod.GET)
	public String registerByGet02(Member member) {
		log.info("registerByGet02()실행....!");
		log.info("member.userid  :" +member.getUserId());
		log.info("member.dateOfBirth :" +member.getDateOfBirth());
		return "success";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(Member member) {
		log.info("register()실행....!");
		log.info("member.userid  :" +member.getUserId());
		log.info("member.password  :" +member.getPassword());
		log.info("member.dateOfBirth :" +member.getDateOfBirth());
		return "success";
	}
	
	/*
	 * 6. @DataTimeFormat 어노테이션
	 * -@DateTimeFormat 어노테이션의 pattern속성값에 원하는 날짜형식을 지정할 수있다.
	 * 
	 * > 테스트는 /registerByGet02를 요청하고 파라미터로 받아 줄 , Member클래스의 날짜를 받는 필드의 어노테이션 설정
	 * 
	 */
	
	/*
	 * 7. 폼방식 요청 처리
	 * 
	 */

	//폼텍스트 필드 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerUserId", method=RequestMethod.POST)
	public String registerUserId(String userId) {
		log.info("registerUserId() 실행...!");
		log.info("userid  :" + userId);
		return "success";
	}
	
	
	//폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerMemberUserId", method=RequestMethod.POST)
	public String registerMemberUserId(Member member) {
		log.info("registerMemberUserId() 실행...!");
		log.info("userid  :" + member.getUserId());
		return "success";
	}
	
	//폼 비밀번호 필드요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerPassword", method=RequestMethod.POST)
	public String registerPassword(Member member) {
		log.info("registerPassword() 실행...!");
		log.info("member.getPassword()  :" + member.getPassword());
		return "success";
	}
	
	// 폼 라디오버튼 요소값을 문자열 타입 매개변수로 처리한다
	@RequestMapping(value="/registerRadio", method=RequestMethod.POST)
	public String registerRadio(String gender) {
		log.info("registerRadio() 실행...!");
		log.info("gender  :" + gender);
		return "success";
	}
	
	//폼 셀렉트 박스 요소값을 기본데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerSelect", method=RequestMethod.POST)
	public String registerSelect(String nationality) {
		log.info("registerSelect() 실행...!");
		log.info("nationality  :" + nationality);
		return "success";
	}
	
	
	//복수 선택이 가능한 폼 셀렉트 박스요소값을 기본데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultiSelect01", method=RequestMethod.POST)
	public String registerMultiSelect01(String cars) {
		log.info("registerMultiSelect01() 실행...!");
		log.info("cars  :" + cars);
		return "success";
	}
	
	//복수선택이 가능한 폼 셀렉트 박스 요소값을 문자열 배열타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultiSelect02", method=RequestMethod.POST)
	public String registerMultiSelect02(String[] carArray) {
		log.info("registerMultiSelect02() 실행...!");
		log.info("carArray  :" + carArray);
		if(carArray != null) {
			log.info("carArray.length  :" + carArray.length);
			for(int i = 0;i<carArray.length; i++) {
				log.info("carArray["+i+"]  :" + carArray[i]);
			}
		}else {
			log.info("carArray is null");
		}
		return "success";
	}
	//복수선택이 가능한 폼 셀렉트 박스 요소값을 문자열요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultiSelect03", method=RequestMethod.POST)
	public String registerMultiSelect03(ArrayList<String> carList) {
		// 리스트로는 셀렉트박스값을 가져 올 수 없다.
		//가져오려면 배열형태를 이용하거나 객체를 이용해서 데이터를 얻어온다.
		log.info("registerMultiSelect03() 실행...!");
		if(carList != null && carList.size() > 0) { //데이터가 존재
			log.info("carList.size() :" + carList.size());
			for(int i = 0;i<carList.size(); i++) {
				log.info("carList["+i+"]  :" + carList.get(i));
			}
		}else {
			log.info("carList is null");
		}
		return "success";
	}
	
	//폼 체크박스 요소값을 기본데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox01", method=RequestMethod.POST)
	public String registerCheckbox01(String hobby) {
		log.info("registerCheckbox01() 실행...!");
		log.info("hobby  :" + hobby);
		return "success";
	}
	
	//폼 체크박스 요소값을 문자열 배열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox02", method=RequestMethod.POST)
	public String registerCheckbox02(String[] hobbyArray) {
		log.info("registerCheckbox02() 실행...!");
		if(hobbyArray != null) {
			log.info("hobbyArray.length  :" + hobbyArray.length);
			for(int i = 0;i<hobbyArray.length; i++) {
				log.info("hobbyArray["+i+"]  :" + hobbyArray[i]);
			}
		}else {
			log.info("hobbyArray is null");
		}
		return "success";
	}
	
	//폼 체크박스 요소값을 문자열요소를 가진 리스트 컬렉션타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox03", method=RequestMethod.POST)
	public String registerCheckbox03(ArrayList<String> hobbyList) {
		//받는 타입을 LIst로 하게되면 no primary or default constructor found for interface java.util.list]에러발생
		// 스프링에서는 List 타입으로 데이터를 받는데에 문제가있다. (데이터 바인딩이 안됨)
		log.info("registerCheckbox03() 실행...!");
		if(hobbyList != null) { //데이터가 존재
			log.info("hobbyList.size() :" + hobbyList.size());
			for(int i = 0;i<hobbyList.size(); i++) {
				log.info("hobbyList["+i+"]  :" + hobbyList.get(i));
			}
		}else {
			log.info("hobbyList is null");
		}
		return "success";
	}
	
	
	
	@RequestMapping(value="/registerCheckbox04", method=RequestMethod.POST)
	public String registerCheckbox04(String developer) {
		//값이 존재하면value속성안에 들어있는 값이 넘어오고
		//값이 존재하지않으면 null값이 출력됨
		log.info("registerCheckbox04() 실행...!");
		log.info("developer  :" + developer);
		return "success";
	}
	
	//폼 체크박스 요소값을 기본데이터 타입인 불리언 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox05", method=RequestMethod.POST)
	public String registerCheckbox05(boolean foreigner ) {
		// 개인정보 동의와 같은 기능 을 만들때 사용
		//체크된값이 존재하면 value속성에 설정된 true가 넘어오고,
		//체크된 값이 존재하지않으면 false가 넘어온다.
		log.info("registerCheckbox05() 실행...!");
		log.info("foreigner   :" + foreigner );
		return "success";
	}
	
	//폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerAddress", method=RequestMethod.POST)
	public String registerAddress(Address address ) {
		log.info("registerAddress() 실행...!");
		
		if(address != null) {
			log.info("address.getPostCode() :" + address.getPostCode() );
			log.info("address.getLocation() :" + address.getLocation() );
		}else {
			log.info("address is null");
		}
		return "success";
		
	}
	/* Member객체에서 get써서 가져오기
	@RequestMapping(value="/registerUserAddress", method=RequestMethod.POST)
	public String registerUserAddress(Member member) {
		log.info("registerUserAddress() 실행...!");
		log.info("member.getAddress().getPostCode() 실행...!" + member.getAddress().getPostCode());
		log.info("member.getAddress().getLocation() 실행...!" + member.getAddress().getLocation());
		
		return "success";
	}
	*/
	//폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerUserAddress", method=RequestMethod.POST)
	public String registerUserAddress(Member member) {
		
		log.info("registerUserAddress() 실행...!");
		Address address = member.getAddress();
		if(address != null) {
		log.info("member.getAddress().getPostCode() 실행...!" +address.getPostCode());
		log.info("member.getAddress().getLocation() 실행...!" +address.getLocation());
		}else {
			log.info("address is null");
		}
		return "success";
	}
	
	//폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.
		@RequestMapping(value="/registerUserCardList", method=RequestMethod.POST)
		public String registerUserCardList(Member member) {
			log.info("registerUserCardList() 실행...!");
			List<Card> cardList = member.getCardList();
			
			if(cardList != null) {
				log.info("cardList.size() :" + cardList.size());
				for(int i = 0;i<cardList.size(); i++) {
					Card card= cardList.get(i);
					log.info("card.getNo()  :" + card.getNo());
					log.info("card.getValidMonth() :" + card.getValidMonth());
				}
			}else {
				log.info("cardList is null");
			}
			return "success";
			
		}
	
		//17.폼 텍스트 영역 요소값을 기본데이터 타입인 문자열 타입 매개변수로 처리한다
		@RequestMapping(value="/registerTextArea", method=RequestMethod.POST)
		public String registerTextArea(String introduction) {
			log.info("registerTextArea() 실행...!");
			log.info("introduction()  :" + introduction);
			return "success";
		}
	
		//18. 폼 텍스트 영역값을 Date 타입 매개변수로 처리한다.
		@RequestMapping(value="/registerDate01", method=RequestMethod.POST)
		public String registerDate01(Date dateOfBirth) {
			//결과로 Date값을 받기위해서는 Default인 2023/05/24의 형태로 값을 설정해서 보내야한다.
			//내가 원하는 Date타입의 형식이 존재한다면 @DateTimeFormat Pattern 속성을 이요하여
			//원하는 패턴을 설정하여 날짜형식의 값을 넘겨받는다. (pattern="yyyyMMdd", pattern="yyyy-MM-dd")
			log.info("registerDate01() 실행...!");
			log.info("dateOfBirth()  :" + dateOfBirth);
			return "success";
		}
		
		//회원가입에 필요한 전체 폼페이지 (문제)
		
		@RequestMapping(value="/registerAllForm", method=RequestMethod.GET)
		public String registerAllForm() {
			log.info("registerAllForm () 실행....!");
			return "member/registerAllForm";
		}
		
		//전체폼페이지에 넘겨받은 데이터 모두 콘솔에 출력후
		//member폴더 아래에 있는 result페이지로 전달 후,
		// result페이지에서 넘긴 모든데이터를 영어로 된 값이 아닌 한글로 출력해주세요.
		// -input 요소에 value로 설정되어있는 값은 한글이 아닌 영어로 설정되어있어야함
		// ex) 성별 : 남자
		//	   국적 : 대한민국
		//	   개발자여부 : 개발자
		//	   외국인여부 : 한국인/외국인
		//	   소유차량 : BMW,VOLVO
		//	   취미 : 운동,영화,음악
		//	   ..그외 모든 데이터
		// >콘솔에서도 출력
		// > member/result 페이지로 이동해서도 출력(jstl적극 활용)
		// >table태그 이용해서 출력해도 됨
		
		@RequestMapping(value="/registerUser", method=RequestMethod.POST)
		public String registerUser(MemberAllForm member, Model model) {
			List<Card> cardList = member.getCardList();
			Address address = member.getAddress();
			model.addAttribute("member", member);
			log.info("userId : "+member.getUserId());
			log.info("password : "+member.getPassword());
			log.info("이름 : "+member.getUserName());
			log.info("E-mail : "+member.getEmail());
			log.info("dateOfBirth: "+member.getDateOfBirth());
			log.info("gender :" + member.getGender());
			
			if(member.getDeveloper().equals('y')) {
				log.info("developer : 개발자");
			}else {
				log.info("developer : 개발자 아님");
			}
			
			if(member.isForeigner()) {
				log.info("외국인여부 : 외국인");
			}else {
				log.info("외국인여부 :한국인");
				
			}
			log.info("국적 : " + member.getNationality()[0]);
			
			
			String cars = "";
			for(int i = 0; i < member.getCars().length; i++) {
				cars +=member.getCars()[i] +" ";
			}
			log.info("소유차량: " + cars);
			
			String hobbys = "";
			for(int i = 0; i < member.getHobby().length; i++) {
				hobbys +=member.getHobby()[i] +" ";
			}
			log.info("취미 : "+ hobbys);
			
			Address address1 = member.getAddress();
			log.info("우편번호 : " + address1.getPostCode());
			log.info("주소지 : " + address1.getLocation());
			
			if(cardList != null) {
				log.info("소유카드 갯수: "+ cardList.size());
				for(int i = 0; i < cardList.size(); i++) {
					log.info("카드 번호[" +i+"]: " + cardList.get(i).getNo());
					log.info("카드 유효기간["+i+"]: " + cardList.get(i).getValidMonth());
				}
			}else {
				log.info("소유하고 있는 카드가 없습니다!");
			}
			
			log.info("소개 : " + member.getIntroduction());
			
			return "member/result";
		}
		
		//위설정을 진행하였는데도 에러가 나는 경우 조치방법
		// > multi-part에러가 발생하거나 null에러가 발생하거나,multipartFile에러가 발생할때 설정한다.
		// > server > context.xml 내에서 context 태그 내 옵션 추가
		// > allowCasualMultipartParsing = "true" path="/"
		// > local환경에서는 셋팅이 가능하다.
		
		//파일 업로드 폼 파일 요소값을 스프링mvc가 지원하는 MUltipartFile매개변수로 처리한다.
		@RequestMapping(value="/registerFile01", method=RequestMethod.POST)
		public String registerFile01(MultipartFile picture) {
			log.info("registerFile01()실행....!");
			log.info("originalName : " + picture.getOriginalFilename());
			log.info("size : " + picture.getSize());
			log.info("contentType : " + picture.getContentType());
			return "success";
			
		}
		
		//파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 문자열 매개변수로 처리한다.
		@RequestMapping(value="/registerFile02", method=RequestMethod.POST)
		public String registerFile02(MultipartFile picture, String userId, String password) {
			log.info("registerFile02()실행....!");
			log.info("userId : " + userId);
			log.info("password : " + password);
			
			log.info("originalName : " + picture.getOriginalFilename());
			log.info("size : " + picture.getSize());
			log.info("contentType : " + picture.getContentType());
			return "success";
		}
		//파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 자바빈즈 매개변수로 처리한다.
		@RequestMapping(value="/registerFile03", method=RequestMethod.POST)
		public String registerFile03(MultipartFile picture, Member member) {
			log.info("registerFile03()실행....!");
			log.info("userId : " + member.getUserId());
			log.info("password : " + member.getPassword());
			
			log.info("originalName : " + picture.getOriginalFilename());
			log.info("size : " + picture.getSize());
			log.info("contentType : " + picture.getContentType());
			return "success";
		}
		//4.파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 FileMember 타입의 자바빈즈 매개변수로 처리한다
		@RequestMapping(value="/registerFile04", method=RequestMethod.POST)
		public String registerFile04(FileMember fileMember) {
			log.info("registerFile04()실행....!");
			log.info("userId : " + fileMember.getUserId());
			log.info("password : " + fileMember.getPassword());
			
			MultipartFile picture = fileMember.getPicture();
			log.info("originalName : " + picture.getOriginalFilename());
			log.info("size : " + picture.getSize());
			log.info("contentType : " + picture.getContentType());
			return "success";
		}
		
		//5.여러개의 파일 업로드를 폼파일 요소값을 여러개의 MultipartFile 매개변수로 처리한다.
		@RequestMapping(value="/registerFile05", method=RequestMethod.POST)
		public String registerFile05(MultipartFile picture, MultipartFile picture2) {
			log.info("registerFile05()실행....!");
			log.info("originalName : " + picture.getOriginalFilename());
			log.info("size : " + picture.getSize());
			log.info("contentType : " + picture.getContentType());
			
			log.info("originalName : " + picture2.getOriginalFilename());
			log.info("size : " + picture2.getSize());
			log.info("contentType : " + picture2.getContentType());
			return "success";
		}
		
		//여러개의 파일 업로드를 폼파일 요소값을 여러개의 MultipartFile타입 요소를 가진 리스트컬렉션 타입 매개변수로 처리한다.
		@RequestMapping(value="/registerFile06", method=RequestMethod.POST)
		public String registerFile06(List<MultipartFile> pictureList) {
			log.info("registerFile06()실행....!");
			log.info("pictureList.size() : " + pictureList.size());
			
			for(int i=0; i<pictureList.size();i++) {
				log.info("originalName["+i+"] : " + pictureList.get(i).getOriginalFilename());
				log.info("size ["+i+"] : "+ pictureList.get(i).getSize());
				log.info("contentType["+i+"]  : " + pictureList.get(i).getContentType());
			}
			return "success";
		}
		//여러개의 파일 업로드를 폼파일 요소값을 여러개의 MultipartFile타입 요소를 가진 리스트컬렉션 타입 매개변수로 처리한다.
		@RequestMapping(value="/registerFile07", method=RequestMethod.POST)
		public String registerFile07(MultipartFileMember multiFileMember) {
			log.info("registerFile07()실행....!");
			List<MultipartFile> pictureList = multiFileMember.getPictureList();
			log.info("pictureList.size() : " + pictureList.size());
			
			for(int i=0; i<pictureList.size();i++) {
				log.info("originalName["+i+"] : " + pictureList.get(i).getOriginalFilename());
				log.info("size ["+i+"] : "+ pictureList.get(i).getSize());
				log.info("contentType["+i+"]  : " + pictureList.get(i).getContentType());
			}
			return "success";
		}
		
		
		@RequestMapping(value="/registerFile08", method=RequestMethod.POST)
		public String registerFile08(MultipartFile[] pictureList) {
			log.info("registerFile08()실행....!");
			log.info("pictureList.length : " + pictureList.length);
			
			for (MultipartFile picture : pictureList) {
				log.info("originalName : " + picture.getOriginalFilename());
				log.info("size : " + picture.getSize());
				log.info("contentType : " + picture.getContentType());
			}
			return "success";
		}
}
