package kr.or.ddit.controller.file.item03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.controller.file.item03.service.ItemService3;
import kr.or.ddit.vo.Item3;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/item3")
public class FileUploadController03 {
	
	/*
	 * 4. 비동기 방식 업로드
	 *  - 비동기 방식으로 여러개의 이미지를 업로드하는 파일 업로드 기능을 구현한다.
	 *   1. 환경설정
	 *   1-1) 의존관계 정의
	 *    - commons - io : 파일을 처리하기 위한 의존 라이브러리
	 *    - imgscalr - lib : 이미지변환을 처리하기 위한 의존 라이브러리
	 *    - jackson-databind : json 데이터 바인딩을 위한 의존 라이브러리
	 *    *** MAVEN > Update Projects 진행하여 의존 등록 완료(설정)
	 *    
	 *    
	 *    2. 파일 업로드 구현 설명
	 *     - 파일업로드 등록화면 컨트롤러 만들기(FileUploadController03)
	 *     - 파일 업로드 등록 화면 컨트롤러 메소드 만들기(iem3RegisterForm:get)
	 *     - 파일 업로드 등록 화면 만들기(item3/register.jsp)
	 *     - 여기까지 확인
	 *     
	 *     - 파일 업로드 등록  기능 컨트롤러 메소드 만들기(iem3Register:Post)
	 *     - 파일 업로드 등록 기능 서비스 인터페이스 메소드만들기
	 *     - 파일 업로드 등록 기능 서비스 클래스 메소드 만들기
	 *     - 파일 업로드 등록기능 Mapper인터페이스 메소드  만들기
	 *     - 파일 업로드 등록 기능 Mapper xml 쿼리 만들기
	 *     - 파일업로드 등록 완료 페이지 만들기
	 *     - 여기까지 확인	 
	 *     
	 *     - 파일 업로드 목록 화면 컨트롤러 메소드 만들기(iem3List:get)
	 *     - 파일 업로드 목록 화면 서비스 인터페이스 메소드만들기
	 *     - 파일 업로드 목록 화면 서비스 클래스 메소드 만들기
	 *     - 파일 업로드 목록 화면 Mapper인터페이스 메소드  만들기
	 *     - 파일 업로드 목록 화면 Mapper xml 쿼리 만들기
	 *     - 파일업로드 목록 화면 페이지 만들기
	 *     - 여기까지 확인	 
	 *     
	 */
	@Inject
	private ItemService3 itemService;
	
	//root-Context.xml에서 설정한 uploadPath빈등록 path경로를 사용한다.
	@Resource(name="uploadPath")
	private String resourcePath;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String itemRegisterForm() {
		return "item3/register";
		
	}
	@RequestMapping(value="/uploadAjax",method=RequestMethod.POST, produces = "text/plain; charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws IOException{
		log.info("originalName : " + file.getOriginalFilename());
		String savedName = UploadFileUitls.uploadFile(resourcePath, file.getOriginalFilename(),file.getBytes());
		return new ResponseEntity<String>(savedName,HttpStatus.CREATED);
	}
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String item3Register(Item3 item, Model model) {
		String[] files = item.getFiles();
		
		for(int i=0; i<files.length; i++) {
			log.info("files["+i+"] : " + files[i]);
		}
		
		itemService.register(item);
		model.addAttribute("msg","등록이 완료되었습니다.");
		return "item3/success";
	}
	
	@RequestMapping(value="/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws FileNotFoundException{
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
	
		log.info("fileName :" + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(resourcePath + fileName);
			if(mType != null) {
				headers.setContentType(mType);
			}else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("content-Disposition", "attachment; filename=\"" + 
						new String (fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			
		}finally{
			try {
				in.close();
			} catch (IOException e2) {
				e2.printStackTrace();			
			}
		}
		return entity;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String item3List(Model model) {
		List<Item3> itemList = itemService.list();
		model.addAttribute("itemList",itemList);
		return "item3/list";
		
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String item3ModifyForm(int itemId, Model model) {
		Item3 item = itemService.read(itemId);
		model.addAttribute("item",item);
		return "item3/modify";
	}
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String item3Modify(Item3 item, Model model) {
		String[] files = item.getFiles();
		for(int i= 0; i<files.length; i++) {
			log.info("files ["+i+"]: "+files[i]);
		}
		
		itemService.modify(item);
		model.addAttribute("msg", "수정완료");
		return "item3/success";
		
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public String item3RemoveForm(int itemId, Model model) {
		Item3 item = itemService.read(itemId);
		model.addAttribute("item", item);
		return "item3/remove";
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String item3Remove(Item3 item,Model model) {
		itemService.remove(item);
		model.addAttribute("msg", "삭제완료");
		return "item3/success";
	}
	
	@ResponseBody
	@RequestMapping(value="/getAttach/{itemId}")
	public List<String> getAttach(@PathVariable("itemId") int itemId){
		log.info("itemId : "+ itemId);
		return itemService.getAttach(itemId);
		
	}
}
