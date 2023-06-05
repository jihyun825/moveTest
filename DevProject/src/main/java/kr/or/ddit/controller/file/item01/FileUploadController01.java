package kr.or.ddit.controller.file.item01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.controller.file.item01.service.ItemService;
import kr.or.ddit.vo.Item;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/item")
@Slf4j
public class FileUploadController01 {
	/*
	 *  13장 파일업로드
	 *  1. 파일업로드 설명
	 *   - 서블릿 3.0에서 지원하는 파일업로드 기능과 스프링웹에서 제공하는 컴포넌트를 사용하여 파일을 업로드한다.
	 *   
	 *    파일업로드 설정
	 *    1) 서블릿 3.0이상 지원
	 *    2) 서블릿 표준 파일업로드 기능을 활성화
	 *    3) 스프링MVC와 연계
	 *    4) 업로드된 파일 저장 위치 설정
	 *    
	 *    환경설정 
	 *     1) 의존관계 정의
	 *      - 파일을 처리하기위해서 의존 라이브러리를 추가한다.
	 *      >pom.xml commons-io, commons-fileupload
	 *     2) 웹 컨테이너 설정
	 *      > web.xml 서블릿 표준 버전 3.1.로 설정
	 *      > multipart-config 활성화 (web.xml servlet 태그내 설정)
	 *     3) 스프링 웹 설정
	 *      > servlet-context.xml
	 *      > multipartResolver Bean 등록(id는 multipartResolver로 설정)
	 *    	[파일 업로드 설정]
	 *    파일업로드를 설정하는 방식은 두가지가 있다.
	 *    1. StandardServletMultipartResolver 사용시 설정
	 *    	> Servlet 3.0의 part를 이용한 multipartFile 데이터처리
	 *    	> servlet-context.xml 에 설정
	 *     		> StandardServletMultipartResolver Bean등록
	 *      > web.xml에 설정
	 *      	>multipart-config(servlet태그 안에 설정)
	 *      
	 *    2. CommonsMultipartResolver 사용시 설정(우리가 사용할 방식)
	 *    	> Commons Fileupoad API를 이용한 MultipartFile 데이터처리
	 *    	> bean 속성으로 maxUploadSize, maxInMemorySize, defaultEncoding설정을 제공한다.
	 *      > 기본값 및 허용되는 값에 대한 자세한 내용은 DiskFileUpload 속성을 참조
	 *      > pom.xml 설정
	 *      	> commons-fileupload 추가
	 *      > root-context.xml 에 설정
	 *      	> CommonsMultipartResolver bean등록
	 *      
	 *      > root-context.xml에 설정
	 *      	> uploadPath bean 등록
	 *      
	 *      >multipartFilter 등록
	 *       > web.xml
	 *   
	 *   데이터베이스 준비
	 *    1) item 테이블 생성 (item, item2,item3,item3_attach)
	 *  - 파일업로드 등록화면 컨트롤러 만들기(FileUploadController01)
	 *  - 파일업로드 등록화면 컨트롤러 메소드 만들기(itemRegisterForm:get)
	 *  - 파일업로드 등록화면 만들기(item/register.jsp)
	 *  - 여기까지 확인
	 *  
	 *  - 파일업로드 등록 기능 컨트롤러 메소드 만들기(itemRegister:post)
	 *  - 파일 업로드 등록 기능 서비스 인터페이스 메소드 만들기
	 *  - 파일 업로드 등록 기능 서비스 클래스 메소드 만들기
	 *  - 파일업로드 등록 기능 mapper 인터페이스 메소드 만들기
	 *  - 파일업로드 등록 기능 Mapper xml쿼리 만들기
	 *  - 파일업로드 등록완료 페이지 만들기
	 *  
	 *  
	 *  - 파일 업로드 목록 화면 컨트롤러 메소드 만들기(itemList:get)
	 *  - 파일 업로드 목록화면 서비스 인터페이스 메소드 만들기
	 *  - 파일 업로드 목록화면 서비스 클래스 메소드 만들기
	 *  - 파일업로드  목록화면 mapper 인터페이스 메소드 만들기
	 *  - 파일업로드 목록화면 Mapper xml쿼리 만들기
	 *  - 파일업로드 목록화면 페이지 만들기(item/list)
	 *  - 여기까지 확인
	 *  
	 *  - 파일 업로드 수정 화면 컨트롤러 메소드 만들기(itemModifyForm:get)
	 *  - 파일 업로드 수정 화면 서비스 인터페이스 메소드 만들기
	 *  - 파일 업로드 수정 화면 서비스 클래스 메소드 만들기
	 *  - 파일 업로드  수정 화면 mapper 인터페이스 메소드 만들기
	 *  - 파일 업로드 수정 화면 Mapper xml쿼리 만들기
	 *  - 파일 업로드 수정 화면 페이지 만들기(item/modify.jsp)
	 *  
	 *  - 파일 업로드 수정 기능 컨트롤러 메소드 만들기(itemModify:Post)
	 *  - 파일 업로드 수정 기능 서비스 인터페이스 메소드 만들기
	 *  - 파일 업로드 수정 기능 서비스 클래스 메소드 만들기
	 *  - 파일 업로드 수정 기능 mapper 인터페이스 메소드 만들기
	 *  - 파일 업로드 수정 기능 Mapper xml쿼리 만들기
	 *  - 여기까지 확인
	 *  
	 *  - 파일 업로드 삭제 화면 컨트롤러 메소드 만들기(itemRemoveForm:get)
	 *  - 파일 업로드 삭제 화면 서비스 인터페이스 메소드 만들기
	 *  - 파일 업로드 삭제 화면 서비스 클래스 메소드 만들기
	 *  - 파일 업로드 삭제 화면 mapper 인터페이스 메소드 만들기
	 *  - 파일 업로드 삭제 화면 Mapper xml쿼리 만들기
	 *  
	 *  - 파일 업로드 삭제 화면 페이지 만들기(item/remove.jsp)
	 *  - 파일 업로드 삭제 기능 컨트롤러 메소드 만들기(itemRemove:post)
	 *  - 파일 업로드 삭제 기능 서비스 인터페이스 메소드 만들기
	 *  - 파일 업로드 삭제 기능 서비스 클래스 메소드 만들기
	 *  - 파일 업로드 삭제 기능 mapper 인터페이스 메소드 만들기
	 *  - 파일 업로드 삭제 기능 Mapper xml쿼리 만들기
	 *  - 여기까지 확인
	 */
	// root-context.xml 에서 설정한 uploadPath 빈 등록 path경로를 사용한다.(value로 설정했던값)
	@Resource(name="uploadPath")
	private String resourcesPath;
	
	@Inject
	private ItemService itemService;
 	
//	private String uploadPath = "C://upload"
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String itemRegisterForm() {
		return "item/register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String itemRegister(Item item, Model model) throws IOException {
		MultipartFile file = item.getPicture();
		log.info("originalName: "+ file.getName());
		log.info("size: "+ file.getSize());
		log.info("contentType: "+ file.getContentType());
		
		//넘겨받은 파일을 이용하여 파일 업로드 (복사) 진행한다.
		// return : UUID + "_" + 원본파일명
		String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
		item.setPictureUrl(createdFileName);
		itemService.register(item);
		
		model.addAttribute("msg", "등록이완료되었습니다.");
		
		return "item/success";
	}
	//리스트 출력
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String itemList(Model model) {
		List<Item> itemList = itemService.list();
		model.addAttribute("itemList", itemList);
		return "item/list";
		
		
	}
	
	//수정페이지
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public String itemModifyForm(int itemId, Model model) {
		Item item = itemService.read(itemId);
		model.addAttribute("item", item);
		return "item/modify";
		
	}
	
	
	@RequestMapping(value="/display")
	public ResponseEntity<byte[]> displayFile(int itemId) throws FileNotFoundException{
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		String fileName = itemService.getPicture(itemId);
		log.info("fileName : "+fileName);
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1); //확장자를 추출
			MediaType mType = getMediaType(formatName);
			
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(resourcesPath + File.separator + fileName); //separator는 '/'와 동일
			if(mType != null) {
				headers.setContentType(mType);
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			try {
				in.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		
		}
		return entity;
		
	}
	//수정 처리
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String itemModify(Item item, Model model) throws IOException {
		MultipartFile file = item.getPicture();
		if(file != null && file.getSize() > 0) {
			log.info("originalName: "+ file.getOriginalFilename());
			log.info("size: "+ file.getSize());
			log.info("contentType: "+ file.getContentType());
			String createdFileName = uploadFile(file.getOriginalFilename(),file.getBytes());
			item.setPictureUrl(createdFileName);
		}
		itemService.modify(item);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "item/success";
	}
	
	//삭제폼
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public String itemRemoveForm(int itemId, Model model) {
		Item item = itemService.read(itemId);
		model.addAttribute("item", item);
		return "item/remove";
	}
	
	//삭제처리
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String itemRemove(int itemId, Model model) {
		itemService.remove(itemId);
		model.addAttribute("msg","삭제가 완료되었음다");
		return  "item/success";
	}
	
	//데이터타입 가져오는 메소드
	private MediaType getMediaType(String formatName) {
		if(formatName != null) {
			if(formatName.toUpperCase().equals("JPG")) {
				return MediaType.IMAGE_JPEG;
			}
			if(formatName.toUpperCase().equals("GIF")) {
				return MediaType.IMAGE_GIF;
			}
			if(formatName.toUpperCase().equals("PNF")) {
				return MediaType.IMAGE_PNG;
			}
		}
		
		return null;
	}

	//파일업로드 메소드
	private String uploadFile(String originalName, byte[] fileData) throws IOException {
		System.out.println("resourcesPath: " + resourcesPath);
		UUID uuid = UUID.randomUUID();
		String createdFileName= uuid.toString() + "_" + originalName;
		File file = new File(resourcesPath);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		File target = new File(resourcesPath, createdFileName);
		FileCopyUtils.copy(fileData, target); //  파일복사
		return createdFileName;
	}
}
